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

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;

    boolean customViewStarted = false;

    Button takeVideoButton;
    Button endButton;

    EditText editTextDuration;
    EditText editTextFPS;

    final Context context = this;
    Activity thisActivity = this;

    int videoDuration = 0;
    int frameRate_FPS = 0;

    int selectedPreviewType = 0;

    public void hideKeyboard() {

        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = this.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void radioButtonsHandler(View v){

        selectedPreviewType = radioGroup.getCheckedRadioButtonId();

        //radioButton = findViewById(selectedPreviewType);

        if(findViewById(selectedPreviewType) == findViewById(R.id.fullscreen)){

            takeVideoButton.setAlpha(.5f);
            takeVideoButton.setClickable(false);

            endButton.setAlpha(.5f);
            endButton.setClickable(false);

        }else{

            takeVideoButton.setAlpha(1f);
            takeVideoButton.setClickable(true);

            endButton.setAlpha(1f);
            endButton.setClickable(true);
        }
    }

    public boolean wasDurationAndFpsEntered(){

        String durationText = editTextDuration.getText().toString();
        String fpsText = editTextFPS.getText().toString();

        boolean wasIt = (!durationText.equals("")
                || !fpsText.equals(""));
        return wasIt;
    }

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

        CameraSDK.Start(thisActivity, videoDuration, frameRate_FPS);
    }

    public void startCustomView(){

        customViewStarted = true;

        CameraSDK.Start( thisActivity, R.id.container );
    }

    public void onStartClick(View v){

        hideKeyboard();
        if(
                selectedPreviewType == 0
        ){
            Toast.makeText(context,"You must select View Type", Toast.LENGTH_LONG).show();
        }else{

            if( selectedPreviewType==R.id.fullscreen){

                // START BY CHECKING "VIDEO DURATION" and "FRAME RATE"
                boolean durationAndFpsEntered = wasDurationAndFpsEntered();

                if (!durationAndFpsEntered) {
                    Toast.makeText(context,"Please enter both Duration and FPS", Toast.LENGTH_LONG).show();
                }else{

                    videoDuration = Integer.parseInt(editTextDuration.getText().toString());
                    frameRate_FPS = Integer.parseInt(editTextFPS.getText().toString());

                    startFullscreen();
                }



            }else{

                startCustomView();

            }
        }


    }

    public void onTakeVideoClick(View v){

        hideKeyboard();

        if(!wasDurationAndFpsEntered()){
            Toast.makeText(context,"Insert Duration and Frames Per Sec",Toast.LENGTH_SHORT).show();
        }else{
            if (!customViewStarted){
                Toast.makeText(thisActivity,"You must start camera first",Toast.LENGTH_SHORT).show();
            }else {
                takeVideo();
            }
        }
    }

    public void onEndClick(View v){

        hideKeyboard();

        if (!customViewStarted){
            Toast.makeText(thisActivity,"You must start camera first",Toast.LENGTH_SHORT).show();
        }else {
            CameraSDK.End(thisActivity);

        }

    }


    public void takeVideo(){

        videoDuration = Integer.parseInt(editTextDuration.getText().toString());
        frameRate_FPS = Integer.parseInt(editTextFPS.getText().toString());

        CameraSDK.takeVideo( thisActivity, videoDuration, frameRate_FPS );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        radioGroup          = (RadioGroup) findViewById(R.id.radioGroup);

        takeVideoButton     = (Button) findViewById(R.id.takeVideo);
        endButton           = (Button) findViewById(R.id.end);

        editTextDuration    = (EditText) findViewById(R.id.editTextDuration);
        editTextFPS         = (EditText) findViewById(R.id.editTextFPS);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Mor","Run SDK Here...");


                //fullScreenSelected ();
                //customActivitySelected();

                //startCustomView();

                takeVideo();


                Handler handler = new Handler();
                Runnable runnable = new Runnable(){
                    public void run() {
                        CameraSDK.End(thisActivity);
                    }
                };

                handler.postDelayed(runnable, 8500);
            }
        });




        //startCustomView ();

    }


    /*
     FINISHED:
        //startFullscreen();

         //startCustomView();

     LEFT
         //takeVideo();
         //end


     */





}
