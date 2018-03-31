package com.mrocks.mukul.library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mrocks.mukul.library.DatabaseHelper.CustomerDatabaseHelper;

public class DelActivity extends AppCompatActivity {
    EditText search ;
    Button delete;
    CustomerDatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del);
        search= findViewById(R.id.etdel_del);
        delete=findViewById(R.id.btndel_del);
        databaseHelper=new CustomerDatabaseHelper(this);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.deleteData(Integer.parseInt(search.getText().toString()));
                Toast.makeText(DelActivity.this,"Customer Deleted",Toast.LENGTH_SHORT).show();
            }
        });

        search.setText("");
    }
}
