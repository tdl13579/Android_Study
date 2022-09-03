package com.android.myapplication.model;

public class MainData {
    private int image;
    private String title;
    private String subTitle;
    private String subTitleTwo;

    public MainData(int image, String title, String subTitle, String subTitleTwo) {
        this.image = image;
        this.title = title;
        this.subTitle = subTitle;
        this.subTitleTwo = subTitleTwo;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSubTitleTwo() {
        return subTitleTwo;
    }

    public void setSubTitleTwo(String subTitleTwo) {
        this.subTitleTwo = subTitleTwo;
    }
}
