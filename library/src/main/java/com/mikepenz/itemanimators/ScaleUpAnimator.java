package com.mikepenz.itemanimators;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.View;

/**
 * Created by mikepenz on 08.01.16.
 */
public class ScaleUpAnimator extends BaseScaleAnimator<ScaleUpAnimator> {
    public void addAnimationPrepare(View view) {
        ViewCompat.setScaleX(view, 0);
        ViewCompat.setScaleY(view, 0);
    }

    public ViewPropertyAnimatorCompat addAnimation(View view) {
        return ViewCompat.animate(view).scaleX(1).scaleY(1).setDuration(getAddDuration()).setInterpolator(getInterpolator());
    }

    public void addAnimationCleanup(View view) {
        ViewCompat.setScaleX(view, 1);
        ViewCompat.setScaleY(view, 1);
    }


    public ViewPropertyAnimatorCompat removeAnimation(View view) {
        return ViewCompat.animate(view).setDuration(getRemoveDuration()).scaleX(0).scaleY(0).setInterpolator(getInterpolator());
    }

    public void removeAnimationCleanup(View view) {
        ViewCompat.setScaleX(view, 1);
        ViewCompat.setScaleY(view, 1);
    }

    public float changeAnimationPrepare1(View view) {
        return ViewCompat.getScaleX(view);
    }

    public void changeAnimationPrepare2(View view, float prevValue) {
        ViewCompat.setScaleX(view, prevValue);
    }

    public void changeAnimationPrepare3(View view) {
        ViewCompat.setScaleX(view, 0);
        ViewCompat.setScaleY(view, 0);
    }

    public ViewPropertyAnimatorCompat changeOldAnimation(View view, ChangeInfo changeInfo) {
        return ViewCompat.animate(view).setDuration(getChangeDuration()).scaleX(0).scaleY(0).translationX(changeInfo.toX - changeInfo.fromX).translationY(changeInfo.toY - changeInfo.fromY).setInterpolator(getInterpolator());
    }

    public ViewPropertyAnimatorCompat changeNewAnimation(View view) {
        return ViewCompat.animate(view).translationX(0).translationY(0).setDuration(getChangeDuration()).scaleX(1).scaleY(1).setInterpolator(getInterpolator());
    }

    public void changeAnimationCleanup(View view) {
        ViewCompat.setScaleX(view, 1);
        ViewCompat.setScaleY(view, 1);
    }
}
