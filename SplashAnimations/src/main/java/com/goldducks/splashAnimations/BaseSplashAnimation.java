package com.goldducks.splashAnimations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Common lifecycle shared by every splash animation style: inflate the animation's
 * overlay layout, draw it on top of the window via {@link DrawingMaster}, run the
 * animation and finally erase the overlay once it completes.
 */
abstract class BaseSplashAnimation {

    final Context context;
    final View contentView;
    private OnSplashAnimationEndListener endListener;

    BaseSplashAnimation(Context context, int layoutRes) {
        this.context = context;
        this.contentView = LayoutInflater.from(context).inflate(layoutRes, null);
        if (DrawingMaster.requiresIntialization()) {
            new DrawingMaster(context);
        }
        DrawingMaster.getInstance().draw(contentView);
    }

    void setOnSplashAnimationEndListener(OnSplashAnimationEndListener listener) {
        this.endListener = listener;
    }

    abstract void start();

    <T extends View> T findViewById(int id) {
        return contentView.findViewById(id);
    }

    void finish() {
        DrawingMaster.getInstance().erase(contentView);
        if (endListener != null) {
            endListener.onSplashAnimationEnd();
        }
    }
}
