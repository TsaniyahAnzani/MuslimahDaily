package com.example.muslimahdaily;

class Alquran {
    private String mArti;
    private String mAsma;
    private int  mAyat;
    private String mNama;
    private int mNomor;
    private String mType;


    public Alquran(String arti, String asma, int ayat, String nama,int nomor,  String type) {
        mArti = arti;
        mAsma = asma;
        mAyat = ayat;
        mNama = nama;
        mNomor = nomor;
        mType = type;


    }

    public String getArti(){
        return mArti;
    }

    public String getAsma(){
        return mAsma;
    }
    public int getAyat() {
        return mAyat;
    }

    public String getNama() {
        return mNama;
    }

    public int getNomor(){
        return mNomor;
    }


    public String getType() {
        return mType;
    }



}

