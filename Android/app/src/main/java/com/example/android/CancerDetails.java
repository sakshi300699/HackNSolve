package com.example.android;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CancerDetails implements Serializable {

    @SerializedName("age")
    @Expose
    String age;

    @SerializedName("number_of_sexual_partners")
    @Expose
    String number_of_sexual_partners;

    @SerializedName("age_of_first_sexual_intercourse")
    @Expose
    String age_of_first_sexual_intercourse;

    @SerializedName("number_of_pregnancies")
    @Expose
    String number_of_pregnancies;

    @SerializedName("smokes")
    @Expose
    String smokes;

    @SerializedName("smokes_years")
    @Expose
    String smokes_years;

    @SerializedName("smokes_packs_per_year")
    @Expose
    String smokes_packs_per_year;

    @SerializedName("hormonal_contraceptives")
    @Expose
    String hormonal_contraceptives;

    @SerializedName("hormonal_contraceptives_years")
    @Expose
    String hormonal_contraceptives_years;

    @SerializedName("IUD")
    @Expose
    String IUD;

    @SerializedName("IUD_years")
    @Expose
    String IUD_years;

    @SerializedName("STDs")
    @Expose
    String STDs;

    @SerializedName("STDs_number")
    @Expose
    String STDs_number;

    @SerializedName("STDs_condylomatosis")
    @Expose
    String STDs_condylomatosis;

    @SerializedName("STDs_vaginal_condylomatosis")
    @Expose
    String STDs_vaginal_condylomatosis;

    @SerializedName("STDs_vulvo_perineal_condylomatosis")
    @Expose
    String STDs_vulvo_perineal_condylomatosis;

    @SerializedName("STDs_syphilis")
    @Expose
    String STDs_syphilis;

    @SerializedName("STDs_pelvic_inflammatory_disease_")
    @Expose
    String STDs_pelvic_inflammatory_disease_;

    @SerializedName("STDs_genital_herpes")
    @Expose
    String STDs_genital_herpes;

    @SerializedName("STDs_molluscum_contagiosum")
    @Expose
    String STDs_molluscum_contagiosum;

    @SerializedName("STDs_HIV")
    @Expose
    String STDs_HIV;

    @SerializedName("STDs_HepatitisB")
    @Expose
    String STDs_HepatitisB;

    @SerializedName("STDs_HPV")
    @Expose
    String STDs_HPV;

    @SerializedName("STDs_Number_of_diagnosis")
    @Expose
    String STDs_Number_of_diagnosis;

    @SerializedName("Dx_Cancer")
    @Expose
    String Dx_Cancer;

    @SerializedName("Dx_CIN")
    @Expose
    String Dx_CIN;

    @SerializedName("Dx_HPV")
    @Expose
    String Dx_HPV;

    @SerializedName("Dx")
    @Expose
    String Dx;

    public CancerDetails(String age, String number_of_sexual_partners, String age_of_first_sexual_intercourse, String number_of_pregnancies, String smokes, String smokes_years, String smokes_packs_per_year, String hormonal_contraceptives, String hormonal_contraceptives_years, String IUD, String IUD_years, String STDs, String STDs_number, String STDs_condylomatosis, String STDs_vaginal_condylomatosis, String STDs_vulvo_perineal_condylomatosis, String STDs_syphilis, String STDs_pelvic_inflammatory_disease_, String STDs_genital_herpes, String STDs_molluscum_contagiosum, String STDs_HIV, String STDs_HepatitisB, String STDs_HPV, String STDs_Number_of_diagnosis, String dx_Cancer, String dx_CIN, String dx_HPV, String dx) {
        this.age = age;
        this.number_of_sexual_partners = number_of_sexual_partners;
        this.age_of_first_sexual_intercourse = age_of_first_sexual_intercourse;
        this.number_of_pregnancies = number_of_pregnancies;
        this.smokes = smokes;
        this.smokes_years = smokes_years;
        this.smokes_packs_per_year = smokes_packs_per_year;
        this.hormonal_contraceptives = hormonal_contraceptives;
        this.hormonal_contraceptives_years = hormonal_contraceptives_years;
        this.IUD = IUD;
        this.IUD_years = IUD_years;
        this.STDs = STDs;
        this.STDs_number = STDs_number;
        this.STDs_condylomatosis = STDs_condylomatosis;
        this.STDs_vaginal_condylomatosis = STDs_vaginal_condylomatosis;
        this.STDs_vulvo_perineal_condylomatosis = STDs_vulvo_perineal_condylomatosis;
        this.STDs_syphilis = STDs_syphilis;
        this.STDs_pelvic_inflammatory_disease_ = STDs_pelvic_inflammatory_disease_;
        this.STDs_genital_herpes = STDs_genital_herpes;
        this.STDs_molluscum_contagiosum = STDs_molluscum_contagiosum;
        this.STDs_HIV = STDs_HIV;
        this.STDs_HepatitisB = STDs_HepatitisB;
        this.STDs_HPV = STDs_HPV;
        this.STDs_Number_of_diagnosis = STDs_Number_of_diagnosis;
        Dx_Cancer = dx_Cancer;
        Dx_CIN = dx_CIN;
        Dx_HPV = dx_HPV;
        Dx = dx;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNumber_of_sexual_partners() {
        return number_of_sexual_partners;
    }

    public void setNumber_of_sexual_partners(String number_of_sexual_partners) {
        this.number_of_sexual_partners = number_of_sexual_partners;
    }

    public String getAge_of_first_sexual_intercourse() {
        return age_of_first_sexual_intercourse;
    }

    public void setAge_of_first_sexual_intercourse(String age_of_first_sexual_intercourse) {
        this.age_of_first_sexual_intercourse = age_of_first_sexual_intercourse;
    }

    public String getNumber_of_pregnancies() {
        return number_of_pregnancies;
    }

    public void setNumber_of_pregnancies(String number_of_pregnancies) {
        this.number_of_pregnancies = number_of_pregnancies;
    }

    public String getSmokes() {
        return smokes;
    }

    public void setSmokes(String smokes) {
        this.smokes = smokes;
    }

    public String getSmokes_years() {
        return smokes_years;
    }

    public void setSmokes_years(String smokes_years) {
        this.smokes_years = smokes_years;
    }

    public String getSmokes_packs_per_year() {
        return smokes_packs_per_year;
    }

    public void setSmokes_packs_per_year(String smokes_packs_per_year) {
        this.smokes_packs_per_year = smokes_packs_per_year;
    }

    public String getHormonal_contraceptives() {
        return hormonal_contraceptives;
    }

    public void setHormonal_contraceptives(String hormonal_contraceptives) {
        this.hormonal_contraceptives = hormonal_contraceptives;
    }

    public String getHormonal_contraceptives_years() {
        return hormonal_contraceptives_years;
    }

    public void setHormonal_contraceptives_years(String hormonal_contraceptives_years) {
        this.hormonal_contraceptives_years = hormonal_contraceptives_years;
    }

    public String getIUD() {
        return IUD;
    }

    public void setIUD(String IUD) {
        this.IUD = IUD;
    }

    public String getIUD_years() {
        return IUD_years;
    }

    public void setIUD_years(String IUD_years) {
        this.IUD_years = IUD_years;
    }

    public String getSTDs() {
        return STDs;
    }

    public void setSTDs(String STDs) {
        this.STDs = STDs;
    }

    public String getSTDs_number() {
        return STDs_number;
    }

    public void setSTDs_number(String STDs_number) {
        this.STDs_number = STDs_number;
    }

    public String getSTDs_condylomatosis() {
        return STDs_condylomatosis;
    }

    public void setSTDs_condylomatosis(String STDs_condylomatosis) {
        this.STDs_condylomatosis = STDs_condylomatosis;
    }

    public String getSTDs_vaginal_condylomatosis() {
        return STDs_vaginal_condylomatosis;
    }

    public void setSTDs_vaginal_condylomatosis(String STDs_vaginal_condylomatosis) {
        this.STDs_vaginal_condylomatosis = STDs_vaginal_condylomatosis;
    }

    public String getSTDs_vulvo_perineal_condylomatosis() {
        return STDs_vulvo_perineal_condylomatosis;
    }

    public void setSTDs_vulvo_perineal_condylomatosis(String STDs_vulvo_perineal_condylomatosis) {
        this.STDs_vulvo_perineal_condylomatosis = STDs_vulvo_perineal_condylomatosis;
    }

    public String getSTDs_syphilis() {
        return STDs_syphilis;
    }

    public void setSTDs_syphilis(String STDs_syphilis) {
        this.STDs_syphilis = STDs_syphilis;
    }

    public String getSTDs_pelvic_inflammatory_disease_() {
        return STDs_pelvic_inflammatory_disease_;
    }

    public void setSTDs_pelvic_inflammatory_disease_(String STDs_pelvic_inflammatory_disease_) {
        this.STDs_pelvic_inflammatory_disease_ = STDs_pelvic_inflammatory_disease_;
    }

    public String getSTDs_genital_herpes() {
        return STDs_genital_herpes;
    }

    public void setSTDs_genital_herpes(String STDs_genital_herpes) {
        this.STDs_genital_herpes = STDs_genital_herpes;
    }

    public String getSTDs_molluscum_contagiosum() {
        return STDs_molluscum_contagiosum;
    }

    public void setSTDs_molluscum_contagiosum(String STDs_molluscum_contagiosum) {
        this.STDs_molluscum_contagiosum = STDs_molluscum_contagiosum;
    }

    public String getSTDs_HIV() {
        return STDs_HIV;
    }

    public void setSTDs_HIV(String STDs_HIV) {
        this.STDs_HIV = STDs_HIV;
    }

    public String getSTDs_HepatitisB() {
        return STDs_HepatitisB;
    }

    public void setSTDs_HepatitisB(String STDs_HepatitisB) {
        this.STDs_HepatitisB = STDs_HepatitisB;
    }

    public String getSTDs_HPV() {
        return STDs_HPV;
    }

    public void setSTDs_HPV(String STDs_HPV) {
        this.STDs_HPV = STDs_HPV;
    }

    public String getSTDs_Number_of_diagnosis() {
        return STDs_Number_of_diagnosis;
    }

    public void setSTDs_Number_of_diagnosis(String STDs_Number_of_diagnosis) {
        this.STDs_Number_of_diagnosis = STDs_Number_of_diagnosis;
    }

    public String getDx_Cancer() {
        return Dx_Cancer;
    }

    public void setDx_Cancer(String dx_Cancer) {
        Dx_Cancer = dx_Cancer;
    }

    public String getDx_CIN() {
        return Dx_CIN;
    }

    public void setDx_CIN(String dx_CIN) {
        Dx_CIN = dx_CIN;
    }

    public String getDx_HPV() {
        return Dx_HPV;
    }

    public void setDx_HPV(String dx_HPV) {
        Dx_HPV = dx_HPV;
    }

    public String getDx() {
        return Dx;
    }

    public void setDx(String dx) {
        Dx = dx;
    }
}
