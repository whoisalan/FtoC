package com.example.ftoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class exchange extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);

        Button btn1,btn2,btn3;
        btn1 = findViewById(R.id.button10);
        btn2 = findViewById(R.id.button11);
        btn3 = findViewById(R.id.button12);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        TextView out = findViewById(R.id.editText3);
        // 获取textview对象
        String str = out.getText().toString();
        // 获取内容
        double rio = Double.valueOf(str);
        // 转为数字

        Intent intent = getIntent();
        Bundle bdl = new Bundle();

//        Intent i=new Intent(this,excRate.class);

        SharedPreferences sp = getSharedPreferences("myrate", Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = sp.edit();





        switch (v.getId()){
            case R.id.button10:
//                i.putExtra("dollar",rio);
                bdl.putDouble("dollar",rio);
                edit.putFloat("dollar",(float)rio );
                break;

            case R.id.button11:
//                i.putExtra("euro",rio);
                bdl.putDouble("euro",rio);
                edit.putFloat("euro", (float)rio);
                break;

            case R.id.button12:
//                i.putExtra("won",rio);
                bdl.putDouble("won",rio);
                edit.putFloat("won", (float)rio);
                break;
        }
        edit.commit();
        intent.putExtras(bdl);
        setResult(2,intent);
        finish();
//        startActivity(i);


    }
}
