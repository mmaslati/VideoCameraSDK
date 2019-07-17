package com.mor.example.sdk;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;




public class FullScreenCamera extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent   = getIntent();

        //videoDuration   = intent.getIntExtra("Duration", 5000);
        //videoFrameRate  = intent.getIntExtra("FPS", 24);

        SharedPreferences sp = getSharedPreferences("CameraSDKVars", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        //editor.putString(KEY_NAME, name);
        editor.putInt("duration",intent.getIntExtra("Duration", 5000));
        editor.putInt("FPS",intent.getIntExtra("FPS", 24));

        editor.apply();

        setContentView(R.layout.activity_camera);

        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, VideoCameraFragment.newInstance())
                    .commit();


        }
        /*
        final Activity a = this;

        Handler handler = new Handler();
        Runnable runnable = new Runnable(){
            public void run() {
                //TESTING DO SOMETHING
            }
        };

        handler.postDelayed(runnable, 5000);
        */
    }
}
