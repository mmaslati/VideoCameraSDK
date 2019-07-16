package com.mor.example.sdk;
import android.app.Activity;
import android.os.Bundle;




public class FullScreenCamera extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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
