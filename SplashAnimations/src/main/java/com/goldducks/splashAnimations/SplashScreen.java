package com.goldducks.splashAnimations;

import android.content.Context;
import android.util.Log;

/**
 * Created by Maninder Taggar on 25/10/16.
 */

public final class SplashScreen {
    private static final String TAG = "SplashScreen";

    public static final int TERMINAL_ANIMATION = 101;
    public static final int FADE_ANIMATION = 102;
    public static final int ZOOM_ANIMATION = 103;
    public static final int SLIDE_UP_ANIMATION = 104;
    public static final int CURTAIN_ANIMATION = 105;
    public static final int CIRCULAR_REVEAL_ANIMATION = 106;
    public static final int PULSE_ANIMATION = 107;
    public static final int ROTATE_LOADER_ANIMATION = 108;
    public static final int BOUNCE_DOTS_ANIMATION = 109;
    public static final int TYPEWRITER_ANIMATION = 110;
    public static final int GRADIENT_WAVE_ANIMATION = 111;
    public static final int RIPPLE_ANIMATION = 112;

    private SplashScreen() {
    }

    public static void show(Context context, int animation) {
        show(context, animation, null);
    }

    public static void show(Context context, int animation, OnSplashAnimationEndListener listener) {
        BaseSplashAnimation splashAnimation = createAnimation(context, animation);
        if (splashAnimation == null) {
            Log.e(TAG, "unknown animation");
            return;
        }
        splashAnimation.setOnSplashAnimationEndListener(listener);
        splashAnimation.start();
    }

    private static BaseSplashAnimation createAnimation(Context context, int animation) {
        switch (animation) {
            case TERMINAL_ANIMATION:
                return new TerminalAnimation(context);
            case FADE_ANIMATION:
                return new FadeAnimation(context);
            case ZOOM_ANIMATION:
                return new ZoomAnimation(context);
            case SLIDE_UP_ANIMATION:
                return new SlideUpAnimation(context);
            case CURTAIN_ANIMATION:
                return new CurtainAnimation(context);
            case CIRCULAR_REVEAL_ANIMATION:
                return new CircularRevealAnimation(context);
            case PULSE_ANIMATION:
                return new PulseAnimation(context);
            case ROTATE_LOADER_ANIMATION:
                return new RotateLoaderAnimation(context);
            case BOUNCE_DOTS_ANIMATION:
                return new BounceDotsAnimation(context);
            case TYPEWRITER_ANIMATION:
                return new TypewriterAnimation(context);
            case GRADIENT_WAVE_ANIMATION:
                return new GradientWaveAnimation(context);
            case RIPPLE_ANIMATION:
                return new RippleAnimation(context);
            default:
                return null;
        }
    }
}
