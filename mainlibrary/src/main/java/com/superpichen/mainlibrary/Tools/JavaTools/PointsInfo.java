package com.superpichen.mainlibrary.Tools.JavaTools;


public class PointsInfo {
    private double count;
    private String type;
    private String date;
    private boolean isReceive;

    public PointsInfo() {
    }

    public PointsInfo(double count, String type, String date, boolean isReceive) {
        this.count = count;
        this.type = type;
        this.date = date;
        this.isReceive = isReceive;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isReceive() {
        return isReceive;
    }

    public void setReceive(boolean receive) {
        isReceive = receive;
    }
}
