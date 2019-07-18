
# Example Camera SDK

## Explanation:
This is an example for an SDK I have created, following a task givin to me as part of an application for Android Mobile Development. Here is the task:
```

1. Create SDK with the following:

	a. Start - starts the camera on full screen or on the view given by the developer

	b. takeVideo - takes video of period X seconds and frame rate Y

	c. End - open share option to send the video, after share deletes the video and close the camera view

2. Create minimal sample to demonstrate the use of sample app

3. The code should be uploaded to github

```
## Requirements to using my SDK:
* Minimum Target SDK Version - 21.

## Steps to import SDK to your project:
<b><u> Step 1: Add the following code root build.gradle at the end of repositories: </u></b>

  

```

allprojects {
	repositories {
	...
	maven {
		url 'https://jitpack.io'
		}
	}
}

```

  

  

<b><u>Step 2. Add the dependency (Check for most updated release version. Exaple showing "v0.1")</u></b>

```

dependencies {
	implementation 'com.github.mmaslati:VideoCameraSDK:v0.1'
}

```
That's it.
  
  
## Steps for using the SDK:
<b><u>Option 1 - Full Screen:</u></b>
Call the "startFullScreen" method in the CameraSDK and provide a context, the desired duration of the final video and the frame rate number in Frames Per Seconds.
```
	
//CameraSDK.startFullScreen( Context context, int desiredVideoLengthInMilisec,  int desiredFrameRateInFPS);
CameraSDK.startFullScreen(context,4500,30);

```

  

<b><u>Option 2 - Custom View:</u></b>
Create an Activity with an XML Layout for it. In the XML layout file create a placeholder view item for the camera (make sure to give it an "id" for later). For Example:
```

<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"  
	xmlns:tools="http://schemas.android.com/tools"  
	android:id="@+id/container"  
	android:layout_width="match_parent"  
	android:layout_height="250dp"  
	android:background="#000"  
	tools:context="com.example.android.camera2basic.CameraActivity" />

```
In the onCreate method of the Activity with the desired View. Call the "startCustomView" in the CameraSDK and provide the desired duration of the final video, the frame rate number in Frames Per Seconds, Context (can simpley be "this") and a pointer to the "id" of the previousely created view placeholder.
```

////CameraSDK.startCustomView( int desiredVideoLengthInMilisec,  int desiredFrameRateInFPS, Context context, int viewOfPlaceholder);
CameraSDK.startCustomView( 5000,24,this, R.id.container );

```
That's it.
