package com.mor.example.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class CameraSDK {


    public static void startFullScreen(Context context, int videoDurationMili, int frameRate_FPS ){

            Log.d("Mor","Run Full screen");

            Intent intent = new Intent(context, FullScreenCamera.class);

            intent.putExtra("Duration", videoDurationMili);
            intent.putExtra("FPS", frameRate_FPS);

            context.startActivity(intent);


    }


    public static void startCustomView( int videoDurationMili, int frameRate_FPS, Activity activity, int view ){


        SharedPreferences sp = activity.getSharedPreferences("CameraSDKVars", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        //editor.putString(KEY_NAME, name);
        editor.putInt("duration",videoDurationMili);
        editor.putInt("FPS",frameRate_FPS);

        editor.apply();

        activity.getFragmentManager().beginTransaction()
                .replace(view, VideoCameraFragment.newInstance())
                .commit();

    }






}
