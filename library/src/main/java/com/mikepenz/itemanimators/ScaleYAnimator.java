package com.mikepenz.itemanimators;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.View;

/**
 * Created by mikepenz on 08.01.16.
 */
public class ScaleYAnimator extends BaseScaleAnimator<ScaleYAnimator> {
    public void addAnimationPrepare(View view) {
        ViewCompat.setScaleY(view, 0);
    }

    public ViewPropertyAnimatorCompat addAnimation(View view) {
        return ViewCompat.animate(view).scaleY(1).setDuration(getAddDuration());
    }

    public void addAnimationCleanup(View view) {
        ViewCompat.setScaleY(view, 1);
    }


    public ViewPropertyAnimatorCompat removeAnimation(View view) {
        final ViewPropertyAnimatorCompat animation = ViewCompat.animate(view);
        return animation.setDuration(getRemoveDuration()).scaleY(0);
    }

    public void removeAnimationCleanup(View view) {
        ViewCompat.setScaleY(view, 1);
    }

    public float changeAnimationPrepare1(View view) {
        return ViewCompat.getScaleY(view);
    }

    public void changeAnimationPrepare2(View view, float prevValue) {
        ViewCompat.setScaleY(view, prevValue);
    }

    public void changeAnimationPrepare3(View view) {
        ViewCompat.setScaleY(view, 0);
    }

    public ViewPropertyAnimatorCompat changeOldAnimation(View view, ChangeInfo changeInfo) {
        return ViewCompat.animate(view).setDuration(getChangeDuration()).scaleY(0).translationX(changeInfo.toX - changeInfo.fromX).translationY(changeInfo.toY - changeInfo.fromY);

    }

    public ViewPropertyAnimatorCompat changeNewAnimation(View view) {
        return ViewCompat.animate(view).translationX(0).translationY(0).setDuration(getChangeDuration()).scaleY(1);
    }

    public void changeAnimationCleanup(View view) {
        ViewCompat.setScaleY(view, 1);
    }
}
