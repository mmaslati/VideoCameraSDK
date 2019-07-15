package com.mor.example.sdk;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

public class CameraSDK {


    public static void init(Context context, int videoDurationMili, int frameRate_FPS, View customView ){

        if( customView == null){

            Log.d("Mor","Run Full screen");

            Intent intent = new Intent(context, FullScreenCamera.class);

            intent.putExtra("Text_1", String.valueOf(videoDurationMili));
            intent.putExtra("Text_2", String.valueOf(frameRate_FPS));

            context.startActivity(intent);



        }else{


            Log.d("Mor","Run CustomView");
        }

    }









}
