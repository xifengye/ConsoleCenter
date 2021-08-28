package com.cptp.console;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SeekBar;

public class MySeekBar extends SeekBar {

    private Animation scaleToLargeAnimation = null;

    private int animationDuration = 100;
    private int animationDelay = 1;

    public MySeekBar(Context context) {
        super(context);
        // init Animation
        initAnimation();

    }

    public MySeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        // init Animation
        initAnimation();
    }

    public MySeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // init Animation
        initAnimation();
    }

    private void initAnimation() {

        scaleToLargeAnimation = AnimationUtils.loadAnimation(this.getContext(),
                R.anim.scale_to_large);

    }

    @Override
    protected void onFocusChanged(boolean focused, int direction,
                                  Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);

        if (focused) {
            scaleToLarge();
        } else {
            this.clearAnimation();
        }

    }

    private void scaleToLarge() {
        if (animationDuration > 0) {
            clearAnimation();
            scaleToLargeAnimation.setStartTime(AnimationUtils
                    .currentAnimationTimeMillis() + animationDelay);
            setAnimation(scaleToLargeAnimation);
        }
    }
}