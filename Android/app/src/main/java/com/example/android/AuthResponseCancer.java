package com.example.android;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AuthResponseCancer implements Serializable {

    @SerializedName("Hinselmann")
    @Expose
    int Hinselmann;

    @SerializedName("Cytology")
    @Expose
    int Cytology;

    @SerializedName("Schiller")
    @Expose
    int Schiller;

    @SerializedName("Biopsy")
    @Expose
    int Biopsy;

    public int getHinselmann() {
        return Hinselmann;
    }

    public void setHinselmann(int hinselmann) {
        Hinselmann = hinselmann;
    }

    public int getCytology() {
        return Cytology;
    }

    public void setCytology(int cytology) {
        Cytology = cytology;
    }

    public int getSchiller() {
        return Schiller;
    }

    public void setSchiller(int schiller) {
        Schiller = schiller;
    }

    public int getBiopsy() {
        return Biopsy;
    }

    public void setBiopsy(int biopsy) {
        Biopsy = biopsy;
    }



}
