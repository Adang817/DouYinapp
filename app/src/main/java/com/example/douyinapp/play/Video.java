package com.example.douyinapp.play;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.douyinapp.R;
import com.example.douyinapp.play.media.VideoPlayRecyclerView;

public class Video extends Activity {
    private VideoPlayRecyclerView mRvVideo;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);
        initView();
    }

    private void initView() {
        findViewById(R.id.ibBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRvVideo = findViewById(R.id.rvVideo);
        adapter = new MainAdapter(this);
        mRvVideo.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.release();
    }
}