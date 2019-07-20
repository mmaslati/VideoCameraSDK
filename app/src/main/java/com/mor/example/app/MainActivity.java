package com.mor.example.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.mor.example.sdk.CameraSDK;

public class MainActivity extends AppCompatActivity {

    // View Variables:
    //******************

    RadioGroup radioGroup;
    Button takeVideoButton;
    Button endButton;

    EditText editTextDuration;
    EditText editTextFPS;


    // Other Variables:
    //******************

    boolean customViewStarted = false;

    final Context context = this;
    Activity thisActivity = this;

    int videoDuration = 0;
    int frameRate_FPS = 0;

    int selectedPreviewType = 0;



    // Simply hides keyboard if open.
    private void hideKeyboard() {

        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = this.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    // Radio buttons handler.
    public void radioButtonsHandler(View v){

        selectedPreviewType = radioGroup.getCheckedRadioButtonId();

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

    // Checking if Video Duration & Frames-Per-Seconds numbers entered.
    private boolean wasDurationAndFpsEntered(){

        String durationText = editTextDuration.getText().toString();
        String fpsText = editTextFPS.getText().toString();

        boolean wasIt = (!durationText.equals("")
                || !fpsText.equals(""));
        return wasIt;
    }

    // User selected to Start the video in full screen.
    private void startFullscreen(){

        CameraSDK.Start(thisActivity, videoDuration, frameRate_FPS);
    }

    // User selected to Start the video in current activity.
    private void startCustomView(){

        customViewStarted = true;

        CameraSDK.Start( thisActivity, R.id.container );
    }

    // Start Button click handler.
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

    // Take Video Button click handler.
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

    // End Button click handler.
    public void onEndClick(View v){

        hideKeyboard();

        if (!customViewStarted){
            Toast.makeText(thisActivity,"You must start camera first",Toast.LENGTH_SHORT).show();
        }else {
            CameraSDK.End(thisActivity);

        }

    }

    // Take Video for CustomView.
    private void takeVideo(){

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

        // Initializing Views.
        radioGroup          = (RadioGroup) findViewById(R.id.radioGroup);
        takeVideoButton     = (Button) findViewById(R.id.takeVideo);
        endButton           = (Button) findViewById(R.id.end);
        editTextDuration    = (EditText) findViewById(R.id.editTextDuration);
        editTextFPS         = (EditText) findViewById(R.id.editTextFPS);
    }
}
