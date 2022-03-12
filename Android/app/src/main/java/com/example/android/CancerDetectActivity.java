package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

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
        boolean smoke=true;
        if(((RadioButton)findViewById(R.id.noSmoke)).isChecked()){
            smoke=false;
        }
        String numYearSmoking = ((EditText)findViewById(R.id.numYearSmoke)).getText().toString();
        String numPacketSmoke = ((EditText)findViewById(R.id.numPacketsSmoke)).getText().toString();
        boolean hormonalContra = true;
        if(((RadioButton)findViewById(R.id.noHormonalContra)).isChecked()){
            hormonalContra=false;
        }
        String numYearContra = ((EditText)findViewById(R.id.numYearContra)).getText().toString();
        boolean iud = true;
        if(((RadioButton)findViewById(R.id.noIuds)).isChecked()){
            iud=false;
        }
        String numYearIUD = ((EditText)findViewById(R.id.numYearIud)).getText().toString();
        boolean std = true;
        if(((RadioButton)findViewById(R.id.noStd)).isChecked()){
            std=false;
        }
        String numSTD = ((EditText)findViewById(R.id.numStd)).getText().toString();
        boolean condy = true;
        if(((RadioButton)findViewById(R.id.noCondy)).isChecked()){
            condy=false;
        }
        boolean vaginalCondy = true;
        if(((RadioButton)findViewById(R.id.noVagCondy)).isChecked()){
            vaginalCondy=false;
        }
        boolean vulvao = true;
        boolean syph = true;
        if(((RadioButton)findViewById(R.id.noSyph)).isChecked()){
            syph=false;
        }
        boolean pelvicInflam = true;
        if(((RadioButton)findViewById(R.id.noPelvic)).isChecked()){
            pelvicInflam=false;
        }
        boolean herpes = true;
        if(((RadioButton)findViewById(R.id.noHerp)).isChecked()){
            herpes=false;
        }
        boolean molluscum = true;
        boolean hiv = true;
        if(((RadioButton)findViewById(R.id.noHiv)).isChecked()){
            hiv=false;
        }
        boolean hepB = true;
        if(((RadioButton)findViewById(R.id.noHepB)).isChecked()){
            hepB=false;
        }
        boolean hpv = true;
        if(((RadioButton)findViewById(R.id.noHpv)).isChecked()){
            hpv=false;
        }
        String numDiaSTD = ((EditText)findViewById(R.id.numDiaSTDs)).getText().toString();
        boolean diaCancer = true;
        if(((RadioButton)findViewById(R.id.noCancerDiag)).isChecked()){
            diaCancer=false;
        }
        boolean diaCin = true;
        if(((RadioButton)findViewById(R.id.noCinDiag)).isChecked()){
            diaCin=false;
        }
        boolean diaHpv = true;
        if(((RadioButton)findViewById(R.id.noHpvDiag)).isChecked()){
            diaHpv=false;
        }
        boolean repDiseaseDiag = true;
        if(((RadioButton)findViewById(R.id.noRepDis)).isChecked()){
            repDiseaseDiag=false;
        }

    }
}