package com.mor.example.sdk;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static android.hardware.Camera.getNumberOfCameras;

public class FullScreenCamera extends Activity {

    Camera camera;

    private boolean safeCameraOpen(int id) {
        boolean qOpened = false;

        try {
            releaseCameraAndPreview();
            camera = Camera.open(id);
            qOpened = (camera != null);
        } catch (Exception e) {
            Log.e(getString(R.string.app_name), "failed to open Camera");
            e.printStackTrace();
        }

        return qOpened;
    }

    private void releaseCameraAndPreview() {
//        preview.setCamera(null);
//        if (camera != null) {
//            camera.release();
//            camera = null;
//        }
    }

    private void startPreview(){

        View v = new CustomCameraView(this);

        setContentView(v);
//
//            TextView textView1 = (TextView)findViewById(R.id.textView1);
//            TextView textView2 = (TextView)findViewById(R.id.textView2);
//
//            textView1.setText("Video Duration in Miliseconds: "+text1);
//            textView2.setText("Video Frame Rate in FPS: "+text2);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int numberOfCameras = getNumberOfCameras();

        //safeCameraOpen(1);
        // First check android version
        if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {

                // Give first an explanation, if needed.
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CAMERA)) {

                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CAMERA},
                            1);
                }


            }
            else{
                boolean started = safeCameraOpen(1);

                if (started){
                    camera.startPreview();
                }

                //startPreview();
            }
        }











    }
}
