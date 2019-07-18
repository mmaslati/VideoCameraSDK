package com.mor.example.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class CameraSDK {


    static VideoCameraFragment videoFragment;

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


    // Full Screen
    public static void Start( Activity activity, int videoDurationMili, int frameRate_FPS ){


        Intent intent = new Intent(activity, FullScreenCamera.class);
        intent.putExtra("Duration", videoDurationMili);
        intent.putExtra("FPS", frameRate_FPS);
        activity.startActivity(intent);

    }

    // Full CustomView
    public static void Start( Activity activity, int view ){

        SharedPreferences sp = activity.getSharedPreferences("CameraSDKVars", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        //editor.putString(KEY_NAME, name);
        editor.putInt("duration",0);
        editor.putInt("FPS",0);

        editor.apply();

        if(videoFragment == null){
            videoFragment = VideoCameraFragment.newInstance();
        }

        activity.getFragmentManager().beginTransaction()
                .replace(view, videoFragment)
                .commit();

    }


    public static void takeVideo( Activity activity, int videoDurationMili, int frameRate_FPS ){

        if(videoFragment != null) {

            videoFragment.takeVideo(activity,videoDurationMili,frameRate_FPS);

        }else{
            Toast.makeText(activity,"You must start Camera first",Toast.LENGTH_SHORT);
        }
    }

    public static void End(){

        if(videoFragment == null){
            videoFragment = VideoCameraFragment.newInstance();
        }
        videoFragment.shareVideo();
    }

}
