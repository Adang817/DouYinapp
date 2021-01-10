package com.example.douyinapp.liaotiandemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.douyinapp.R;

import java.util.ArrayList;

public class chat extends AppCompatActivity {

    private ArrayList<Msg> msgArrayList=new ArrayList<Msg>();
    private MsgAdapter adapter=null;
    EditText editText;
    Button button ;
    Boolean send=true;
    RecyclerView recyclerView;
    MsgAdapter msgAdapter;
    LinearLayoutManager linearLayoutManager;
    Button back;
    Button edit;
    String info =null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent i1 = getIntent();
         info = i1.getStringExtra("info");

        TextView viewById = findViewById(R.id.t1);
        viewById.setText(info);
        //加载控件
        button = findViewById(R.id.send);
        editText=findViewById(R.id.inputText);
        back=findViewById(R.id.button10);

        //隐藏标题栏
        getSupportActionBar().hide();

        //加入默认对话
        iniMsg();

        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        msgAdapter=  new MsgAdapter(msgArrayList);
        recyclerView.setLayoutManager(linearLayoutManager);

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setAdapter(msgAdapter);
        //点击返回事件
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });


        //发送消息
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content=editText.getText().toString();
                Msg msg;
                if (!"".equals(content))
                {

                    msg=new Msg(content,Msg.TYPE_SENT);
                    msgArrayList.add(msg);
                    msgAdapter.notifyItemInserted(msgArrayList.size()-1);
                    recyclerView.scrollToPosition(msgArrayList.size()-1);
                    editText.setText("");

                }


                msg=new Msg(content,Msg.TYPE_RECEIVED);
                msgArrayList.add(msg);
                msgAdapter.notifyItemInserted(msgArrayList.size()-1);
                recyclerView.scrollToPosition(msgArrayList.size()-1);

                editText.setText("");

            }

        });
    }


    private  void  iniMsg(){
        Msg msg3=new Msg("你好，我是："+info,Msg.TYPE_RECEIVED);
        msgArrayList.add(msg3);

    }
}