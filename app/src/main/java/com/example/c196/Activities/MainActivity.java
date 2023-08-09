package com.example.c196.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.c196.R;

public class MainActivity extends AppCompatActivity {
    public static int numAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
    }
    public void onButtonClick(View view) {
        Log.d("MainActivity", "Button clicked");
        Intent intent = new Intent(MainActivity.this, TermList.class);
        startActivity(intent);
    }

}
