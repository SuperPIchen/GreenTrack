package com.superpichen.mainlibrary.Tools.ThreeD.engine.util.android;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

public class AndroidURLStreamHandlerFactory implements URLStreamHandlerFactory {

    @Override
    public URLStreamHandler createURLStreamHandler(String protocol) {
        if ("assets".equals(protocol)) {
            return new com.superpichen.study3d.engine.util.android.assets.Handler();
        } else if ("content".equals(protocol)){
            return new com.superpichen.study3d.engine.util.android.content.Handler();
        }
        return null;
    }
}
