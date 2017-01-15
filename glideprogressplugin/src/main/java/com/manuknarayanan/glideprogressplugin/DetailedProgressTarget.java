package com.manuknarayanan.glideprogressplugin;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.request.target.Target;


/**
 * Created by Manu K Narayanan on 10-01-2017.
 */

public class DetailedProgressTarget<Z> extends ProgressTarget<String, Z> {
    private static final String TAG = "LOG_PROGRESS_TAR";
    private final TextView text;
    private final ProgressBar progress;
    private final ImageView image;

    /**
     * Constructor
     * @param target
     * @param progress
     * @param image
     * @param text
     */
    public DetailedProgressTarget(Target<Z> target, ProgressBar progress, ImageView image, TextView text) {
        super(target);
        this.progress = progress;
        this.image = image;
        this.text = text;
    }

    @Override
    public float getGranualityPercentage() {
        return 0.1f; // this matches the format string for #text below
    }

    @Override
    protected void onConnecting() {
        progress.setIndeterminate(true);
        progress.setVisibility(View.VISIBLE);
        image.setImageLevel(0);
        text.setVisibility(View.VISIBLE);
        text.setText("Loading....");
    }

    @Override
    protected void onDownloading(long bytesRead, long expectedLength) {
        progress.setIndeterminate(false);
        progress.setProgress((int) (100 * bytesRead / expectedLength));
        image.setImageLevel((int) (10000 * bytesRead / expectedLength));
        double m = expectedLength/1e6;
        double k = expectedLength/1000;
        double size=0;
        double total=0;
        String unit="bytes";
        if (m > 1) {
            size = bytesRead / 1e6;
            total=expectedLength / 1e6;
            unit="MB";
        } else if (k > 1){
            size = bytesRead / 1000;
            total=expectedLength / 1000;
            unit="KB";
        }else{
            size = bytesRead ;
            total=expectedLength;
            unit="bytes";
        }

        String progressData = String.format("Downloading %.2f/%.2f %s \n%.1f%%",
                size,total ,unit, 100f * bytesRead / expectedLength);
        text.setText(progressData);
    }

    @Override
    protected void onDownloaded() {
        progress.setIndeterminate(true);
        image.setImageLevel(10000);
        text.setText("Loading.....");
    }

    @Override
    protected void onDelivered() {
        progress.setVisibility(View.INVISIBLE);
        image.setImageLevel(0); // reset ImageView default
        text.setVisibility(View.INVISIBLE);
    }
}
