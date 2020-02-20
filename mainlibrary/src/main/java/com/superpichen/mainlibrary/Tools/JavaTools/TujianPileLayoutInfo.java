package com.superpichen.mainlibrary.Tools.JavaTools;

import android.net.Uri;

public class TujianPileLayoutInfo {
    private String name;
    private String id;
    private int img;
    private Uri uri;

    public TujianPileLayoutInfo(String name, String id, int img,Uri uri) {
        this.name = name;
        this.id = id;
        this.img = img;
        this.uri=uri;
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

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
