package com.goldducks.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.goldducks.splashAnimations.SplashScreen;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.show(this, SplashScreen.TERMINAL_ANIMATION);

        setContentView(R.layout.layout_content_view);
        bindAnimationButtons();
    }

    private void bindAnimationButtons() {
        bind(R.id.buttonTerminal, SplashScreen.TERMINAL_ANIMATION);
        bind(R.id.buttonFade, SplashScreen.FADE_ANIMATION);
        bind(R.id.buttonZoom, SplashScreen.ZOOM_ANIMATION);
        bind(R.id.buttonSlideUp, SplashScreen.SLIDE_UP_ANIMATION);
        bind(R.id.buttonCurtain, SplashScreen.CURTAIN_ANIMATION);
        bind(R.id.buttonCircularReveal, SplashScreen.CIRCULAR_REVEAL_ANIMATION);
        bind(R.id.buttonPulse, SplashScreen.PULSE_ANIMATION);
        bind(R.id.buttonRotateLoader, SplashScreen.ROTATE_LOADER_ANIMATION);
        bind(R.id.buttonBounceDots, SplashScreen.BOUNCE_DOTS_ANIMATION);
        bind(R.id.buttonTypewriter, SplashScreen.TYPEWRITER_ANIMATION);
        bind(R.id.buttonGradientWave, SplashScreen.GRADIENT_WAVE_ANIMATION);
        bind(R.id.buttonRipple, SplashScreen.RIPPLE_ANIMATION);
    }

    private void bind(int buttonId, final int animation) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> SplashScreen.show(MainActivity.this, animation));
    }
}
