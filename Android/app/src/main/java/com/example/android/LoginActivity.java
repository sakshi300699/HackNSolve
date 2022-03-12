package com.example.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText email,password;
    private Button login_btn;
    private TextView register,forgotPwd;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference myref;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialiseWidgets();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        forgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this,R.string.forgot_pwd,Toast.LENGTH_SHORT).show();
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString();
                String pass = password.getText().toString();
                if(validateLogin(mail,pass)){
                    loginUser(mail,pass);
                }
            }
        });

    }

    private void loginUser(String mail, String pass) {
        mAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    myref = db.getReference("roles");
                    myref.child(userID).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String role = snapshot.getValue(String.class);
                            saveUserRole(role);
                            loginUserAsPerRole(role,userID);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveUserRole(String role) {
        SharedPreferences.Editor editor = getSharedPreferences("role" , MODE_PRIVATE).edit();
        editor.putString("userRole", role);
        editor.commit();
    }

    private void initialiseWidgets() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login);
        register = findViewById(R.id.register);
        forgotPwd = findViewById(R.id.forgotPwd);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        prefs = getSharedPreferences("role",MODE_PRIVATE);
        checkIfUserIsAlreadyLoggedIn();
    }

    private void checkIfUserIsAlreadyLoggedIn() {
        String role = prefs.getString("userRole","");
        FirebaseUser user = mAuth.getCurrentUser();
        if(role==null || role.length()==0){
            if(user!=null){
                String userID = user.getUid();
                myref = db.getReference("roles");

                myref.child(userID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String Role = snapshot.getValue(String.class);
                        loginUserAsPerRole(Role,userID);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }else {
            if(user!=null){
                loginUserAsPerRole(role,mAuth.getCurrentUser().getUid());
            }
        }

    }

    private boolean validateLogin(String mail, String password) {
        if(mail.length()==0 || mail.trim().length()==0){
            Toast.makeText(LoginActivity.this,R.string.reqMail,Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.length()<8 || password.trim().length()<8){
            Toast.makeText(LoginActivity.this,R.string.reqPass,Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void loginUserAsPerRole(String role, String userID){
        if(role.equals("Patient")){
            Intent intent = new Intent(LoginActivity.this,PatientHomeActivity.class);
            startActivity(intent);
        }
        if(role.equals("Doctor")){
            myref = db.getReference("doctor").child(userID).child("info");
            myref.child("isVerified").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String isVerified = snapshot.getValue(String.class);
                    if(isVerified.equals("false")){
                        Toast.makeText(LoginActivity.this,R.string.yetTobeVerified,Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent = new Intent(LoginActivity.this,ChatListActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(LoginActivity.this,error.getMessage().toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}