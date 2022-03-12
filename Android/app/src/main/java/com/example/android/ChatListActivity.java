package com.example.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatListActivity extends AppCompatActivity {

    public int ActivityNum = 0;
    private FloatingActionButton newChatbtn;
    private ListView chatList;
    ArrayList<String> chatIDs;
    ArrayList<String> chatNames;
    private SharedPreferences prefs;
    String currUserRole;
    long numOfChats;
    int currCount=0;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("role",MODE_PRIVATE);
        currUserRole = prefs.getString("userRole","");
        setContentView(R.layout.activity_chat_list);
        setUpBottomNavigationViewForDoctor();
        currUserRole = "doctor";
        chatList = findViewById(R.id.chatList);
        initPrevChats();
    }

    private void setUpBottomNavigationViewForDoctor() {
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavViewBar);
        bottomNavigationView.setItemIconTintList(null);
        BottomNavigationHelperForDoctor.enableNavigation(ChatListActivity.this,bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ActivityNum);
        menuItem.setChecked(true);
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
                Toast.makeText(ChatListActivity.this,"Cancelled "+error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addNameAsPerUserRole() {
        String nameType = "";
        if(currUserRole=="patient"){
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
        ChatListAdapter adapter = new ChatListAdapter(this,chatIDs,chatNames);
        chatList.setAdapter(adapter);
    }

    private void findUserName(String roomID) {
        String currUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(currUserRole).child(currUserId).child("info");
        ref.child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String username = snapshot.getValue(String.class);
                Intent intent = new Intent(ChatListActivity.this, ChatActivity.class);
                intent.putExtra("roomId",roomID);
                intent.putExtra("currUserName",username);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void navigateToChatRoom(View view) {
        TextView roomID = view.findViewById(R.id.roomID);
        String chatRoomID = roomID.getText().toString();
        chatIDs = new ArrayList<>();
        chatNames = new ArrayList<>();
        findUserName(chatRoomID);
    }
}