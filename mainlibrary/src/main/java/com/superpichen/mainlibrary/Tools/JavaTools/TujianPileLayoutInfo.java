package com.superpichen.mainlibrary.Tools.JavaTools;

public class TujianPileLayoutInfo {
    private String name;
    private String id;
    private int img;

    public TujianPileLayoutInfo(String name, String id, int img) {
        this.name = name;
        this.id = id;
        this.img = img;
    }

    public TujianPileLayoutInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
