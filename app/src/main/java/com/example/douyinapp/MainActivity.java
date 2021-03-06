package com.example.douyinapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageView tx;
    ImageView eyes;
    EditText zh;
    EditText mm;
    static  int flag_eyes=1;
    CheckBox checkBox;
    CreateDB createDB =null;
    SharedPreferences pf =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        tx=findViewById(R.id.tx);
        zh=findViewById(R.id.zh);
        mm=findViewById(R.id.mm);
        checkBox=findViewById(R.id.checkBox);
        eyes=findViewById(R.id.eyes);
        createDB = new CreateDB(this, "test.db", null, 1);






//        记住密码
        pf=getSharedPreferences("rememberpassword", Context.MODE_PRIVATE);
        boolean isRemember=pf.getBoolean("rememberpassword",false);
        if(isRemember){
            String name=pf.getString("zhanghao","");
            String password=pf.getString("mima","");
            zh.setText(name);
            mm.setText(password);
            checkBox.setChecked(true);

        }

        //注册放回该界面自动填充账号密码
        Intent intent=getIntent();
        String str_zh = intent.getStringExtra("zh");
        String str_mm = intent.getStringExtra("mm");
        if(str_zh!=null){
            zh.setText(str_zh);
            mm.setText(str_mm);
        }

        //账号文本框焦点改变事件
        this.zh.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Resources resources = getBaseContext().getResources();
                Drawable imageDrawable = resources.getDrawable(R.drawable.touxiang); //图片在drawable文件夹下
                tx.setBackgroundDrawable(imageDrawable);
            }
        });
        //密码文本框焦点改变事件
        mm.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    Resources resources = getBaseContext().getResources();
                    Drawable imageDrawable = resources.getDrawable(R.drawable.touxiang_noeyes); //图片在drawable文件夹下
                    tx.setBackgroundDrawable(imageDrawable);
                } else {
                    Resources resources = getBaseContext().getResources();
                    Drawable imageDrawable = resources.getDrawable(R.drawable.touxiang); //图片在drawable文件夹下
                    tx.setBackgroundDrawable(imageDrawable);
                }

            }
        });
    }

    //注册按钮
    public void register(View view) {
        Intent intent=new Intent(MainActivity.this,Register.class);
        startActivity(intent);
    }

    //让密码文本框改变状态
    public void show(View view) {
        if (flag_eyes==1){
            Resources resources = getBaseContext().getResources();
            Drawable imageDrawable = resources.getDrawable(R.drawable.close_eyes); //图片在drawable文件夹下
            eyes.setBackgroundDrawable(imageDrawable);
            mm.setTransformationMethod(PasswordTransformationMethod.getInstance());
            flag_eyes=0;
        }else{
            Resources resources = getBaseContext().getResources();
            Drawable imageDrawable = resources.getDrawable(R.drawable.open_eyes); //图片在drawable文件夹下
            eyes.setBackgroundDrawable(imageDrawable);
            mm.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            flag_eyes=1;
        }
    }

    //登录
    public void login(View view) {
        SQLiteDatabase db = createDB.getWritableDatabase();
        Cursor cursor=db.query("user",null,"zhanghao='"+zh.getText().toString()+"'",null,null,null,null);
        if(cursor.getCount()==0){
            Toast.makeText(this,"账号不存在",Toast.LENGTH_SHORT).show();
        }else{
            String mima ="";
            if(cursor.moveToFirst()){
              mima=  cursor.getString(cursor.getColumnIndex("mima"));
            }

            SharedPreferences.Editor editor=null;
            if(mima.equals(mm.getText().toString())){
                if(checkBox.isChecked()){
                     editor=pf.edit();
                        editor.putBoolean("rememberpassword",true);
                        editor.putString("zhanghao",zh.getText().toString());
                        editor.putString("mima",mm.getText().toString());
                        editor.apply();

                }else{
                    editor=pf.edit();
                    editor.putBoolean("rememberpassword",false);
                    editor.apply();
                }

                //登录成功！跳转至dibudaohanglang
                Intent intent=new Intent(MainActivity.this,dibudaohanlang.class);
                intent.putExtra("zhanghao",zh.getText().toString());
                startActivity(intent);
            }else{
                Toast.makeText(this,"密码错误",Toast.LENGTH_SHORT).show();
            }

        }
    }

}