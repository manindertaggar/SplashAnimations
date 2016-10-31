Automated Splash Screen for android apps.

# Download

Grab the latest via Gradle</br>

## `compile 'com.goldducks:splashanimations:0.0.1'`

# Usage
in onCreate method of your very first activity

```java
@Override
    protected void onCreate(Bundle savedInstanceState) {
        //add this Line 
        SplashScreen.show(this, SplashScreen.TERMINAL_ANIMATION);
        setContentView(R.layout.layout_content_view);
    
