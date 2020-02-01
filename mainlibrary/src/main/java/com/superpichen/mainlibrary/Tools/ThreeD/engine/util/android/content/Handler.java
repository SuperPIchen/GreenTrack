package com.superpichen.mainlibrary.Tools.ThreeD.engine.util.android.content;

import com.superpichen.study3d.engine.util.android.AndroidURLConnection;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * Android's content url handler
 */
public class Handler extends URLStreamHandler {

    @Override
    protected URLConnection openConnection(final URL url) {
        return new AndroidURLConnection(url);
    }

}