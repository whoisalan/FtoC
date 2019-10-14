package com.example.ftoc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView input,out;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        out = findViewById(R.id.textView3);
        input =  findViewById(R.id.textView5);
        btn = findViewById(R.id.button3);
        btn.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        Log.i("aaa","onClick:");
        String str = out.getText().toString();
        float C_num = Float.valueOf(str);
        C_num = C_num*1.8f + 33;
        out.setText("Result:" + String.format("%.2f",C_num));

    }
}





