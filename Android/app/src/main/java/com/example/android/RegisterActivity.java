package com.example.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class RegisterActivity extends AppCompatActivity {

    public View customview;
    public AlertDialog mDialog;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference myref;
    private GifImageView registerGif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showAlertDialogue();
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
    }

    private void showAlertDialogue() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(RegisterActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        customview = inflater.inflate(R.layout.layout_popup_patordoc,null);
        mBuilder.setView(customview);
        mDialog = mBuilder.create();
        mDialog.show();
    }

    public void patientLogin(View view) {
        setContentView(R.layout.activity_register);
        registerGif = findViewById(R.id.registerGif);
        mDialog.dismiss();
    }

    public void doctorLogin(View view) {
        setContentView(R.layout.activity_register_doctor);
        initialiseSpinner();
        mDialog.dismiss();
    }

    private ArrayList<String> getCustomerList()
    {
        ArrayList<String> customers = new ArrayList<>();
        customers.add("Cardiologist");
        customers.add("Dermatologist");
        customers.add("Physician");
        customers.add("Hematologist");
        return customers;
    }

    private void initialiseSpinner() {
        final AutoCompleteTextView customerAutoTV = findViewById(R.id.customerTextView);

        // create list of customer
        ArrayList<String> customerList = getCustomerList();

        //Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(RegisterActivity.this, android.R.layout.simple_spinner_item, customerList);

        //Set adapter
        customerAutoTV.setAdapter(adapter);
    }

    public void registerPatient(View view) {
        EditText editName = findViewById(R.id.name);
        EditText editEmail = findViewById(R.id.email);
        EditText editPass = findViewById(R.id.password);

        String name = editName.getText().toString();
        String email = editEmail.getText().toString();
        String password = editPass.getText().toString();

        if(validateRegisterForPatient(name,email,password)){
            //registerGif.setImageResource(R.drawable.gif_loading);
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    String UUID = user.getUid();
                    enterInfoInDBPatient(name,email,UUID);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    registerGif.setImageResource(R.drawable.register_patient_gif);
                    Toast.makeText(RegisterActivity.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void enterInfoInDBPatient(String name, String email,String userID) {
        myref = db.getReference("patient").child(userID).child("info");
        myref.child("name").setValue(name);
        myref.child("email").setValue(email);
        myref.child("UID").setValue(userID);
        myref = db.getReference("roles");
        myref.child(userID).setValue("Patient");
    }

    private boolean validateRegisterForPatient(String name, String mail, String password) {
        if(name.length()==0 || name.trim().length()==0){
            Toast.makeText(RegisterActivity.this,R.string.reqName,Toast.LENGTH_SHORT).show();
            return false;
        }
        if(mail.length()==0 || mail.trim().length()==0){
            Toast.makeText(RegisterActivity.this,R.string.reqMail,Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.length()<8 || password.trim().length()<8){
            Toast.makeText(RegisterActivity.this,R.string.reqPass,Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void registerDoctor(View view) {
        final AutoCompleteTextView specialisationDropDown = findViewById(R.id.customerTextView);
        EditText editName = findViewById(R.id.name);
        EditText editEmail = findViewById(R.id.email);
        EditText editPass = findViewById(R.id.password);
        EditText editMedId = findViewById(R.id.medID);

        String name = editName.getText().toString();
        String email = editEmail.getText().toString();
        String password = editPass.getText().toString();
        String specialisation = specialisationDropDown.getText().toString();
        String medID = editMedId.getText().toString();

        if(validateRegisterForDoctor(name,email,password,specialisation,medID)){
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    enterInfoInDBDoctor(name,email,specialisation,medID,userID);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterActivity.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void enterInfoInDBDoctor(String name, String email, String specialisation, String medID, String userID) {
        myref = db.getReference("doctor").child(userID).child("info");
        myref.child("name").setValue(name);
        myref.child("email").setValue(email);
        myref.child("specialisation").setValue(specialisation);
        myref.child("medID").setValue(medID);
        myref.child("isVerified").setValue("false");
        myref.child("UID").setValue(userID);
        myref = db.getReference("roles");
        myref.child(userID).setValue("Doctor");
        myref = db.getReference().child("specialisation").child(specialisation);
        myref.child(userID).setValue(name);
    }

    private boolean validateRegisterForDoctor(String name, String email, String password, String specialisation, String medID) {
        if(name.length()==0 || name.trim().length()==0){
            Toast.makeText(RegisterActivity.this,R.string.reqName,Toast.LENGTH_SHORT).show();
            return false;
        }
        if(email.length()==0 || email.trim().length()==0){
            Toast.makeText(RegisterActivity.this,R.string.reqMail,Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.length()<8 || password.trim().length()<8){
            Toast.makeText(RegisterActivity.this,R.string.reqPass,Toast.LENGTH_SHORT).show();
            return false;
        }
        if(specialisation.length()==0 || specialisation.trim().length()==0){
            Toast.makeText(RegisterActivity.this,R.string.reqSpl,Toast.LENGTH_SHORT).show();
            return false;
        }
        if(medID.length()==0 || medID.trim().length()==0){
            Toast.makeText(RegisterActivity.this,R.string.reqID,Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}