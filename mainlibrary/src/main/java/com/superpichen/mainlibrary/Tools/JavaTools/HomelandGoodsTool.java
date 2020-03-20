package com.superpichen.mainlibrary.Tools.JavaTools;

public class HomelandGoodsTool {
    private int img;
    private String name;
    private double add;
    private String type;
    private int index;

    public HomelandGoodsTool() {
    }

    public HomelandGoodsTool(int img, String name, double add, String type, int index) {
        this.img = img;
        this.name = name;
        this.add = add;
        this.type = type;
        this.index = index;
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

    public double getAdd() {
        return add;
    }

    public void setAdd(double add) {
        this.add = add;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
