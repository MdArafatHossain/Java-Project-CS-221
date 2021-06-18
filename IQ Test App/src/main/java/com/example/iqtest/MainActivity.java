package com.example.iqtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button newgameBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newgameBtn = findViewById(R.id.newgame_btn);// assign newgameBtn value
        // set the clicklistener for new game button
        newgameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent subjectIntent = new Intent(MainActivity.this, SubjectActivity.class);
                startActivity(subjectIntent);

            }
        });

    }
}
