package com.goldducks.splashanimations;

import android.content.Context;
import android.util.Log;

/**
 * Created by Maninder Taggar on 25/10/16.
 */

public final class SplashScreen {
    private static String TAG = "SplashScreen";
    public final static int TERMINAL_ANIMATION = 101;

    public static void show(Context context, int animation) {
        switch (animation) {
            case TERMINAL_ANIMATION:
                new TerminalAnimation(context).start();
                break;
            default:
                Log.e(TAG, "unknown animation");
        }
    }

}
