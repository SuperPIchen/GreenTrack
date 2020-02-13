package com.superpichen.mainlibrary.Tools.JavaTools;

public class FraOnlineXbanner3Info extends com.stx.xhb.androidx.entity.SimpleBannerInfo{
    private String title;
    private int img;
    private int gif;

    public FraOnlineXbanner3Info() {
    }

    public FraOnlineXbanner3Info(String title, int img, int gif) {
        this.title = title;
        this.img = img;
        this.gif = gif;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getGif() {
        return gif;
    }

    public void setGif(int gif) {
        this.gif = gif;
    }

    @Override
    public Object getXBannerUrl() {
        return null;
    }
}
