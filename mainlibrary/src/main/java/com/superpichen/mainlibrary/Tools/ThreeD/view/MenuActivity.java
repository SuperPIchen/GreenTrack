package com.superpichen.mainlibrary.Tools.ThreeD.view;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.superpichen.mainlibrary.R;
import com.superpichen.mainlibrary.Tools.ThreeD.engine.util.android.ContentUtils;

import java.util.HashMap;
import java.util.Map;

public class MenuActivity extends ListActivity {

    /**
     * Load file user data
     */
    private Map<String, Object> loadModelParameters = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        loadModelFromAssets();
    }


    private void loadModelFromAssets() {
        ContentUtils.provideAssets(this);
        launchModelRendererActivity(Uri.parse("assets://" + getPackageName() + "/" + "models/cowboy.dae"));
    }




    private void launchModelRendererActivity(Uri uri) {
        Intent intent = new Intent(getApplicationContext(), ModelActivity.class);
        intent.putExtra("uri", uri.toString());
        intent.putExtra("immersiveMode", "true");

        // content provider case
        if (!loadModelParameters.isEmpty()) {
            intent.putExtra("type", loadModelParameters.get("type").toString());
            loadModelParameters.clear();
        }

        startActivity(intent);
    }
}
