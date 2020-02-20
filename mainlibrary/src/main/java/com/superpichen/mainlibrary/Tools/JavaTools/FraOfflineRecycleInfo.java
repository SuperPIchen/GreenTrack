package com.superpichen.mainlibrary.Tools.JavaTools;

public class FraOfflineRecycleInfo{
    private int img;
    private String title;
    private String local;

    public FraOfflineRecycleInfo(int img, String title, String local) {
        this.img = img;
        this.title = title;
        this.local = local;
    }

    public FraOfflineRecycleInfo() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
