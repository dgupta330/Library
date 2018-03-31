package com.mrocks.mukul.library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mrocks.mukul.library.DatabaseHelper.CustomerDatabaseHelper;
import com.mrocks.mukul.library.Models.Customer;

public class UpdateActivity extends AppCompatActivity {
    EditText id,name,mobile,area,company,planid,plandetail;
    Button update;
    CustomerDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        id=findViewById(R.id.etid_update);
        name=findViewById(R.id.etname_update);
        area = findViewById(R.id.etarea_update);
        mobile = findViewById(R.id.etmobile_update);
        company=findViewById(R.id.etcompany_update);
        planid = findViewById(R.id.etplanid_update);
        plandetail = findViewById(R.id.etplandetail_update);
        update = findViewById(R.id.btn_update);
        databaseHelper = new CustomerDatabaseHelper(this);
        name.setText("");
        mobile.setText("");
        area.setText("");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Customer customer = new Customer(id.getText().toString(),name.getText().toString(),
                        mobile.getText().toString(),area.getText().toString(),
                        company.getText().toString(),planid.getText().toString(),plandetail.getText().toString());

                databaseHelper.updateData(customer);

                Toast.makeText(UpdateActivity.this,"Customer Updated",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
