package com.goldducks.test;

import android.app.Activity;
import android.os.Bundle;

import com.goldducks.splashAnimations.SplashScreen;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.show(this, SplashScreen.TERMINAL_ANIMATION);

        setContentView(R.layout.layout_content_view);
    }
}
