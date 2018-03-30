package com.mrocks.mukul.library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText name,email,mobile;
    Button update;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        name=findViewById(R.id.etname_update);
        email = findViewById(R.id.etemail_update);
        mobile = findViewById(R.id.etmobile_update);
        update = findViewById(R.id.btn_update);
        databaseHelper = new DatabaseHelper(this);
        name.setText("");
        mobile.setText("");
        email.setText("");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.updateData(name.getText().toString(),mobile.getText().toString(),email.getText().toString());
                Toast.makeText(UpdateActivity.this,"Contact Updated",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
