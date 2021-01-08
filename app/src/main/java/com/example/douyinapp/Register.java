package com.example.douyinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText zh=null;
    EditText mm1=null;
    EditText mm2=null;
    Button register=null;
    Button back=null;
    CreateDB createDB =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        zh=findViewById(R.id.register_zh);
        mm1=findViewById(R.id.register_mm1);
        mm2=findViewById(R.id.register_mm2);
        createDB = new CreateDB(this, "test.db", null, 1);

    }

    //退回到登录页面
    public void back(View view) {
            Intent intent=new Intent(Register.this,MainActivity.class);
            startActivity(intent);
    }

    //注册界面
    public void reg(View view) {
        if("".equals(zh.getText().toString()) || "".equals(mm1.getText().toString()) || "".equals(mm2.getText().toString()) ){
            Toast.makeText(this,"请输入完整",Toast.LENGTH_SHORT).show();
        }else if(mm1.getText().toString().equals(mm2.getText().toString())){

            SQLiteDatabase db = createDB.getWritableDatabase();

            Cursor cursor=db.query("user",null,"zhanghao='"+zh.getText().toString()+"'",null,null,null,null);
            if(cursor.getCount()!=0){
                Toast.makeText(Register.this,"账号已存在",Toast.LENGTH_SHORT).show();
            }else{
                ContentValues contentValues = new ContentValues();
                contentValues.put("zhanghao",zh.getText().toString());
                contentValues.put("mima",mm1.getText().toString());
                db.insert("user",null,contentValues);
                contentValues.clear();
                Toast.makeText(this,"注册成功！",Toast.LENGTH_SHORT).show();
                String str_zh=zh.getText().toString();
                String str_mm= mm1.getText().toString();

                Intent intent=new Intent(Register.this,MainActivity.class);
                intent.putExtra("zh",str_zh);
                intent.putExtra("mm",str_mm);

                startActivity(intent);
            }

            cursor.close();


        }else{
            Toast.makeText(this,"俩次输入不一致",Toast.LENGTH_SHORT).show();
        }

    }
}