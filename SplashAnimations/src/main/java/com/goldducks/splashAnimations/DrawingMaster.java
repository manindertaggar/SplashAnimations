package com.goldducks.splashAnimations;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.View;
import android.view.WindowManager;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by Maninder Taggar on 31/10/16.
 */

public class DrawingMaster {
    private static DrawingMaster mInstance;
    Context mContext;
    WindowManager.LayoutParams params;

    WindowManager windowManager;

    public DrawingMaster(Context context) {
        if (!DrawingMaster.requiresIntialization())
            return;

        mInstance = this;
        mContext = context;
        windowManager = (WindowManager) mContext.getSystemService(WINDOW_SERVICE);
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT);

    }

    public void draw(View view) {
        windowManager.addView(view, params);
    }

    public void erase(View view) {
        windowManager.removeView(view);
    }

    public static DrawingMaster getInstance() {
        return mInstance;
    }

    public static Boolean requiresIntialization() {
        if (DrawingMaster.getInstance() == null)
            return true;
        return false;
    }
}
