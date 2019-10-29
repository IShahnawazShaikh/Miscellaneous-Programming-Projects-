package com.example.oasisNav;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
        Button HeadManager,ServiceMan;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            HeadManager=findViewById(R.id.HeadManager);
            ServiceMan=findViewById(R.id.ServiceMan);
            HeadManager.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(MainActivity.this, Manager_Login.class);
                    startActivity(intent);
                }
            });
            ServiceMan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(MainActivity.this, ServiceMan_Login.class);
                    startActivity(intent);
                }
            });


        }
    }

