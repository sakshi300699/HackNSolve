package com.example.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class NewDoctorSearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    DoctorSearchAdapter adapter;
    private FirebaseAuth auth;
    private FirebaseDatabase db;
    DatabaseReference myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_doctor_search);
        initialiseWidgets();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private void initialiseWidgets() {
        recyclerView = findViewById(R.id.doctorList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Query query = FirebaseDatabase.getInstance().getReference().child("doctor");
        FirebaseRecyclerOptions<TempDoctor> options = new FirebaseRecyclerOptions.Builder<TempDoctor>().setQuery(query, TempDoctor.class).build();
        adapter = new DoctorSearchAdapter(options);
        recyclerView.setAdapter(adapter);
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
    }

    public void createChatRoom(View view) {
        TextView txt = view.findViewById(R.id.doctorUserID);
        String uidDoc = txt.getText().toString();
        String uidPat = auth.getCurrentUser().getUid();
        TextView nameDocTxt = view.findViewById(R.id.name);
        String nameDoc = nameDocTxt.getText().toString();
        findUserName(uidPat,uidDoc,nameDoc);
    }

    private void findUserName(String uidPat, String uidDoc, String nameDoc) {
        myref = db.getReference().child("patient").child(uidPat).child("info");
        myref.child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String namePat = snapshot.getValue(String.class);
                if(namePat.length()>1){
                    enterDataInChatNode(uidPat,uidDoc,nameDoc,namePat);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void enterDataInChatNode(String uidPat, String uidDoc, String nameDoc, String namePat) {
        final Map map = new HashMap<>();
        myref = db.getReference().child("consulation");
        String chatRoomID = myref.push().getKey();
        map.put("uidDoc",uidDoc);
        map.put("uidPat",uidPat);
        map.put("nameDoc",nameDoc);
        map.put("namePat",namePat);
        myref.child(chatRoomID).child("info").updateChildren(map);
        registerChatRoomWithUser(uidDoc,true,chatRoomID);
        registerChatRoomWithUser(uidPat,false,chatRoomID);
        Intent intent = new Intent(NewDoctorSearchActivity.this,ChatActivity.class);
        intent.putExtra("roomId",chatRoomID);
        intent.putExtra("currUserName",namePat);
        startActivity(intent);
    }

    private void registerChatRoomWithUser(String uid, boolean b,String chatRoomID) {
        if (b){
            myref = db.getReference().child("doctor").child(uid);
            myref.child("chatRooms").child(chatRoomID).setValue("true");
        }else{
            myref = db.getReference().child("patient").child(uid);
            myref.child("chatRooms").child(chatRoomID).setValue("true");
        }
    }
}