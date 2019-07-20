package com.example.smartparkingapp;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AcquistaTicket extends AppCompatActivity {

    private String Targa;
    private String CodiceArea;
    private String OraDa;
    private int OraA;
    private double CostoTicket;
    private Socket s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acquista_ticket);
        final Spinner listaauto = findViewById(R.id.listaAuto);
        final EditText codicearea = (EditText)findViewById(R.id.codiceArea);
        final TextView orada = findViewById(R.id.ora);
        final EditText oraa = findViewById(R.id.ora2);
        Button btnCalcola = (Button)findViewById(R.id.btnCalcolaCostoOra);
        final Button btnAcquista = (Button)findViewById(R.id.btnAcquista);
        final TextView costoTotale = (TextView)findViewById(R.id.costoTotale);
        Bundle bundle = getIntent().getExtras();
        final String Username = bundle.getString("username");
        final String Password = bundle.getString("password");
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final Socket s = new Socket(InetAddress.getByName("10.0.2.2"), 8000);
                        final DataOutputStream out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
                        final DataInputStream in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                        out.writeUTF("caricaautosend");
                        out.flush();
                        out.writeUTF(Username);
                        out.flush();
                        boolean ok = in.readBoolean();
                        if (ok) {
                            int listaAutosize = in.readInt();
                            ArrayList<String> auto = new ArrayList<String>();
                            for (int i = 0; i < listaAutosize; i++)
                                auto.add(in.readUTF());
                            //Set auto to Spinner
                            listaauto.setAdapter(new ArrayAdapter<String>(AcquistaTicket.this, R.layout.support_simple_spinner_dropdown_item, auto));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        final Socket s = new Socket(InetAddress.getByName("10.0.2.2"), 8000);
                        final DataOutputStream out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
                        final DataInputStream in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                        out.writeUTF("dataorariosend");
                        out.flush();
                        boolean checkora = in.readBoolean();
                        if(checkora){
                            String orario = in.readUTF();
                            orada.setText(orario);
                        }else{
                            return;
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            });
            t1.start();
            btnAcquista.setEnabled(false);
            btnCalcola.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (oraa.getText().toString().isEmpty()||codicearea.getText().toString().isEmpty()) {
                        Toast.makeText(AcquistaTicket.this,"Inserire tutti i campi",Toast.LENGTH_LONG).show();
                        return;
                    }else {
                        final String Targa = listaauto.getSelectedItem().toString();
                        OraDa = orada.getText().toString();
                        OraA = Integer.parseInt(oraa.getText().toString().trim());
                        final double Durata = OraA;
                        CodiceArea = codicearea.getText().toString().trim();
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
                                    out.writeUTF(Targa);
                                    out.flush();
                                    out.writeUTF(CodiceArea);
                                    out.flush();
                                    out.writeUTF(OraDa);
                                    out.flush();
                                    out.writeDouble(Durata);
                                    out.flush();
                                    out.writeUTF(Username);
                                    out.flush();
                                    out.writeUTF(Password);
                                    out.flush();
                                    CostoTicket = in.readDouble();
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            costoTotale.setText(String.valueOf(CostoTicket));
                                            btnAcquista.setEnabled(true);
                                        }
                                    });
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                    }
                }
            });
            btnAcquista.setOnClickListener(new View.OnClickListener() {
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
                                out.writeUTF("acquistasend");
                                out.flush();
                                final String confirm = in.readUTF();
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (confirm.equals("ok")) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(AcquistaTicket.this);
                                            builder.setCancelable(true);
                                            builder.setTitle("Acquisto effettuato");
                                            builder.setMessage("Ticket acquistato correttamente");
                                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    // NUOVA PAGINA DI CONTROLLO DEL TICKET
                                                    ComponentName componentName = new ComponentName(AcquistaTicket.this, NotifyScheduler.class);
                                                    JobInfo info = new JobInfo.Builder(123,componentName)
                                                            .setRequiresCharging(false)
                                                            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                                                            .setPersisted(true)
                                                            .setPeriodic(15 * 60 * 1000)
                                                            .build();
                                                    JobScheduler scheduler = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
                                                    int result = scheduler.schedule(info);
                                                    if(result==JobScheduler.RESULT_SUCCESS){
                                                        Toast.makeText(AcquistaTicket.this,"Notifica avviata",Toast.LENGTH_LONG).show();
                                                    }else{
                                                        Toast.makeText(AcquistaTicket.this,"Errore avvio notifica",Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });
                                            AlertDialog dialog = builder.create();
                                            dialog.show();
                                        }else{
                                            Toast.makeText(AcquistaTicket.this,"Impossibile inserire l'acquisto",Toast.LENGTH_LONG).show();
                                            return;
                                        }
                                    }
                                });
                                out.close();
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
