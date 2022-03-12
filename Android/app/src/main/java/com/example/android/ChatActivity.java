package com.example.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    String chatRoomID = "null";
    EditText composeMsg;
    Button sendBtn, dateSelector, timeSelector, scheduleCall;
    private RecyclerView chatList;
    private RecyclerView.Adapter messageListAdapter;
    private RecyclerView.LayoutManager messageListLayoutManager;
    ArrayList<MessageObject> messageList;
    String currUserName;
    private FloatingActionButton newCall;
    private SharedPreferences prefs;
    String currUserRole;
    public AlertDialog mDialog;
    public View customview;
    DatabaseReference myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chatRoomID = getIntent().getStringExtra("roomId");
        currUserName = getIntent().getStringExtra("currUserName");
        prefs = getSharedPreferences("role",MODE_PRIVATE);
        currUserRole = prefs.getString("userRole","");
        initialiseWidgets();
        initializeMessage();
        getPrevMessages();
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = composeMsg.getText().toString();
                if(message!=null && message.length()>0){
                    initiateSendMsg(message);
                    composeMsg.setText("");
                }
            }
        });
        newCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ChatActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                customview = inflater.inflate(R.layout.layout_popup_schedule_app,null);
                mBuilder.setView(customview);
                mDialog = mBuilder.create();
                mDialog.show();
                dateSelector = customview.findViewById(R.id.dateSelector);
                timeSelector = customview.findViewById(R.id.timeSelector);
                scheduleCall = customview.findViewById(R.id.scheduleCall);

                scheduleCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String date = dateSelector.getText().toString();
                        String time = timeSelector.getText().toString();
                        if(validate(date,time)){
                            addAppointment(date,time);
                        }
                    }
                });

                MaterialDatePicker.Builder dateBuilder = MaterialDatePicker.Builder.datePicker();
                dateBuilder.setTitleText("SELECT APPOINTMENT DATE");
                MaterialTimePicker timeBuilder = new MaterialTimePicker.Builder().setTitleText("SELECT YOUR TIMING").setHour(12).setMinute(10).setTimeFormat(TimeFormat.CLOCK_24H).build();

                final MaterialDatePicker datePicker = dateBuilder.build();
                dateSelector.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        datePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
                    }
                });
                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        dateSelector.setText(datePicker.getHeaderText());
                    }
                });

                timeSelector.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timeBuilder.show(getSupportFragmentManager(),"MATERIAL_TIME_PICKER");
                    }
                });

                timeBuilder.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timeSelector.setText(timeBuilder.getHour()+" : "+timeBuilder.getMinute());
                    }
                });

            }
        });
    }

    private boolean validate(String date, String time) {
        if(!date.contains(",")){
            Toast.makeText(ChatActivity.this,"Please select a date",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!time.contains(":")){
            Toast.makeText(ChatActivity.this,"Please select a time",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void addAppointment(String date, String time) {
        myref = FirebaseDatabase.getInstance().getReference().child("consulation").child(chatRoomID).child("info");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                InfoModel infoNode = snapshot.getValue(InfoModel.class);
                scheduleCallWithDoctor(infoNode,date,time);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void scheduleCallWithDoctor(InfoModel infoNode, String date, String time) {
        Map map = new HashMap<>();
        map.put("nameDoc",infoNode.getNameDoc());
        map.put("namePat",infoNode.getNamePat());
        map.put("uidDoc",infoNode.getUidDoc());
        map.put("uidPat",infoNode.getNamePat());
        map.put("date",date);
        map.put("time",time);
        map.put("link","Link goes here");
        myref = FirebaseDatabase.getInstance().getReference().child("callRooms");
        String callRoomKey = myref.push().getKey();
        myref.child(callRoomKey).updateChildren(map);
        registerCallRoomWithUser(infoNode.getUidDoc(),true,callRoomKey);
        registerCallRoomWithUser(infoNode.getUidPat(),false,callRoomKey);
        mDialog.dismiss();
        Toast.makeText(ChatActivity.this,"Appointment Booked Successfully",Toast.LENGTH_SHORT).show();

    }

    private void registerCallRoomWithUser(String uid, boolean b,String chatRoomID) {
        if (b){
            myref = FirebaseDatabase.getInstance().getReference().child("doctor").child(uid);
            myref.child("callRooms").child(chatRoomID).setValue("true");
        }else{
            myref = FirebaseDatabase.getInstance().getReference().child("patient").child(uid);
            myref.child("callRooms").child(chatRoomID).setValue("true");
        }
    }

    private void initiateSendMsg(String message) {
        DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child("consulation").child(chatRoomID).child("messages");
        String messageID = myref.push().getKey();
        final Map map = new HashMap<>();
        map.put("creator",currUserName);
        map.put("text",message);
        myref.child(messageID).updateChildren(map);
    }

    private void initialiseWidgets() {
        composeMsg = findViewById(R.id.messageInput);
        sendBtn = findViewById(R.id.send);
        chatList = findViewById(R.id.messageList);
        newCall = findViewById(R.id.btn_newcall);
        if(currUserRole.equals("Doctor")){
            newCall.setVisibility(View.GONE);
        }
    }

    private void getPrevMessages() {
        DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child("consulation").child(chatRoomID).child("messages");
        myref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.exists()){
                    String text = "", creator = "";
                    if(snapshot.child("text").getValue()!=null){
                        text = snapshot.child("text").getValue().toString();
                    }
                    if(snapshot.child("creator").getValue()!=null){
                        creator = snapshot.child("creator").getValue().toString();
                    }
                    MessageObject mMessage = new MessageObject(snapshot.getKey(), creator, text);
                    messageList.add(mMessage);
                    messageListLayoutManager.scrollToPosition(messageList.size()-1);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @SuppressLint("WrongConstant")
    private void initializeMessage() {
        messageList = new ArrayList<>();
        chatList= findViewById(R.id.messageList);
        chatList.setNestedScrollingEnabled(false);
        chatList.setHasFixedSize(false);
        messageListLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL, false);
        chatList.setLayoutManager(messageListLayoutManager);
        messageListAdapter = new MessageAdapter(messageList);
        chatList.setAdapter(messageListAdapter);
    }
}