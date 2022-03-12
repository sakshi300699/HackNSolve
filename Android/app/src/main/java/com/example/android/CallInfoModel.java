package com.example.android;

public class CallInfoModel {

    String nameDoc, namePat, uidDoc, uidPat, date, time, link;

    public CallInfoModel(String nameDoc, String namePat, String uidDoc, String uidPat, String date, String time, String link) {
        this.nameDoc = nameDoc;
        this.namePat = namePat;
        this.uidDoc = uidDoc;
        this.uidPat = uidPat;
        this.date = date;
        this.time = time;
        this.link = link;
    }

    public CallInfoModel() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
