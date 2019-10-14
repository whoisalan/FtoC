package com.example.ftoc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListActivity extends android.app.ListActivity implements Runnable, AdapterView.OnItemClickListener {



    Handler handler;
    private ArrayList<HashMap<String,String>> listItems;
    private SimpleAdapter listItemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initListView();
        this.setListAdapter(listItemAdapter);

        Thread t = new Thread(this);
        t.start();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 7) {
                    List<HashMap<String, String>> list2 = (List<HashMap<String, String>>) msg.obj;
                    listItemAdapter = new SimpleAdapter(ListActivity.this,
                            list2,
                            R.layout.activity_list2,
                            new String[]{"ItemTitle", "ItemDetail"},
                            new int[]{R.id.itemTitle, R.id.itemDetail});
                    setListAdapter(listItemAdapter);

                }
                super.handleMessage(msg);
            }

        };

        getListView().setOnItemClickListener(this);
    }
    private void initListView() {
        listItems = new ArrayList<HashMap<String, String>>();
        for(int i = 0;i<10;i++){
            HashMap<String,String> map = new HashMap<String, String>();
            map.put("ItemTitle","Rate: "+i);
            map.put("ItemDetail","Detail: "+i);
            listItems.add(map);
        }
        listItemAdapter = new SimpleAdapter(this,listItems,
                R.layout.activity_list2,new String[]{"ItemTitle","ItemDetail"},
                new int[] {R.id.itemTitle,R.id.itemTitle});

    }

    @Override
    public void run() {

        List<HashMap<String,String>> reList = new ArrayList<HashMap<String, String>>();
        Document doc = null;
        try{
            doc = Jsoup.connect("http://www.usd-cny.com/icbc.htm").get();
            Element table = doc.getElementsByTag("table").first();
            Elements trs = table.getElementsByTag("tr");

            for(int i = 1;i<trs.size();i++){
                Element td = trs.get(i);
                Elements tds =  td.getElementsByTag("td");
                String str1 = tds.get(0).text();
                String str2 = tds.get(1).text();

                Log.i("hello","a" + str1);
                Log.i("hello","b" + str2);

                HashMap<String,String> map = new HashMap<String, String>();
                map.put("ItemTitle",str1);
                map.put("ItemDetail",str2);
                reList.add(map);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Message msg = handler.obtainMessage(7);
        msg.obj = reList;
        handler.sendMessage(msg);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        HashMap<String,String> map = (HashMap<String, String>) getListView().getItemAtPosition(position);

        String a = map.get("ItemTitle");
        String b = map.get("ItemDetail");

        Log.i("hello","a" + a);
        Log.i("hello","b" + b);

        Intent i = new Intent(this,moveToActivity.class);
        i.putExtra("title",a);
        i.putExtra("rate",Float.parseFloat(b));
        startActivity(i);

    }
}
