package com.example.smartparkingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Time;
import java.util.Date;

public class AcquistaTicket extends AppCompatActivity {

    private String Targa;
    private String CodiceArea;
    private int OraDa;
    private int OraA;
    private double CostoTicket;

    public AcquistaTicket(){}

    public double getCostoTicket(){
        return this.CostoTicket;
    }

    public void setCostoTicket(double CostoTicket){
        this.CostoTicket = CostoTicket;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acquista_ticket);
        final EditText targa = (EditText)findViewById(R.id.targa);
        final EditText codicearea = (EditText)findViewById(R.id.codiceArea);
        final EditText orada = findViewById(R.id.ora);
        final EditText oraa = findViewById(R.id.ora2);
        Button btnCalcola = (Button)findViewById(R.id.btnCalcolaCostoOra);
        final Button btnAcquista = (Button)findViewById(R.id.btnAcquista);
        final TextView costoTotale = (TextView)findViewById(R.id.costoTotale);
        btnAcquista.setEnabled(false);
        btnCalcola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CodiceArea = codicearea.getText().toString().trim();
                ProxyAutomobilista proxyAutomobilista = new ProxyAutomobilista();
                if(proxyAutomobilista.OttieniCostoTicket(CodiceArea)==0){
                    TicketImpl ticket = new TicketImpl();
                    ticket.avvia_skeleton();
                    OraDa = Integer.parseInt(orada.getText().toString().trim());
                    OraA = Integer.parseInt(oraa.getText().toString().trim());
                    //Calcolo l'importo totale facendo la differenza delle ore
                    costoTotale.setText(String.valueOf((OraA-OraDa)*getCostoTicket())+" \u20ac");
                    btnAcquista.setEnabled(true);
                }

            }
        });
        btnAcquista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Targa = targa.getText().toString().trim();
                ProxyAutomobilista proxyAutomobilista = new ProxyAutomobilista();
                int Durata = OraA-OraDa;
                double costoTot = Double.parseDouble(costoTotale.getText().toString().trim());
                if(proxyAutomobilista.AcquistaTicket(Targa,CodiceArea,Durata,costoTot)){
                    //Skeleton
                }
            }
        });
    }
}
