package com.example.smartparkingapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OttieniDisponibilita extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ottieni_disponibilita);
        final EditText codArea = findViewById(R.id.codiceAreaDisp);
        Button btnGetPostiDisp = findViewById(R.id.btnGetDisponibilita);
        final String username = getIntent().getExtras().getString("username");
        final String password = getIntent().getExtras().getString("password");
        btnGetPostiDisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String CodiceArea = codArea.getText().toString();
                if(CodiceArea.isEmpty()){
                    Toast.makeText(OttieniDisponibilita.this,"Inserire tutti i campi",Toast.LENGTH_LONG).show();
                    return;
                }else{
                        final Handler handler = new Handler();
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                ProxyAutomobilista proxyAutomobilista = new ProxyAutomobilista();
                                proxyAutomobilista.getDisponibilita(CodiceArea,username,password,handler,OttieniDisponibilita.this);
                            }
                        });
                        thread.start();
                    }
                }
        });
    }
}
