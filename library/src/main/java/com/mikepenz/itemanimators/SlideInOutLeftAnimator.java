package com.mikepenz.itemanimators;

import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * Created by mikepenz on 08.01.16.
 * Base for this animator thanks to @gabrielemariotti - https://github.com/gabrielemariotti/RecyclerViewItemAnimators
 */
public class SlideInOutLeftAnimator extends DefaultAnimator<SlideInOutLeftAnimator> {

    private float mDeltaX;
    private RecyclerView mRecyclerView;

    public SlideInOutLeftAnimator(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    @Override
    public void addAnimationPrepare(RecyclerView.ViewHolder holder) {
        retrieveItemPosition(holder);
        ViewCompat.setTranslationX(holder.itemView, -mDeltaX);
        ViewCompat.setTranslationZ(holder.itemView, 100);
    }

    @Override
    public ViewPropertyAnimatorCompat addAnimation(RecyclerView.ViewHolder holder) {
        final View view = holder.itemView;
        return ViewCompat.animate(view).translationX(0).alpha(1).setDuration(getAddDuration());
    }

    @Override
    public void addAnimationCleanup(RecyclerView.ViewHolder holder) {
        ViewCompat.setAlpha(holder.itemView, 1);
        ViewCompat.setTranslationX(holder.itemView, 0);
        ViewCompat.setTranslationZ(holder.itemView, 1);
    }

    @Override
    public ViewPropertyAnimatorCompat removeAnimation(RecyclerView.ViewHolder holder) {
        ViewCompat.setTranslationZ(holder.itemView, 100);
        final ViewPropertyAnimatorCompat animation = ViewCompat.animate(holder.itemView);
        return animation.setDuration(getRemoveDuration()).alpha(0).translationX(-mDeltaX);
    }

    @Override
    public void removeAnimationCleanup(RecyclerView.ViewHolder holder) {
        ViewCompat.setTranslationX(holder.itemView, 0);
        ViewCompat.setAlpha(holder.itemView, 1);
        ViewCompat.setTranslationZ(holder.itemView, 1);
    }


    private void retrieveItemPosition(final RecyclerView.ViewHolder holder) {
        mDeltaX = mRecyclerView.getWidth() - mRecyclerView.getLayoutManager().getDecoratedLeft(holder.itemView);
    }

    @Override
    public long getAddDelay(long remove, long move, long change) {
        return 0;
    }

    @Override
    public long getRemoveDelay(long remove, long move, long change) {
        return remove / 2;
    }

}
