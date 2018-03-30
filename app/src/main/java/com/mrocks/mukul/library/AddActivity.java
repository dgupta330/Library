package com.mrocks.mukul.library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddActivity extends AppCompatActivity {
    EditText name,email,mobile;
    Button add;
    DatabaseHelper databaseHelper;
    private String TAG="AddActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name=findViewById(R.id.etname_add);
        email = findViewById(R.id.etemail_add);
        mobile = findViewById(R.id.etmobile_add);
        add= findViewById(R.id.btnadd_add);
        databaseHelper = new DatabaseHelper(this);

        // Log.i(TAG,"onCreate");


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Log.i(TAG,"onclick invoke");

                boolean result= databaseHelper.insert(name.getText().toString(),mobile.getText().toString(),email.getText().toString());

                if(result ==true)
                    Toast.makeText(AddActivity.this,"Contact Added",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddActivity.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });
        //Log.i(TAG,"add completed");
        name.setText("");
        mobile.setText("");
        email.setText("");
    }
}
