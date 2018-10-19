package com.example.sande.handlerproject;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button button;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler(); // handler always on Ui Thread
        tv =findViewById(R.id.textView);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread th = new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        for(int i =0;i<=10;i++)
                        {
                            final int value =i;
                            try {
                                sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                 tv.setText("" + value);
                                }
                            });
                        }
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "data shown after 5 secs", Toast.LENGTH_SHORT).show();
                            }
                        },5000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "data sent using runonUiThread", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                };
                th.start();
            }
        });
    }
}
