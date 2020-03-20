package com.superpichen.mainlibrary.Tools.JavaTools;

public class HomelandWallInfo {
    private String type;
    private boolean[] isApply;
    private HomelandGoodsTool goods;

    public HomelandWallInfo() {
    }

    public HomelandWallInfo(String type, boolean[] isApply, HomelandGoodsTool goods) {
        this.type = type;
        this.isApply = isApply;
        this.goods = goods;
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

    public HomelandGoodsTool getGoods() {
        return goods;
    }

    public void setGoods(HomelandGoodsTool goods) {
        this.goods = goods;
    }
}
