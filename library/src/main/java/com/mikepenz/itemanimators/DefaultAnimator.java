package com.mikepenz.itemanimators;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.View;

/**
 * Created by mikepenz on 08.01.16.
 */
public class DefaultAnimator<T> extends BaseItemAnimator<T> {
    // ADD ANIMATION METHODS

    public void addAnimationPrepare(View view) {
        ViewCompat.setAlpha(view, 0);
    }

    public ViewPropertyAnimatorCompat addAnimation(View view) {
        return ViewCompat.animate(view).alpha(1).setDuration(getAddDuration()).setInterpolator(getInterpolator());
    }

    public void addAnimationCleanup(View view) {
        ViewCompat.setAlpha(view, 1);
    }

    // REMOVE ANIMATION METHODS

    public ViewPropertyAnimatorCompat removeAnimation(View view) {
        return ViewCompat.animate(view).setDuration(getRemoveDuration()).alpha(0).setInterpolator(getInterpolator());
    }

    public void removeAnimationCleanup(View view) {
        ViewCompat.setAlpha(view, 1);
    }

    // CHANGE ANIMATION METHODS
    public ViewPropertyAnimatorCompat changeOldAnimation(View view, ChangeInfo changeInfo) {
        return ViewCompat.animate(view).setDuration(getChangeDuration()).alpha(0).translationX(changeInfo.toX - changeInfo.fromX).translationY(changeInfo.toY - changeInfo.fromY).setInterpolator(getInterpolator());
    }

    public ViewPropertyAnimatorCompat changeNewAnimation(View view) {
        return ViewCompat.animate(view).translationX(0).translationY(0).setDuration(getChangeDuration()).alpha(1).setInterpolator(getInterpolator());
    }

    public void changeAnimationCleanup(View view) {
        ViewCompat.setAlpha(view, 1);
    }
}
