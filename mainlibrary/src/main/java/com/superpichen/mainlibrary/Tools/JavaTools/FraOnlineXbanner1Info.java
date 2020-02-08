package com.superpichen.mainlibrary.Tools.JavaTools;

/**
 *
 */
public class FraOnlineXbanner1Info extends com.stx.xhb.androidx.entity.SimpleBannerInfo{
    private String tag;
    private String title;
    private String Extratext;
    private int Img;

    public FraOnlineXbanner1Info() {
    }

    public FraOnlineXbanner1Info(String tag, String title, String extratext, int img) {
        this.tag = tag;
        this.title = title;
        Extratext = extratext;
        Img = img;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExtratext() {
        return Extratext;
    }

    public void setExtratext(String extratext) {
        Extratext = extratext;
    }

    public int getImg() {
        return Img;
    }

    public void setImg(int img) {
        Img = img;
    }

    @Override
    public Object getXBannerUrl() {
        return getImg();
    }
}
