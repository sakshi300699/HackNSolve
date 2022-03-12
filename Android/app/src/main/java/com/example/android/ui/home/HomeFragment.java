package com.example.android.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.ChatActivity;
import com.example.android.ChatListAdapter;
import com.example.android.PatientHomeActivity;
import com.example.android.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ListView chatList;
    ArrayList<String> chatIDs;
    ArrayList<String> chatNames;
    private SharedPreferences prefs;
    String currUserRole;
    long numOfChats;
    int currCount=0;
    Activity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        prefs = this.getActivity().getSharedPreferences("role",MODE_PRIVATE);
        currUserRole = prefs.getString("userRole","").toLowerCase();
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        chatList = root.findViewById(R.id.chatList);
        initPrevChats();
        return root;
    }

    private void initPrevChats() {
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child(currUserRole).child(userID);
        chatIDs = new ArrayList<>();
        chatNames = new ArrayList<>();
        myref.child("chatRooms").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                numOfChats = snapshot.getChildrenCount();
                for(DataSnapshot ds:snapshot.getChildren()){
                    chatIDs.add(ds.getKey());
                }
                addNameAsPerUserRole();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Cancelled "+error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addNameAsPerUserRole() {
        String nameType = "";
        if(currUserRole.equals("patient")){
            nameType = "nameDoc";
        }else{
            nameType = "namePat";
        }
        addNameAsType(nameType);
    }

    private void addNameAsType(String nameType) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("consulation");
        for(String s: chatIDs){
            ref.child(s).child("info").child(nameType).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String docName = snapshot.getValue(String.class);
                    currCount++;
                    chatNames.add(docName);
                    if(currCount ==numOfChats){
                        addAdapterToList(chatIDs,chatNames);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void addAdapterToList(ArrayList<String> chatIDs, ArrayList<String> chatNames) {
        ChatListAdapter adapter = new ChatListAdapter(this.getActivity(),chatIDs,chatNames);
        chatList.setAdapter(adapter);
    }

    public void goToChatRoom(View view) {
        TextView roomID = view.findViewById(R.id.roomID);
        String chatRoomID = roomID.getText().toString();
        chatIDs = new ArrayList<>();
        chatNames = new ArrayList<>();
        findUserName(chatRoomID);
    }

    private void findUserName(String roomID) {
        String currUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("patient").child(currUserId).child("info");
        ref.child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String username = snapshot.getValue(String.class);
                Intent intent = new Intent(activity, ChatActivity.class);
                intent.putExtra("roomId",roomID);
                intent.putExtra("currUserName",username);
                resetArrays();
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void resetArrays() {
        chatIDs = new ArrayList<>();
        chatNames = new ArrayList<>();
    }
}