package com.mikepenz.itemanimators;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mikepenz on 08.01.16.
 */
public abstract class BaseScaleAnimator<T> extends BaseItemAnimator<T> {
    public void changeAnimation(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromX, int fromY, int toX, int toY) {
        final float prevTranslationX = ViewCompat.getTranslationX(oldHolder.itemView);
        final float prevTranslationY = ViewCompat.getTranslationY(oldHolder.itemView);
        final float prevValue = changeAnimationPrepare1(oldHolder.itemView);
        resetAnimation(oldHolder);
        int deltaX = (int) (toX - fromX - prevTranslationX);
        int deltaY = (int) (toY - fromY - prevTranslationY);
        // recover prev translation state after ending animation
        ViewCompat.setTranslationX(oldHolder.itemView, prevTranslationX);
        ViewCompat.setTranslationY(oldHolder.itemView, prevTranslationY);

        changeAnimationPrepare2(oldHolder.itemView, prevValue);
        if (newHolder != null) {
            // carry over translation values
            resetAnimation(newHolder);
            ViewCompat.setTranslationX(newHolder.itemView, -deltaX);
            ViewCompat.setTranslationY(newHolder.itemView, -deltaY);
            changeAnimationPrepare3(newHolder.itemView);
        }
    }

    /**
     * @param view
     * @return the default value for the animatd attribute
     */
    abstract public float changeAnimationPrepare1(View view);

    /**
     * animates the view to the previous default value
     *
     * @param view
     * @param prevValue the previous value
     */
    abstract public void changeAnimationPrepare2(View view, float prevValue);

    /**
     * resets the value
     *
     * @param view
     */
    abstract public void changeAnimationPrepare3(View view);
}
