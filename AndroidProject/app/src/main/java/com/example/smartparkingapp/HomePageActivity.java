package com.example.smartparkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        final TextView welcometext = findViewById(R.id.textViewBenvenuto);
        Button acquista = findViewById(R.id.btnAcquistaView);
        Button addauto = findViewById(R.id.btnAggiungiAuto);
        final String username = getIntent().getExtras().getString("username");
        final String password = getIntent().getExtras().getString("password");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        welcometext.setText("Benvenuto "+username+"! Sono le "+simpleDateFormat.format(date));
        acquista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this,AcquistaTicket.class);
                Bundle bundle = new Bundle();
                bundle.putString("username",username);
                bundle.putString("password",password);
                intent.putExtras(bundle);
                HomePageActivity.this.startActivity(intent);
            }
        });
        addauto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this,AggiungiAuto.class);
                Bundle bundle = new Bundle();
                bundle.putString("username",username);
                bundle.putString("password",password);
                intent.putExtras(bundle);
                HomePageActivity.this.startActivity(intent);
            }
        });
    }
}
