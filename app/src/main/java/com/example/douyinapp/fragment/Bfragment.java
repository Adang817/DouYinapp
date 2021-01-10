package com.example.douyinapp.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.douyinapp.R;
import com.example.douyinapp.liaotiandemo.chat;

import java.util.ArrayList;
import java.util.List;


public class Bfragment extends Fragment {
    ListView contacts;
    ArrayAdapter<String> adapter;
    List<String> list=new ArrayList<String>();
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bfragment,container,false);
         contacts = view.findViewById(R.id.contactsView);
         swipeRefreshLayout=view.findViewById(R.id.refrent);
         swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
             @Override
             public void onRefresh() {
                refrent();
             }
         });
         adapter=new ArrayAdapter<String>(getContext(),R.layout.textadaper,list);
         contacts.setAdapter(adapter);
         //清除之前的联系人
        adapter.clear();
        adapter.notifyDataSetChanged() ;
        if( ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_CONTACTS},1);

        }else{
            readContacts();
        }
        clickItem();
        return view;
    }
    //获取通讯录内容
    private void  readContacts(){
        Cursor cursor=null;
        try {
            cursor=getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            if(cursor != null){
                while (cursor.moveToNext()){
                    //获取联系人
                    String displayName=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

                    String number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    list.add(displayName+"\n"+number);
                }

                adapter.notifyDataSetChanged();
            }
        }catch (Exception e){
                e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
        }

    }
    //刷新方法
    public void refrent(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.clear();
                        adapter.notifyDataSetChanged() ;
                        readContacts();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

            }
        }).start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    readContacts();
                }else{
                    Toast.makeText(getContext(),"您没有权限",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    //为list view设置点击事件
    public void clickItem(){

        contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent a=new Intent(getContext(), chat.class);
                a.putExtra("info",list.get(position));
                startActivity(a);
            }
        });

    }
}
