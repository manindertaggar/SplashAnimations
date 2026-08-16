package com.goldducks.splashAnimations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.view.View;

/**
 * Cycles the background through a sequence of colors like a slow gradient wave,
 * then fades the whole overlay out.
 */
class GradientWaveAnimation extends BaseSplashAnimation {

    private static final long CYCLE_DURATION = 550;
    private static final long FADE_OUT_DURATION = 350;

    private final int[] colors = new int[5];

    private View waveBackground;

    GradientWaveAnimation(Context context) {
        super(context, R.layout.layout_splash_gradient_wave);
        colors[0] = colorOf(context, R.color.splash_accent_indigo);
        colors[1] = colorOf(context, R.color.splash_accent_purple);
        colors[2] = colorOf(context, R.color.splash_accent_pink);
        colors[3] = colorOf(context, R.color.splash_accent_blue);
        colors[4] = colorOf(context, R.color.splash_accent_indigo);
    }

    @SuppressWarnings("deprecation")
    private static int colorOf(Context context, int colorRes) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(colorRes);
        }
        return context.getResources().getColor(colorRes);
    }

    @Override
    void start() {
        waveBackground = findViewById(R.id.waveBackground);

        ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), (Object[]) boxedColors());
        colorAnimator.setDuration(CYCLE_DURATION * (colors.length - 1));
        colorAnimator.addUpdateListener(animation ->
                waveBackground.setBackgroundColor((int) animation.getAnimatedValue()));
        colorAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                contentView.animate()
                        .alpha(0f)
                        .setDuration(FADE_OUT_DURATION)
                        .withEndAction(GradientWaveAnimation.this::finish)
                        .start();
            }
        });
        colorAnimator.start();
    }

    private Integer[] boxedColors() {
        Integer[] boxed = new Integer[colors.length];
        for (int i = 0; i < colors.length; i++) {
            boxed[i] = colors[i];
        }
        return boxed;
    }
}
