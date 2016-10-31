package com.goldducks.splashanimations;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by Maninder Taggar on 26/10/16.
 */

class TerminalAnimation {
    View centerView, parentView;
    View bottomView, topView;
    View bottomViewGreenLine, topViewGreenLine;
    int parentHeight, parentWidth;
    View contentView;

    public TerminalAnimation(Context context) {
        contentView = LayoutInflater.from(context).inflate(R.layout.layout_splash_animation, null);
        if (DrawingMaster.requiresIntialization())
            new DrawingMaster(context);
        DrawingMaster.getInstance().draw(contentView);
    }


    public void start() {


        centerView =

                contentView.findViewById(R.id.centerView);

        parentView =

                contentView.findViewById(R.id.parentView);

        bottomView =

                contentView.findViewById(R.id.bottomView);

        topView =

                contentView.findViewById(R.id.topView);

        bottomViewGreenLine =

                contentView.findViewById(R.id.bottomViewGreenLine);

        topViewGreenLine =

                contentView.findViewById(R.id.topViewGreenLine);

        parentView.getViewTreeObserver().

                addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                                              @Override
                                              public void onGlobalLayout() {
                                                  parentHeight = parentView.getHeight();
                                                  parentWidth = parentView.getWidth();

                                                  setFlapHeights();
                                                  blink(centerView);
                                                  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                                      parentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                                                  } else {
                                                      parentView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                                                  }
                                              }
                                          }

                );

    }

    private void setFlapHeights() {
        ViewGroup.LayoutParams topViewParams = topView.getLayoutParams();
        topViewParams.height = parentHeight / 2;
        topView.setLayoutParams(topViewParams);

        ViewGroup.LayoutParams bottomViewParams = bottomView.getLayoutParams();
        bottomViewParams.height = parentHeight / 2;
        bottomView.setLayoutParams(bottomViewParams);

    }

    private void blink(final View centerView) {
        final boolean[] isVisible = {true};
        final int maxCount = 5;
        final int[] curruntCount = {0};

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (curruntCount[0] == maxCount) {
                    expand(centerView);
                } else {
                    if (isVisible[0])
                        centerView.animate().setDuration(10).alpha(0);
                    else
                        centerView.animate().setDuration(10).alpha(1);
                    isVisible[0] = !isVisible[0];
                    curruntCount[0]++;
                    handler.postDelayed(this, 500);
                }
            }
        }, 500);

    }

    private void expand(final View centerView) {
        centerView.setAlpha(1);
        final ViewGroup.LayoutParams params = centerView.getLayoutParams();
        int curruntWidth = centerView.getWidth();
        int maxWidth = parentView.getWidth();

        ObjectAnimator animator = new ObjectAnimator();
        animator.setDuration(500);
        animator.setIntValues(curruntWidth, maxWidth);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                params.width = (int) animation.getAnimatedValue();
                centerView.setLayoutParams(params);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                openFlaps();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();

    }

    private void openFlaps() {
        topViewGreenLine.setVisibility(View.VISIBLE);
        bottomViewGreenLine.setVisibility(View.VISIBLE);
        ((ViewGroup) centerView.getParent()).removeView(centerView);

        int reduceTo = 0;
        int reduceFrom = parentHeight / 2;

        final ViewGroup.LayoutParams topViewParams = topView.getLayoutParams();
        final ViewGroup.LayoutParams bottomViewParams = bottomView.getLayoutParams();

        ObjectAnimator animator = new ObjectAnimator();
        animator.setDuration(250);
        animator.setIntValues(reduceFrom, reduceTo);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                topViewParams.height = (int) animation.getAnimatedValue();
                bottomViewParams.height = (int) animation.getAnimatedValue();

                topView.setLayoutParams(topViewParams);
                bottomView.setLayoutParams(bottomViewParams);
            }
        });
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();


    }


}
