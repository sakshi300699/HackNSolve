package com.example.android.ui.gallery;

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

import com.example.android.CallInfoModel;
import com.example.android.CallListAdapter;
import com.example.android.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    public int ActivityNumPat = 2;
    public int ActivityNumDoc = 1;
    private SharedPreferences prefs;
    String currUserRole;
    private BottomNavigationView bottomNavigationView;
    ArrayList<CallInfoModel> callInfos;
    ArrayList<String> callRoomIds;
    long numOfCalls;
    int currCount=0;
    ListView callList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        prefs = this.getActivity().getSharedPreferences("role",MODE_PRIVATE);
        currUserRole = prefs.getString("userRole","").toLowerCase();

        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        callList = root.findViewById(R.id.callList);
        initCallList();
        return root;
    }

    private void initCallList() {
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child(currUserRole).child(userID);
        callRoomIds = new ArrayList<>();
        callInfos = new ArrayList<>();
        myref.child("callRooms").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                numOfCalls = snapshot.getChildrenCount();
                for(DataSnapshot ds:snapshot.getChildren()){
                    callRoomIds.add(ds.getKey());
                }
                addInfosForCallRooms();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Toast.makeText(CallsActivity.this,"Cancelled "+error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addInfosForCallRooms() {
        DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child("callRooms");
        for(String callRoomID: callRoomIds){
            myref.child(callRoomID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    CallInfoModel infoModel = snapshot.getValue(CallInfoModel.class);
                    callInfos.add(infoModel);
                    currCount++;
                    if(currCount==numOfCalls){
                        addAdapterToActivity();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void addAdapterToActivity() {
        CallListAdapter adapter = new CallListAdapter(this.getActivity(),callInfos);
        callList.setAdapter(adapter);
    }

}