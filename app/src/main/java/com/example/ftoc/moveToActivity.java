package com.example.ftoc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class moveToActivity extends AppCompatActivity {

    Float rate = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_to);

        rate  = getIntent().getFloatExtra("rate",0f);
        String title = getIntent().getStringExtra("title");

        ((TextView)findViewById(R.id.title2)).setText(title);
    }
}
