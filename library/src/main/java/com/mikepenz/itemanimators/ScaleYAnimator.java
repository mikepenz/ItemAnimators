package com.mikepenz.itemanimators;

import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by mikepenz on 08.01.16.
 */
public class ScaleYAnimator extends BaseScaleAnimator<ScaleYAnimator> {
    public void addAnimationPrepare(RecyclerView.ViewHolder holder) {
        ViewCompat.setScaleY(holder.itemView, 0);
    }

    public ViewPropertyAnimatorCompat addAnimation(RecyclerView.ViewHolder holder) {
        return ViewCompat.animate(holder.itemView).scaleY(1).setDuration(getAddDuration());
    }

    public void addAnimationCleanup(RecyclerView.ViewHolder holder) {
        ViewCompat.setScaleY(holder.itemView, 1);
    }


    public ViewPropertyAnimatorCompat removeAnimation(RecyclerView.ViewHolder holder) {
        final ViewPropertyAnimatorCompat animation = ViewCompat.animate(holder.itemView);
        return animation.setDuration(getRemoveDuration()).scaleY(0);
    }

    public void removeAnimationCleanup(RecyclerView.ViewHolder holder) {
        ViewCompat.setScaleY(holder.itemView, 1);
    }

    public float changeAnimationPrepare1(RecyclerView.ViewHolder holder) {
        return ViewCompat.getScaleY(holder.itemView);
    }

    public void changeAnimationPrepare2(RecyclerView.ViewHolder holder, float prevValue) {
        ViewCompat.setScaleY(holder.itemView, prevValue);
    }

    public void changeAnimationPrepare3(RecyclerView.ViewHolder holder) {
        ViewCompat.setScaleY(holder.itemView, 0);
    }

    public ViewPropertyAnimatorCompat changeOldAnimation(RecyclerView.ViewHolder holder, ChangeInfo changeInfo) {
        return ViewCompat.animate(holder.itemView).setDuration(getChangeDuration()).scaleY(0).translationX(changeInfo.toX - changeInfo.fromX).translationY(changeInfo.toY - changeInfo.fromY);

    }

    public ViewPropertyAnimatorCompat changeNewAnimation(RecyclerView.ViewHolder holder) {
        return ViewCompat.animate(holder.itemView).translationX(0).translationY(0).setDuration(getChangeDuration()).scaleY(1);
    }

    public void changeAnimationCleanup(RecyclerView.ViewHolder holder) {
        ViewCompat.setScaleY(holder.itemView, 1);
    }
}
