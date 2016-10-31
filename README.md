Automated Splash Screen for android apps.

Note:   This project is under development.
        You can help dev. in bug fixes and with your awesome ideas for Universal. SplashScreens

### Download

Grab the latest via Gradle</br>

 `compile 'com.goldducks:splashanimations:0.0.1'`

### Usage
in onCreate method of your very first activity

```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //add this Line 
        SplashScreen.show(this, SplashScreen.TERMINAL_ANIMATION);
        setContentView(R.layout.layout_content_view);
   ...
   ```
   That's it.
   You are all done.
   Now everytime this activity runs, a Splash Screen will run.
   
## Upcoming Features:
 -Set Duration for Splash Screen.</br>
 -Passing any custom layout to set it as Splash Screen.</br>
 -SplashScreen animation over start and stop Listener.</br>
 -Custom stoping of SplashScreen.</br>
 -and ofcourseMany Many splash animations.</br>
 
