package com.example.smartparkingapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home_page);
        final TextView welcometext = findViewById(R.id.textViewBenvenuto);
        Button acquista = findViewById(R.id.btnAcquistaView);
        Button addauto = findViewById(R.id.btnAggiungiAuto);
        Button btnCaricaConto = findViewById(R.id.btnCaricaConto);
        Button btndeleteAuto = findViewById(R.id.btndeleteAuto);
        Button btnOttDisp = findViewById(R.id.btnDispView);
        final String username = getIntent().getExtras().getString("username");
        final String password = getIntent().getExtras().getString("password");
        final boolean deleted = getIntent().getExtras().getBoolean("cancellato");
        if(deleted) {
            NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                manager.deleteNotificationChannel("SmartParkingNotifica");
            }
        }
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
        btnCaricaConto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this,CaricaContoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username",username);
                bundle.putString("password",password);
                intent.putExtras(bundle);
                HomePageActivity.this.startActivity(intent);
            }
        });
        btndeleteAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this,CancellaAutoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username",username);
                bundle.putString("password",password);
                intent.putExtras(bundle);
                HomePageActivity.this.startActivity(intent);
            }
        });
        btnOttDisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this,OttieniDisponibilita.class);
                Bundle bundle = new Bundle();
                bundle.putString("username",username);
                bundle.putString("password",password);
                intent.putExtras(bundle);
                HomePageActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView welcometext = findViewById(R.id.textViewBenvenuto);
        String username = getIntent().getExtras().getString("username");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        welcometext.setText("Benvenuto "+username+"! Sono le "+simpleDateFormat.format(date));
    }
}
