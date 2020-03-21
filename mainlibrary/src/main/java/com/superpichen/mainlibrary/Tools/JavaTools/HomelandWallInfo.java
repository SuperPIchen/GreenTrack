package com.superpichen.mainlibrary.Tools.JavaTools;

import java.util.List;

public class HomelandWallInfo {
    private String type;
    private boolean[] isApply;
    private int[] goodslist;

    public HomelandWallInfo() {
    }

    public HomelandWallInfo(String type, boolean[] isApply, int[] goodslist) {
        this.type = type;
        this.isApply = isApply;
        this.goodslist = goodslist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean[] getIsApply() {
        return isApply;
    }

    public void setIsApply(boolean[] isApply) {
        this.isApply = isApply;
    }

    public int[] getGoodslist() {
        return goodslist;
    }

    public void setGoodslist(int[] goodslist) {
        this.goodslist = goodslist;
    }
}
