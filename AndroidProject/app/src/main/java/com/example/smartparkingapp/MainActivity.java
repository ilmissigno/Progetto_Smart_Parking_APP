package com.example.smartparkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private String Username;
    private String Password;
    private String Auth;

    public MainActivity(){

    }

    public String getAuth() {
        return Auth;
    }

    public void setAuth(String auth) {
        Auth = auth;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText username = (EditText)findViewById(R.id.username);
        final EditText password = (EditText)findViewById(R.id.password);
        Button login_button = (Button)findViewById(R.id.login);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username = username.getText().toString();
                Password = password.getText().toString();
                ProxyAutomobilista proxyAutomobilista = new ProxyAutomobilista();
                if(proxyAutomobilista.Login(Username,Password)){
                    TicketImpl ticket = new TicketImpl();
                    ticket.avvia_skeleton();
                    if(getAuth().equals("ok")){
                        Bundle bundle = new Bundle();
                        bundle.putString("username",Username);
                        Intent intent = new Intent(MainActivity.this,AcquistaTicket.class);
                        intent.putExtras(bundle);
                        MainActivity.this.startActivity(intent);
                    }
                }

            }
        });
    }
}
