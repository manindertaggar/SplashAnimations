package com.goldducks.splashAnimations;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

/**
 * Types a piece of text out character by character next to a blinking cursor,
 * then blinks a couple more times before the overlay fades out.
 */
class TypewriterAnimation extends BaseSplashAnimation {

    private static final long CHAR_DELAY = 90;
    private static final long CURSOR_BLINK_DELAY = 350;
    private static final int CURSOR_BLINK_COUNT = 4;
    private static final long FADE_OUT_DURATION = 300;

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final CharSequence text;

    private TextView typewriterText;
    private View cursor;

    TypewriterAnimation(Context context) {
        super(context, R.layout.layout_splash_typewriter);
        this.text = context.getString(R.string.splash_default_typewriter_text);
    }

    @Override
    void start() {
        typewriterText = findViewById(R.id.typewriterText);
        cursor = findViewById(R.id.typewriterCursor);
        typewriterText.setText("");
        typeNextChar(0);
    }

    private void typeNextChar(final int index) {
        if (index >= text.length()) {
            blinkCursor(0, true);
            return;
        }
        typewriterText.setText(text.subSequence(0, index + 1));
        handler.postDelayed(() -> typeNextChar(index + 1), CHAR_DELAY);
    }

    private void blinkCursor(final int count, final boolean visible) {
        if (count >= CURSOR_BLINK_COUNT) {
            fadeOut();
            return;
        }
        cursor.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        handler.postDelayed(() -> blinkCursor(count + 1, !visible), CURSOR_BLINK_DELAY);
    }

    private void fadeOut() {
        contentView.animate()
                .alpha(0f)
                .setDuration(FADE_OUT_DURATION)
                .withEndAction(this::finish)
                .start();
    }
}
