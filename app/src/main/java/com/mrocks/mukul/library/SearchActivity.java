package com.mrocks.mukul.library;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mrocks.mukul.library.DatabaseHelper.CustomerDatabaseHelper;

public class SearchActivity extends AppCompatActivity {
    EditText search,id,name,mobile,area,company,planid,plandetail;
    Button searchbtn;
    CustomerDatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        id=findViewById(R.id.etid_search);
        name=findViewById(R.id.etname_search);
        area = findViewById(R.id.etarea_search);
        mobile = findViewById(R.id.etmobile_search);
        company=findViewById(R.id.etcompany_search);
        planid = findViewById(R.id.etplanid_search);
        plandetail = findViewById(R.id.etplandetail_search);
        searchbtn= findViewById(R.id.btnsearch_search);
        search=findViewById(R.id.etsearch_search);

        databaseHelper = new CustomerDatabaseHelper(this);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor =databaseHelper.getData(Integer.parseInt(search.getText().toString()));
                name.setText("");
                mobile.setText("");
                area.setText("");


                while(cursor.moveToNext())
                {
                    Toast.makeText(SearchActivity.this,"Contact Searched",Toast.LENGTH_SHORT).show();

                    id.setText(cursor.getString(0));
                    name.setText(cursor.getString(1));
                    mobile.setText(cursor.getString(2));
                    area.setText(cursor.getString(3));
                    company.setText(cursor.getString(4));
                    planid.setText(cursor.getString(5));
                    plandetail.setText(cursor.getString(6));

                    return;

                }


                Toast.makeText(SearchActivity.this,"No result found",Toast.LENGTH_SHORT).show();

            }
        });


    }
}
