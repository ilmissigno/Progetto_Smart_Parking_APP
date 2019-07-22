package com.example.smartparkingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PopupWindow extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);
        Intent intent = getIntent();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.9),(int)(height*.8));
        final String username = intent.getExtras().getString("Username");
        final String Password = intent.getExtras().getString("Password");
        final int IDTicket = intent.getExtras().getInt("IDTicket");
        final String Targa = intent.getExtras().getString("Targa");
        final String CodiceArea = intent.getExtras().getString("CodiceArea");
        final String DataScadenza = intent.getExtras().getString("DataScadenza");
        TextView textView = findViewById(R.id.textViewNotify);
        textView.setText("ATTENZIONE! TICKET IN SCADENZA\n"+"Attenzione, il Ticket acquistato è in scadenza, le " +
                "informazioni sul ticket sono le seguenti:\n" +
                "IDTicket "+IDTicket+" Targa "+Targa+" CodiceArea "+CodiceArea+"\n Vuoi Rinnovare il Ticket? \nSe si cliccare su Rinnova, altrimenti cliccare altrove");
        Button btnRinnovo = findViewById(R.id.btnRinnovo);
        btnRinnovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PopupWindow.this, RinnovoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Username",username);
                bundle.putString("Password",Password);
                bundle.putInt("IDTicket",IDTicket);
                bundle.putString("Targa",Targa);
                bundle.putString("CodiceArea",CodiceArea);
                bundle.putString("DataScadenza",DataScadenza);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });
    }
}
