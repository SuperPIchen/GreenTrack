package com.superpichen.mainlibrary.Tools.JavaTools;

public class AchievementInfo {
    private String chengjiu;
    private boolean isGet;
    private int type;

    public AchievementInfo(String chengjiu, boolean isGet, int type) {
        this.chengjiu = chengjiu;
        this.isGet = isGet;
        this.type = type;
    }

    public AchievementInfo() {
    }

    public String getChengjiu() {
        return chengjiu;
    }

    public void setChengjiu(String chengjiu) {
        this.chengjiu = chengjiu;
    }

    public boolean isGet() {
        return isGet;
    }

    public void setGet(boolean get) {
        isGet = get;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
