package com.manuknarayanan.glideprogressplugin.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.manuknarayanan.glideprogressplugin.DetailedProgressTarget;
import com.manuknarayanan.glideprogressplugin.R;

/**
 * Created by Manu K Narayanan on 10-01-2017.
 */

public class GlideImageView extends RelativeLayout {
    private ImageView mImageView;
    private ProgressBar mProgressView;
    private TextView mTextView;
    private DetailedProgressTarget<Bitmap> target;

    public GlideImageView(Context context) {
        super(context);
        initializeViews(context);
    }

    public GlideImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public GlideImageView(Context context,
                          AttributeSet attrs,
                          int defStyle) {
        super(context, attrs, defStyle);
        initializeViews(context);
    }

    /**
     * Inflates the views in the layout.
     *
     * @param context the current context for the view.
     */
    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.glide_image_holder, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mImageView = (ImageView) this
                .findViewById(R.id.imageView);
        mProgressView = (ProgressBar) this
                .findViewById(R.id.progressBar);
        mTextView = (TextView) this
                .findViewById(R.id.progressTextView);

        target = new DetailedProgressTarget<>(new BitmapImageViewTarget(mImageView), mProgressView, mImageView, mTextView);
    }

    public void bind(String url) {
        target.setModel(url); // update target's cache
        Glide.with(mImageView.getContext())
                .load(url)
                .asBitmap()
                //.placeholder(R.drawable.no_image)
                .centerCrop() // needs explicit transformation, because we're using a custom target
                .into(target)
        ;
    }


}
