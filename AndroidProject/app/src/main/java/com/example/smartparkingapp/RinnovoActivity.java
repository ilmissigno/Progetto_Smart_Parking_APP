package com.example.smartparkingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class RinnovoActivity extends AppCompatActivity {

    private int OraA;
    private double CostoTicket;
    private Socket s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rinnovo);
        final TextView targa = findViewById(R.id.targa);
        final TextView codarea = findViewById(R.id.codArea);
        final TextView orada = findViewById(R.id.ora);
        final EditText oraa = findViewById(R.id.ora3);
        Button btnCalcola = (Button)findViewById(R.id.btnCalcolaCostoOra);
        final Button btnRin = (Button)findViewById(R.id.btnRin);
        final TextView costoTotale = (TextView)findViewById(R.id.costoTotale);
        final String username = getIntent().getExtras().getString("Username");
        final String Password = getIntent().getExtras().getString("Password");
        final int IDTicket = getIntent().getExtras().getInt("IDTicket");
        final String Targa = getIntent().getExtras().getString("Targa");
        final String CodiceArea = getIntent().getExtras().getString("CodiceArea");
        final String DataScadenza = getIntent().getExtras().getString("DataScadenza");
        targa.setText(Targa);
        codarea.setText(CodiceArea);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    final Socket s = new Socket(InetAddress.getByName("10.0.2.2"), 8000);
                    final DataOutputStream out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
                    final DataInputStream in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                    out.writeUTF("dataorariosend");
                    out.flush();
                    boolean checkora = in.readBoolean();
                    if (checkora) {
                        String orario = in.readUTF();
                        orada.setText(orario);
                    } else {
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(timerTask,0,1000);
        btnRin.setEnabled(false);
        btnCalcola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    OraA = Integer.parseInt(oraa.getText().toString().trim());
                    final double Durata = OraA;
                    final Handler handler = new Handler();
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                final Socket s = new Socket(InetAddress.getByName("10.0.2.2"),8000);
                                final DataOutputStream out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
                                final DataInputStream in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                                out.writeUTF("costoticketsend");
                                out.flush();
                                out.writeUTF(CodiceArea);
                                out.flush();
                                out.writeDouble(Durata);
                                out.flush();
                                CostoTicket = in.readDouble();
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        costoTotale.setText(String.valueOf(CostoTicket)+" \u20ac");
                                        btnRin.setEnabled(true);
                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    t.start();
            }
        });
        btnRin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Handler handler = new Handler();
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            final Socket s = new Socket(InetAddress.getByName("10.0.2.2"),8000);
                            final DataOutputStream out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
                            final DataInputStream in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                            out.writeUTF("rinnovosend");
                            out.flush();
                            out.writeInt(IDTicket);
                            out.flush();
                            out.writeDouble(Double.parseDouble(oraa.getText().toString().trim()));
                            out.flush();
                            out.writeUTF(username);
                            out.flush();
                            out.writeUTF(Password);
                            out.flush();
                            out.writeDouble(CostoTicket);
                            out.flush();
                            boolean confirm = in.readBoolean();
                            if(confirm){
                                final int idTicket = in.readInt();
                                final String datascad = in.readUTF();
                                final boolean ok = in.readBoolean();
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                            if(ok){
                                                AlertDialog.Builder builder = new AlertDialog.Builder(RinnovoActivity.this);
                                                builder.setCancelable(true);
                                                builder.setTitle("Rinnovo Effettuato");
                                                builder.setMessage("Rinnovo del Ticket effettuato correttamente");
                                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(RinnovoActivity.this,TicketInfoActivity.class);
                                                        Bundle bundle2 = new Bundle();
                                                        bundle2.putString("Username",username);
                                                        bundle2.putString("Password",Password);
                                                        bundle2.putInt("IDTicket", idTicket);
                                                        bundle2.putString("Targa", targa.getText().toString());
                                                        bundle2.putString("CodiceArea", codarea.getText().toString());
                                                        bundle2.putString("DataScadenza", datascad);
                                                        intent.putExtras(bundle2);
                                                        RinnovoActivity.this.startActivity(intent);
                                                    }
                                                });
                                                AlertDialog dialog = builder.create();
                                                dialog.show();
                                            }else{
                                                Toast.makeText(RinnovoActivity.this, "Impossibile Effettuare il Rinnovo", Toast.LENGTH_LONG).show();
                                                return;
                                            }
                                    }
                                });
                            }else{
                                Toast.makeText(RinnovoActivity.this, "Impossibile Effettuare il Rinnovo", Toast.LENGTH_LONG).show();
                                return;
                            }
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                });
                t.start();
            }
        });
    }
}
