package com.example.muslimahdaily;

class Surah{
    private String mText;
    private int  mNomor;


    public Surah(String text, int nomor) {

        mText = text;
        mNomor = nomor;

    }



    public String getText(){
        return mText;
    }
    public int getNomor() {
        return mNomor;
    }


}

