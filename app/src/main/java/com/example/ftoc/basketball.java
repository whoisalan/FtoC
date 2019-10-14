package com.example.ftoc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class basketball extends AppCompatActivity implements View.OnClickListener {

    Button btn,btn2,btn4,btn6;
    TextView input,out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basketball);

        btn = findViewById(R.id.button);
        btn.setOnClickListener(this);

        btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(this);

        btn4 = findViewById(R.id.button4);
        btn4.setOnClickListener(this);

        btn6 = findViewById(R.id.button6);
        btn6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        out = findViewById(R.id.textView);
        String str = out.getText().toString();
        int score = Integer.valueOf(str);

        switch (v.getId()){
            case R.id.button:
                score += 1;
            case R.id.button2:
                score += 2;
            case R.id.button4:
                score += 3;
            case R.id.button6:
                score = 0;

        }
        out.setText(String.valueOf(score));

    }


}
