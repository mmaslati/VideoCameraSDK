package com.mor.example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mor.example.sdk.CameraSDK;

public class CustomViewActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.custom_camera_layout);

        Intent intent   = getIntent();

        CameraSDK.startCustomView( intent.getIntExtra("Duration", 5000),intent.getIntExtra("FPS", 24),this, R.id.container );


    }
}
