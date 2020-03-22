package com.superpichen.mainlibrary.Tools.JavaTools;

public class SocialFriendInfo {
    private int paiming;
    private int img;
    private String name;
    private int petCount;
    private double point;

    public SocialFriendInfo() {
    }

    public SocialFriendInfo(int paiming, int img, String name, int petCount, double point) {
        this.paiming = paiming;
        this.img = img;
        this.name = name;
        this.petCount = petCount;
        this.point = point;
    }

    public int getPaiming() {
        return paiming;
    }

    public void setPaiming(int paiming) {
        this.paiming = paiming;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPetCount() {
        return petCount;
    }

    public void setPetCount(int petCount) {
        this.petCount = petCount;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }
}
