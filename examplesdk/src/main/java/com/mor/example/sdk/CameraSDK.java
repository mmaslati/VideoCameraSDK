package com.mor.example.sdk;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

public class CameraSDK {


    public static void startFullScreen(Context context, int videoDurationMili, int frameRate_FPS ){

            Log.d("Mor","Run Full screen");

            Intent intent = new Intent(context, FullScreenCamera.class);

            intent.putExtra("Duration", videoDurationMili);
            intent.putExtra("FPS", frameRate_FPS);

            context.startActivity(intent);


    }


    public static void startCustomView( Activity activity, View view ){

        activity.getFragmentManager().beginTransaction()
                .replace(R.id.container, VideoCameraFragment.newInstance())
                .commit();

    }






}
