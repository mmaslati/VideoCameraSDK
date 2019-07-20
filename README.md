
  
# Example Camera SDK  
  
## Explanation:  
This is an example for an SDK I have created, following a task given to me as part of an application for Android Mobile Development position. Here is the task:  
```  
  
 1. Create SDK with the following:  
	 a. Start - starts the camera on full screen or on the view given by the developer  
	 b. takeVideo - takes video of period X seconds and frame rate Y  
	 c. End - open share option to send the video, after share deletes the video and close the camera view  
 2. Create minimal sample to demonstrate the use of sample app  
 3. The code should be uploaded to github  

```  
* [For this SDK I chose to make use of the Camera2 SDK here.](https://github.com/googlesamples/android-Camera2Basic)  
* I misunderstood the assignment at first. To view old implementation of this SDK (v0.1), scroll to bottom.
## Requirements to using my SDK:  
* Minimum Target SDK Version - 21.  
  
## Steps to import SDK to your project:  
<b><u> Step 1: Add the following code root build.gradle at the end of repositories: </u></b>  
  
    
  
```  
  
allprojects {  
	repositories { ... maven { url 'https://jitpack.io' } }}  
  
```  
  
    
    
<b><u>Step 2. Add the dependency (Check for most updated release version. Example showing "v0.2")</u></b>  
  
```  
  
dependencies {  
	implementation 'com.github.mmaslati:VideoCameraSDK:v0.2'}  
  
```  
That's it.  
 
 ## Steps for using the SDK:  
<b><u>Option 1 - Full Screen:</u></b>  
Call the "Start" method in the CameraSDK and provide an activity, the desired duration of the final video and the frame rate number in Frames Per Seconds. 
* This starts the whole process, Taking Video -> Sharing -> Deleting File -> Closing. So, the "Start" and "takeVideo"
methods are irrelevant.
* Full Screen that does NOT start a process is still possible using the Custom View option.
```  
	//CameraSDK.Start( Activity activity, int videoDurationMili, int frameRate_FPS );
	CameraSDK.Start(this,4500,30);  
  
```  
  
    
<b><u>Option 2 - Custom View:</u></b>  
Create a placeholder view item for the camera In the XML layout file (make sure to give it an "id" for later). For Example:  
```  
  
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"    
	xmlns:tools="http://schemas.android.com/tools"    
    android:id="@+id/container"    
    android:layout_width="match_parent"    
    android:layout_height="250dp"    
    android:background="#000"    
    tools:context="com.example.android.camera2basic.CameraActivity" />  
  
```  
When ready to start the camera preview, call the "Start" method by passing only the Activity and placeholder View Id.  
```  

	//CameraSDK.Start( Activity activity, int view );  
	CameraSDK.Start( thisActivity, R.id.container );  
  
```  
Now that you have a camera preview running, call the "takeVideo" method, passing your activity, the desired video length in milliseconds and the desired frame rate in FPS, when you are ready to take the video. A few things to remember:
* You should have the Video Duration & FPS before calling "takeVideo".
* Currently, you have to create a flag on wether or not the "Start" was previously called (Will be changed in next version).
```  

	//CameraSDK.takeVideo( Activity activity, int videoDurationMili, int frameRate_FPS );  
	CameraSDK.takeVideo( this, 5000, 30 ); 
  
```  
When the video has finished, you can now call the "End" method, which actually opens the share dialog and deletes the video file, with the activity of where the camera preview was created.
```  

	//CameraSDK.End(Activity activity);  
	CameraSDK.End(this); 
  
```  
That's it.
    
### **OLD VERSION (v0.1) Steps for using the SDK is in the Readme of v0.1.
