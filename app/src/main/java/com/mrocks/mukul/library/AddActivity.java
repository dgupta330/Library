package com.mrocks.mukul.library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mrocks.mukul.library.DatabaseHelper.CustomerDatabaseHelper;
import com.mrocks.mukul.library.Models.Customer;


public class AddActivity extends AppCompatActivity {
    EditText id,name,mobile,area,company,planid,plandetail;
    Button add;
    CustomerDatabaseHelper databaseHelper;
    private String TAG="AddActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        id=findViewById(R.id.etid_add);
        name=findViewById(R.id.etname_add);
        area = findViewById(R.id.etarea_add);
        mobile = findViewById(R.id.etmobile_add);
        company=findViewById(R.id.etcompany_add);
        planid = findViewById(R.id.etplanid_add);
        plandetail = findViewById(R.id.etplandetail_add);
        add= findViewById(R.id.btnadd_add);
        databaseHelper = new CustomerDatabaseHelper(this);

        // Log.i(TAG,"onCreate");


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Log.i(TAG,"onclick invoke");

                Customer customer = new Customer(id.getText().toString(),name.getText().toString(),
                        mobile.getText().toString(),area.getText().toString(),
                        company.getText().toString(),planid.getText().toString(),plandetail.getText().toString());
                boolean result= databaseHelper.insert(customer);

                if(result ==true)
                    Toast.makeText(AddActivity.this,"Contact Added",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddActivity.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });
        //Log.i(TAG,"add completed");
        id.setText("");
        name.setText("");
        mobile.setText("");
        area.setText("");
        company.setText("");
        planid.setText("");
        plandetail.setText("");
    }
}
