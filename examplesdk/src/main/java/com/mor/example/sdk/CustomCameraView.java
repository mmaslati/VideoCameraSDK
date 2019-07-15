package com.mor.example.sdk;

import android.content.Context;
import android.widget.FrameLayout;

public class CustomCameraView extends FrameLayout {

    private void initialize(Context context){

        inflate(context, R.layout.custom_camera_layout, this);
    }


    public CustomCameraView(Context context) {
        super(context);

        initialize( context );
    }
}
