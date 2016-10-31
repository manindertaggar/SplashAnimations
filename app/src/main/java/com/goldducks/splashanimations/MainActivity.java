package com.goldducks.splashanimations;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.show(this, SplashScreen.TERMINAL_ANIMATION);

        setContentView(R.layout.activity_main);
    }
}
