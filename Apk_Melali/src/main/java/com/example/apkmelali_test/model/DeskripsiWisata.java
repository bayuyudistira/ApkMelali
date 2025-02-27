package com.example.apkmelali_test.model;

public class DeskripsiWisata {
    private String judulWisata;
    private String deskripsiWisata;
    private int imageResId; // resource ID for the image

    public DeskripsiWisata(String judulWisata, String deskripsiWisata, int imageResId) {
        this.judulWisata = judulWisata;
        this.deskripsiWisata = deskripsiWisata;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return judulWisata;
    }

    public String getDescription() {
        return deskripsiWisata;
    }

    public int getImageResId() {
        return imageResId;
    }
}
