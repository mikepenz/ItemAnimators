package com.mikepenz.itemanimators;

import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by mikepenz on 08.01.16.
 */
public class SlideRightAlphaAnimator extends DefaultAnimator<SlideRightAlphaAnimator> {
    @Override
    public void addAnimationPrepare(RecyclerView.ViewHolder holder) {
        ViewCompat.setTranslationX(holder.itemView, -holder.itemView.getWidth());
        ViewCompat.setAlpha(holder.itemView, 0);
    }

    @Override
    public ViewPropertyAnimatorCompat addAnimation(RecyclerView.ViewHolder holder) {
        return ViewCompat.animate(holder.itemView).translationX(0).alpha(1).setDuration(getMoveDuration()).setInterpolator(getInterpolator());
    }

    @Override
    public void addAnimationCleanup(RecyclerView.ViewHolder holder) {
        ViewCompat.setTranslationX(holder.itemView, 0);
        ViewCompat.setAlpha(holder.itemView, 1);
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
    public ViewPropertyAnimatorCompat removeAnimation(RecyclerView.ViewHolder holder) {
        final ViewPropertyAnimatorCompat animation = ViewCompat.animate(holder.itemView);
        return animation.setDuration(getRemoveDuration()).alpha(0).translationX(-holder.itemView.getWidth()).setInterpolator(getInterpolator());
    }

    @Override
    public void removeAnimationCleanup(RecyclerView.ViewHolder holder) {
        ViewCompat.setTranslationX(holder.itemView, 0);
        ViewCompat.setAlpha(holder.itemView, 1);
    }
}
