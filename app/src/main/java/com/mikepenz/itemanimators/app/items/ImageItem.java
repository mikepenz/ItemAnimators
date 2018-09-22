package com.mikepenz.itemanimators.app.items;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikepenz.fastadapter.commons.utils.FastAdapterUIUtils;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.itemanimators.app.R;
import com.mikepenz.materialize.util.UIUtils;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mikepenz on 28.12.15.
 */
public class ImageItem extends AbstractItem<ImageItem, ImageItem.ViewHolder> {

    public String mImageUrl;
    public String mName;
    public String mDescription;

    public ImageItem withImage(String imageUrl) {
        this.mImageUrl = imageUrl;
        return this;
    }

    public ImageItem withName(String name) {
        this.mName = name;
        return this;
    }

    public ImageItem withDescription(String description) {
        this.mDescription = description;
        return this;
    }

    @Override
    public int getType() {
        return R.id.fastadapter_image_item_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.image_item;
    }

    @Override
    public void bindView(ViewHolder viewHolder, List<Object> payloads) {
        super.bindView(viewHolder, payloads);

        //get context
        Context ctx = viewHolder.itemView.getContext();

        //define our data for the view
        viewHolder.imageName.setText(mName);
        viewHolder.imageDescription.setText(mDescription);
        viewHolder.imageView.setImageBitmap(null);

        //set the background for the item
        int color = UIUtils.getThemeColor(ctx, R.attr.colorPrimary);
        viewHolder.view.setForeground(FastAdapterUIUtils.getSelectablePressedBackground(ctx, FastAdapterUIUtils.adjustAlpha(color, 100), 50, true));

        //load glide
        Glide.clear(viewHolder.imageView);
        Glide.with(ctx).load(mImageUrl).animate(R.anim.alpha_on).into(viewHolder.imageView);
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    /**
     * our ViewHolder
     */
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected FrameLayout view;
        @BindView(R.id.item_image_img)
        protected ImageView imageView;
        @BindView(R.id.item_image_name)
        protected TextView imageName;
        @BindView(R.id.item_image_description)
        protected TextView imageDescription;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = (FrameLayout) view;

            //optimization to preset the correct height for our device
            int screenWidth = view.getContext().getResources().getDisplayMetrics().widthPixels;
            int finalHeight = (int) (screenWidth / 1.5) / 2;
            imageView.setMinimumHeight(finalHeight);
            imageView.setMaxHeight(finalHeight);
            imageView.setAdjustViewBounds(false);
            //set height as layoutParameter too
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) imageView.getLayoutParams();
            lp.height = finalHeight;
            imageView.setLayoutParams(lp);
        }
    }
}
