package com.superpichen.mainlibrary.Tools.JavaTools;

/**
 *
 */
public class FraOfflineXbannerAdvertiseInfo extends com.stx.xhb.androidx.entity.SimpleBannerInfo{
    private int img;

    public FraOfflineXbannerAdvertiseInfo(int img) {
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    @Override
    public Object getXBannerUrl() {
        return getImg();
    }
}
