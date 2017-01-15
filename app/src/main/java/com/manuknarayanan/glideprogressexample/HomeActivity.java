package com.manuknarayanan.glideprogressexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.manuknarayanan.glideprogressplugin.view.GlideImageView;


public class HomeActivity extends AppCompatActivity {
    private GlideImageView glideImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        glideImageView = (GlideImageView) findViewById(R.id.glideImageView);
        //glideImageView.setUrl("http://goo.gl/gEgYUd");
        glideImageView.bind("http://sample.li/boat.jpg");
    }

}
