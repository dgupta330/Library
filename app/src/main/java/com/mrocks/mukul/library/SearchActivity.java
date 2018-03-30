package com.mrocks.mukul.library;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
    EditText name,email,mobile,search;
    Button searchbtn;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        name=findViewById(R.id.etname_search);
        email = findViewById(R.id.etemail_search);
        mobile = findViewById(R.id.etmobile_search);
        search = findViewById(R.id.etsearch_search);
        searchbtn= findViewById(R.id.btnsearch_search);
        databaseHelper = new DatabaseHelper(this);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor =databaseHelper.getData(search.getText().toString());
                name.setText("");
                mobile.setText("");
                email.setText("");


                while(cursor.moveToNext())
                {
                    Toast.makeText(SearchActivity.this,"Contact Searched",Toast.LENGTH_SHORT).show();

                    name.setText(cursor.getString(1));
                    mobile.setText(cursor.getString(2));
                    email.setText(cursor.getString(3));

                    return;

                }


                Toast.makeText(SearchActivity.this,"No result found",Toast.LENGTH_SHORT).show();

            }
        });


    }
}
