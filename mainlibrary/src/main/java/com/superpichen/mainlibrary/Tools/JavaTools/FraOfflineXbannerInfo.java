package com.superpichen.mainlibrary.Tools.JavaTools;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FraOfflineXbannerInfo extends com.stx.xhb.androidx.entity.SimpleBannerInfo{

    private List<Integer> advertiseList=new ArrayList<>();

    public FraOfflineXbannerInfo(List<Integer> advertiseList) {
        this.advertiseList = advertiseList;
    }

    public List<Integer> getAdvertiseList() {
        return advertiseList;
    }

    public void setAdvertiseList(List<Integer> advertiseList) {
        this.advertiseList = advertiseList;
    }

    @Override
    public Object getXBannerUrl() {
        return null;
    }
}
