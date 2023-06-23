package com.example.myapplication;

public class DataClass {

    private String dataTitle;
    private String dataDesc;
    private String dataBudg;
    private String dataImage;
    private String key;

    private String dataDate;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public String getDataBudg() {
        return dataBudg;
    }

    public String getDataImage() {
        return dataImage;
    }
    public String getDataDate() {
        return dataDate;
    }

    public DataClass(String dataTitle, String dataDesc, String dataBudg, String dataImage, String dataDate) {
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataBudg = dataBudg;
        this.dataImage = dataImage;
        this.dataDate = dataDate;
    }
    public DataClass(){

    }
}