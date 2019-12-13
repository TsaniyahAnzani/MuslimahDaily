package com.example.muslimahdaily;

class Asmaul {
    private String mArti;
    private String mAsma;
    private int  mAyat;
    private String mNama;
//    private int mNomor;
//    private String mType;
//    private Object mAyahs;


    public Asmaul(String arti, String asma, int ayat, String nama) {
        mArti = arti;
        mAsma = asma;
        mAyat = ayat;
        mNama = nama;
//        mNomor = nomor;
//        mType = type;
//        mAyahs=  ayahs;

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
//
//    public int getNomor(){
//        return mNomor;
//    }
//
//
//    public String getType() {
//        return mType;
//    }
//    public Object getAyahs() {
//        return mAyahs;
//    }
//


}

