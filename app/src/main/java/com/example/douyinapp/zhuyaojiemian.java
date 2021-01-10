package com.example.douyinapp;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

public class zhuyaojiemian extends FragmentActivity {
    ImageView shouye;
    ImageView jia;
    ImageView txl;
    TextView t1;
    TextView t2;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuyaojiemian);
        shouye=findViewById(R.id.shouye);
        jia=findViewById(R.id.jiahao);
        txl=findViewById(R.id.txl);
        t1=findViewById(R.id.str_sy);
        t2=findViewById(R.id.str_txl);


        shouye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources resources = getBaseContext().getResources();
                Drawable imageDrawable = resources.getDrawable(R.drawable.shouye1); //图片在drawable文件夹下
                shouye.setBackgroundDrawable(imageDrawable);



                 resources = getBaseContext().getResources();
                 imageDrawable = resources.getDrawable(R.drawable.jiahao); //图片在drawable文件夹下
                jia.setBackgroundDrawable(imageDrawable);

                 resources = getBaseContext().getResources();
                 imageDrawable = resources.getDrawable(R.drawable.txl); //图片在drawable文件夹下
                txl.setBackgroundDrawable(imageDrawable);
                t2.setTextColor(Color.BLACK);
                t1.setTextColor(Color.RED);
            }
        });

        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources resources = getBaseContext().getResources();
                Drawable imageDrawable = resources.getDrawable(R.drawable.shouye); //图片在drawable文件夹下
                shouye.setBackgroundDrawable(imageDrawable);


                resources = getBaseContext().getResources();
                imageDrawable = resources.getDrawable(R.drawable.jiahao1); //图片在drawable文件夹下
                jia.setBackgroundDrawable(imageDrawable);

                resources = getBaseContext().getResources();
                imageDrawable = resources.getDrawable(R.drawable.txl); //图片在drawable文件夹下
                txl.setBackgroundDrawable(imageDrawable);

                t2.setTextColor(Color.BLACK);
                t1.setTextColor(Color.BLACK);
            }
        });

        txl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources resources = getBaseContext().getResources();
                Drawable imageDrawable = resources.getDrawable(R.drawable.shouye); //图片在drawable文件夹下
                shouye.setBackgroundDrawable(imageDrawable);

                resources = getBaseContext().getResources();
                imageDrawable = resources.getDrawable(R.drawable.jiahao); //图片在drawable文件夹下
                jia.setBackgroundDrawable(imageDrawable);

                resources = getBaseContext().getResources();
                imageDrawable = resources.getDrawable(R.drawable.txl1); //图片在drawable文件夹下
                txl.setBackgroundDrawable(imageDrawable);

                t1.setTextColor(Color.BLACK);
                t2.setTextColor(Color.RED);
            }
        });
    }
}