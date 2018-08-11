package com.mikepenz.itemanimators;

import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by mikepenz on 08.01.16.
 */
public class ScaleUpAnimator extends BaseScaleAnimator<ScaleUpAnimator> {
    public void addAnimationPrepare(RecyclerView.ViewHolder holder) {
        ViewCompat.setScaleX(holder.itemView, 0);
        ViewCompat.setScaleY(holder.itemView, 0);
    }

    public ViewPropertyAnimatorCompat addAnimation(RecyclerView.ViewHolder holder) {
        return ViewCompat.animate(holder.itemView).scaleX(1).scaleY(1).setDuration(getAddDuration()).setInterpolator(getInterpolator());
    }

    public void addAnimationCleanup(RecyclerView.ViewHolder holder) {
        ViewCompat.setScaleX(holder.itemView, 1);
        ViewCompat.setScaleY(holder.itemView, 1);
    }


    public ViewPropertyAnimatorCompat removeAnimation(RecyclerView.ViewHolder holder) {
        return ViewCompat.animate(holder.itemView).setDuration(getRemoveDuration()).scaleX(0).scaleY(0).setInterpolator(getInterpolator());
    }

    public void removeAnimationCleanup(RecyclerView.ViewHolder holder) {
        ViewCompat.setScaleX(holder.itemView, 1);
        ViewCompat.setScaleY(holder.itemView, 1);
    }

    public float changeAnimationPrepare1(RecyclerView.ViewHolder holder) {
        return ViewCompat.getScaleX(holder.itemView);
    }

    public void changeAnimationPrepare2(RecyclerView.ViewHolder holder, float prevValue) {
        ViewCompat.setScaleX(holder.itemView, prevValue);
    }

    public void changeAnimationPrepare3(RecyclerView.ViewHolder holder) {
        ViewCompat.setScaleX(holder.itemView, 0);
        ViewCompat.setScaleY(holder.itemView, 0);
    }

    public ViewPropertyAnimatorCompat changeOldAnimation(RecyclerView.ViewHolder holder, ChangeInfo changeInfo) {
        return ViewCompat.animate(holder.itemView).setDuration(getChangeDuration()).scaleX(0).scaleY(0).translationX(changeInfo.toX - changeInfo.fromX).translationY(changeInfo.toY - changeInfo.fromY).setInterpolator(getInterpolator());
    }

    public ViewPropertyAnimatorCompat changeNewAnimation(RecyclerView.ViewHolder holder) {
        return ViewCompat.animate(holder.itemView).translationX(0).translationY(0).setDuration(getChangeDuration()).scaleX(1).scaleY(1).setInterpolator(getInterpolator());
    }

    public void changeAnimationCleanup(RecyclerView.ViewHolder holder) {
        ViewCompat.setScaleX(holder.itemView, 1);
        ViewCompat.setScaleY(holder.itemView, 1);
    }
}
