package com.example.ftoc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.annotation.Documented;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class excRate extends AppCompatActivity implements View.OnClickListener,Runnable {

    double d,e,w;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exc_rate);

        SharedPreferences sp = getSharedPreferences("myrate", Context.MODE_PRIVATE);

        float d_1 = sp.getFloat("dollar", 6.90f);
        float e_1 = sp.getFloat("euro", 7.80f);
        float w_1 = sp.getFloat("won", 167.8f);


        d = Double.valueOf(d_1);
        e = Double.valueOf(e_1);
        w = Double.valueOf(w_1);


        Button btn1,btn2,btn3,btn4;
        btn1 = findViewById(R.id.button5);
        btn2 = findViewById(R.id.button7);
        btn3 = findViewById(R.id.button8);
        btn4 = findViewById(R.id.button9);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

//        Intent intent=getIntent();
//        d = intent.getDoubleExtra("dollar",6.90);
//        e = intent.getDoubleExtra("euro",7.80);
//        w = intent.getDoubleExtra("won",167.8);

        Thread t = new Thread(this);
        t.start();

        handler = new Handler(){

            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 5){
                    String str = (String)msg.obj;
                    Log.i("hello","handlerMessage: getMessage msg = " + str);
                }
                super.handleMessage(msg);
            }
        };
    }


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.button9)
        {
            Intent i = new Intent(this,exchange.class);
//            startActivity(i);
            startActivityForResult(i,1);

        }else {
            TextView out = findViewById(R.id.editText2);
            // 获取textview对象
            TextView in = findViewById(R.id.textView2);
            String str = out.getText().toString();
            // 获取内容
            double mon = Double.valueOf(str);
            // 转为数字

            switch (v.getId()) {
                case R.id.button5:
                    mon /= d;
                    break;
                case R.id.button7:
                    mon /= e;
                    break;
                case R.id.button8:
                    mon *= w;
                    break;
            }
            in.setText("" + mon);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 2){
            Bundle bundle = data.getExtras();
            d = bundle.getDouble("dollar",6.90);
            e = bundle.getDouble("euro",7.80);
            w = bundle.getDouble("won",167.8);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu,menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.menu_set){
            Intent i = new Intent(this,exchange.class);
//            startActivity(i);
            startActivityForResult(i,1);
        }else if(item.getItemId() == R.id.open_list){
            Intent list = new Intent(this,ListActivity.class);
            startActivity(list);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void run() {

        URL url = null;
        try {
            url = new URL("http://Www.usd-cny.com/bankofchina.htm");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            InputStream in = http.getInputStream();
            String html = inputStream2String(in);
            Log.i("hello","run:html " + html);

        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Message msg = handler.obtainMessage(5);
        msg.obj = "hello from run()";
        handler.sendMessage(msg);

    }

    private String inputStream2String(InputStream inputStream) throws IOException{
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream,"gb2312");
        while (true){
            int rsz = in.read(buffer,0,buffer.length);
            if(rsz < 0) break;
            out.append(buffer,0,rsz);
        }

//        File input = new File("");
//        Document doc = Jsoup.parse(input,"UTF-8","http://example.com");
//
//        Element links = doc.select("a[href]");
//        Element pngs = doc.select("img[src$=.png]");
//
//        Element masthead = doc.select("div.masthead").first();
//
//        Element resultLinks = doc.select("h3.r > a");




        return out.toString();
    }


}
