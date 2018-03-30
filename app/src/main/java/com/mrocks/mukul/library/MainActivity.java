package com.mrocks.mukul.library;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    ImageButton customer,company,graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView=findViewById(R.id.idtxt);
         mydb=new DatabaseHelper(this);
         customer=findViewById(R.id.customer_btn);
         customer.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i = new Intent(MainActivity.this,CustomerActivity.class);
                 startActivity(i);
             }
         });

        company=findViewById(R.id.company_btn);
        company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,CompanyActivity.class);
                startActivity(i);
            }
        });




    }
}
