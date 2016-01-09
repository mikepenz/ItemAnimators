package com.mikepenz.itemanimators;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.View;

/**
 * Created by mikepenz on 08.01.16.
 */
public class SlideUpAlphaAnimator extends DefaultAnimator<SlideUpAlphaAnimator> {
    @Override
    public void addAnimationPrepare(View view) {
        ViewCompat.setTranslationY(view, view.getHeight());
        ViewCompat.setAlpha(view, 0);
    }

    @Override
    public ViewPropertyAnimatorCompat addAnimation(View view) {
        return ViewCompat.animate(view).translationY(0).alpha(1).setDuration(getMoveDuration());
    }

    @Override
    public void addAnimationCleanup(View view) {
        ViewCompat.setTranslationY(view, 0);
        ViewCompat.setAlpha(view, 1);
    }

    @Override
    public long getAddDelay(long remove, long move, long change) {
        return 0;
    }

    @Override
    public long getRemoveDelay(long remove, long move, long change) {
        return 0;
    }

    @Override
    public ViewPropertyAnimatorCompat removeAnimation(View view) {
        final ViewPropertyAnimatorCompat animation = ViewCompat.animate(view);
        return animation.setDuration(getMoveDuration()).alpha(0).translationY(view.getHeight());
    }

    @Override
    public void removeAnimationCleanup(View view) {
        ViewCompat.setTranslationY(view, 0);
        ViewCompat.setAlpha(view, 1);
    }
}
