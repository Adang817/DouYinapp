package com.example.douyinapp.liaotiandemo;

public class Msg {
    private String content;
    private int type;

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    static final int TYPE_RECEIVED = 0;
     static final int TYPE_SENT = 1;

    public String getContent()
    {
        return content;
    }
    public int getType()
    {
        return type;
    }
}
