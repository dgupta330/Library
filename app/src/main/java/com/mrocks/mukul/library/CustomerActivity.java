package com.mrocks.mukul.library;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CustomerActivity extends AppCompatActivity {
    Button addbtn,delbtn,updatebtn,searchbtn;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // addbtn = findViewById(R.id.add_btn);
        searchbtn=findViewById(R.id.search_btn);
        delbtn = findViewById(R.id.delete_btn);
        updatebtn=findViewById(R.id.update_btn);
        // databaseHelper = new DatabaseHelper(this);

      /*  addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerActivity.this,AddActivity.class);
                startActivity(intent);

            }
        });
        */
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerActivity.this, SearchActivity.class);
                startActivity(intent);

            }
        });
        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerActivity.this,DelActivity.class);
                startActivity(intent);

            }});
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerActivity.this,UpdateActivity.class);
                startActivity(intent);

            }});

    }
}
