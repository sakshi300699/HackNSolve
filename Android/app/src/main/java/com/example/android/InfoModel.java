package com.example.android;

public class InfoModel {

    String nameDoc, namePat, uidDoc, uidPat;

    public InfoModel(String nameDoc, String namePat, String uidDoc, String uidPat) {
        this.nameDoc = nameDoc;
        this.namePat = namePat;
        this.uidDoc = uidDoc;
        this.uidPat = uidPat;
    }

    public InfoModel() {
    }

    public String getNameDoc() {
        return nameDoc;
    }

    public void setNameDoc(String nameDoc) {
        this.nameDoc = nameDoc;
    }

    public String getNamePat() {
        return namePat;
    }

    public void setNamePat(String namePat) {
        this.namePat = namePat;
    }

    public String getUidDoc() {
        return uidDoc;
    }

    public void setUidDoc(String uidDoc) {
        this.uidDoc = uidDoc;
    }

    public String getUidPat() {
        return uidPat;
    }

    public void setUidPat(String uidPat) {
        this.uidPat = uidPat;
    }
}
