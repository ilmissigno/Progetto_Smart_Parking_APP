package com.example.smartparkingapp;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class TicketInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_info);

        final int idticket = getIntent().getExtras().getInt("IDTicket");
        final String Targa = getIntent().getExtras().getString("Targa");
        final String CodiceArea = getIntent().getExtras().getString("CodiceArea");
        final double durata = getIntent().getExtras().getDouble("Durata");
        TextView ticketinfo = findViewById(R.id.textViewTicket);
        ticketinfo.setText("Info Ticket: ID: "+idticket+" Targa:"+Targa+" CodiceArea: "+CodiceArea+" Durata in Ore: "+durata);
        ComponentName componentName = new ComponentName(TicketInfoActivity.this, NotifyScheduler.class);
        JobInfo info = new JobInfo.Builder(123,componentName)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();
        JobScheduler scheduler = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
        int result = scheduler.schedule(info);
        if(result==JobScheduler.RESULT_SUCCESS){
            Toast.makeText(TicketInfoActivity.this,"Notifica avviata",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(TicketInfoActivity.this,"Errore avvio notifica",Toast.LENGTH_LONG).show();
        }
    }
}
