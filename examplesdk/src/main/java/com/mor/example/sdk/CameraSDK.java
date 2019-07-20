package com.mor.example.sdk;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class CameraSDK {

    // VideoFragment instance for CustomView.
    static VideoCameraFragment videoFragment;



    // Full Screen Start.
    public static void Start( Activity activity, int videoDurationMili, int frameRate_FPS ){

        if(frameRate_FPS<1 || frameRate_FPS>35){
            Toast.makeText(activity,"Check frame rate number and try again",Toast.LENGTH_LONG).show();
        }else if(videoDurationMili<100 || videoDurationMili>300000){
            Toast.makeText(activity,"Check video duration and try again",Toast.LENGTH_LONG).show();
        }else {
            Intent intent = new Intent(activity, FullScreenCamera.class);
            intent.putExtra("Duration", videoDurationMili);
            intent.putExtra("FPS", frameRate_FPS);
            activity.startActivity(intent);
        }

    }

    // CustomView Start.
    public static void Start( Activity activity, int view ){

        SharedPreferences sp = activity.getSharedPreferences("CameraSDKVars", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

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

    // Take Video for CustomView (in full screen happens automatically).
    public static void takeVideo( Activity activity, int videoDurationMili, int frameRate_FPS ){

        if(videoFragment != null) {

            videoFragment.takeVideo(activity,videoDurationMili,frameRate_FPS);

        }else{
            Toast.makeText(activity,"You must start camera first",Toast.LENGTH_SHORT);
        }
    }

    // End (actually shares and deletes video file) for CustomView (in full screen happens automatically).
    public static void End(Activity activity){

        if(videoFragment == null){
            videoFragment = VideoCameraFragment.newInstance();
        }
        videoFragment.shareVideo();

        activity.getFragmentManager().beginTransaction().remove(videoFragment).commit();

        videoFragment =  null;
    }

}
