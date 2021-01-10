package com.example.douyinapp.play;

import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.douyinapp.R;

import java.util.ArrayList;

public class PagerSnapHelperAdapter extends RecyclerView.Adapter<PagerSnapHelperAdapter.ViewHolder> {

    // 数据集
    private ArrayList<String> mDataList;


    private int mWidth;
    private int mHeight;
    private VideoView play;
    //
    public PagerSnapHelperAdapter(ArrayList<String> dataset, int width, int height) {
        super();
        this.mDataList = dataset;
        //
        mWidth = width;
        mHeight = height;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
        View view = View.inflate(viewGroup.getContext(), R.layout.recycle_pager_item, null);
        View contentView = view.findViewById(R.id.add_btn);

        RelativeLayout.LayoutParams rl = (RelativeLayout.LayoutParams) contentView.getLayoutParams();
        rl.width = mWidth;
        rl.height = mHeight;
        contentView.setLayoutParams(rl);

        // 创建一个ViewHolder
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Log.e("xiaxl: ", "---onBindViewHolder---");

        // 绑定数据到ViewHolder上
        viewHolder.itemView.setTag(position);
        //
        viewHolder.videoView.setVideoURI(Uri.parse("android.resource://org.dengzh/"+R.raw.p1));
        viewHolder.mTextView.setText(position + " item");
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    /**
     *
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public  VideoView videoView;
        public ViewHolder(View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.video);

            mTextView = (TextView) itemView.findViewById(R.id.add_btn);
        }
    }
}
