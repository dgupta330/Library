package com.mrocks.mukul.library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DelActivity extends AppCompatActivity {
    EditText search ;
    Button delete;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del);
        search= findViewById(R.id.etdel_del);
        delete=findViewById(R.id.btndel_del);
        databaseHelper=new DatabaseHelper(this);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.deleteData(search.getText().toString());
                Toast.makeText(DelActivity.this,"Contact Deleted",Toast.LENGTH_SHORT).show();
            }
        });

        search.setText("");
    }
}
