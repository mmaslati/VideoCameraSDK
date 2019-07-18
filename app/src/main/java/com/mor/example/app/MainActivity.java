package com.mor.example.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mor.example.sdk.CameraSDK;

import com.mor.example.sdk.FullScreenCamera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    final Context context = this;
    Activity thisActivity = this;

    int videoDuration = 6000;
    int frameRate_FPS = 20;

    public void fullScreenSelected (){

        CameraSDK.startFullScreen( context, videoDuration, frameRate_FPS);

    }

    public void customActivitySelected(){

        Intent intent = new Intent(context, CustomViewActivity.class);

        intent.putExtra("Duration", videoDuration);
        intent.putExtra("FPS", frameRate_FPS);

        startActivity(intent);
    }


    public void startFullscreen(){

        CameraSDK.Start(thisActivity);
    }

    public void startCustomView(){

        CameraSDK.Start( thisActivity, R.id.container );
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Mor","Run SDK Here...");


                //fullScreenSelected ();
                //customActivitySelected();

                startCustomView();
            }
        });
    }


}
