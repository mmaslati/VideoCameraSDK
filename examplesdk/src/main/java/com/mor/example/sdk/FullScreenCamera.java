package com.mor.example.sdk;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;




public class FullScreenCamera extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = getSharedPreferences("CameraSDKVars", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        Intent intent   = getIntent();
        editor.putInt("duration"    ,intent.getIntExtra("Duration", 5000));
        editor.putInt("FPS"         ,intent.getIntExtra("FPS", 24));

        editor.apply();

        setContentView(R.layout.activity_camera);

        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, VideoCameraFragment.newInstance())
                    .commit();


        }
    }
}
