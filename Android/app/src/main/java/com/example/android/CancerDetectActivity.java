package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CancerDetectActivity extends AppCompatActivity {

    Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancer_detect);
        go=findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareData();
            }
        });
    }

    private void prepareData() {
        String age = ((EditText)findViewById(R.id.age)).getText().toString();
        String sexPartners = ((EditText)findViewById(R.id.numPartners)).getText().toString();
        String ageFirstInterCourse = ((EditText)findViewById(R.id.ageFirst)).getText().toString();
        String numPreg = ((EditText)findViewById(R.id.numPregnancy)).getText().toString();
        String smoke="1.0";
        if(((RadioButton)findViewById(R.id.noSmoke)).isChecked()){
            smoke="0.0";
        }
        String numYearSmoking = ((EditText)findViewById(R.id.numYearSmoke)).getText().toString();
        String numPacketSmoke = ((EditText)findViewById(R.id.numPacketsSmoke)).getText().toString();
        String hormonalContra = "1.0";
        if(((RadioButton)findViewById(R.id.noHormonalContra)).isChecked()){
            hormonalContra="0.0";
        }
        String numYearContra = ((EditText)findViewById(R.id.numYearContra)).getText().toString();
        String iud = "1.0";
        if(((RadioButton)findViewById(R.id.noIuds)).isChecked()){
            iud="0.0";
        }
        String numYearIUD = ((EditText)findViewById(R.id.numYearIud)).getText().toString();
        String std = "1.0";
        if(((RadioButton)findViewById(R.id.noStd)).isChecked()){
            std="0.0";
        }
        String numSTD = ((EditText)findViewById(R.id.numStd)).getText().toString();
        String condy = "1.0";
        if(((RadioButton)findViewById(R.id.noCondy)).isChecked()){
            condy="0.0";
        }
        String vaginalCondy = "1.0";
        if(((RadioButton)findViewById(R.id.noVagCondy)).isChecked()){
            vaginalCondy="0.0";
        }
        String vulvao = "1.0";
        String syph = "1.0";
        if(((RadioButton)findViewById(R.id.noSyph)).isChecked()){
            syph="0.0";
        }
        String pelvicInflam = "1.0";
        if(((RadioButton)findViewById(R.id.noPelvic)).isChecked()){
            pelvicInflam="0.0";
        }
        String herpes = "1.0";
        if(((RadioButton)findViewById(R.id.noHerp)).isChecked()){
            herpes="0.0";
        }
        String molluscum = "1.0";
        String hiv = "1.0";
        if(((RadioButton)findViewById(R.id.noHiv)).isChecked()){
            hiv="0.0";
        }
        String hepB = "1.0";
        if(((RadioButton)findViewById(R.id.noHepB)).isChecked()){
            hepB="0.0";
        }
        String hpv = "1.0";
        if(((RadioButton)findViewById(R.id.noHpv)).isChecked()){
            hpv="0.0";
        }
        String numDiaSTD = ((EditText)findViewById(R.id.numDiaSTDs)).getText().toString();
        String diaCancer = "1.0";
        if(((RadioButton)findViewById(R.id.noCancerDiag)).isChecked()){
            diaCancer="0.0";
        }
        String diaCin = "1.0";
        if(((RadioButton)findViewById(R.id.noCinDiag)).isChecked()){
            diaCin="0.0";
        }
        String diaHpv = "1.0";
        if(((RadioButton)findViewById(R.id.noHpvDiag)).isChecked()){
            diaHpv="0.0";
        }
        String repDiseaseDiag = "1.0";
        if(((RadioButton)findViewById(R.id.noRepDis)).isChecked()){
            repDiseaseDiag="0.0";
        }
        CancerDetails details = new CancerDetails(age,sexPartners,ageFirstInterCourse,numPreg,smoke,numYearSmoking,numPacketSmoke,hormonalContra,numYearContra,
                iud,numYearIUD,std,numSTD,condy,vaginalCondy,vulvao,syph,pelvicInflam,herpes,molluscum,hiv,hepB,hpv,numDiaSTD,diaCancer,diaCin,diaHpv,repDiseaseDiag);

        Call<AuthResponseCancer> call = AppClient.getInstance().createService(APIServices.class).getCancerDetails(details);
        call.enqueue(new Callback<AuthResponseCancer>() {
            @Override
            public void onResponse(Call<AuthResponseCancer> call, Response<AuthResponseCancer> response) {
                try {
                    if (getApplicationContext() != null) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                AuthResponseCancer authResponse = response.body();
                                Toast.makeText(CancerDetectActivity.this, String.valueOf(authResponse.getBiopsy()),Toast.LENGTH_SHORT).show();
                                
                            } else
                                Toast.makeText(CancerDetectActivity.this, "Couldn't log you in. Please try again.",Toast.LENGTH_SHORT).show();
                        } else if(response.code() == 400){
                            Toast.makeText(CancerDetectActivity.this, "Invalid Credentials!",Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(CancerDetectActivity.this, "Couldn't log you in. Please try again later.",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AuthResponseCancer> call, Throwable t) {
                Toast.makeText(CancerDetectActivity.this, "Failed!",Toast.LENGTH_SHORT).show();
            }
        });


    }
}