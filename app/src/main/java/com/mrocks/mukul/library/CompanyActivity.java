package com.mrocks.mukul.library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mrocks.mukul.library.Adapter.MyListAdapter;
import com.mrocks.mukul.library.DatabaseHelper.CustomerDatabaseHelper;
import com.mrocks.mukul.library.DatabaseHelper.PlanDataBaseHelper;
import com.mrocks.mukul.library.Models.Plan;

import java.util.ArrayList;


public class CompanyActivity extends AppCompatActivity {
    ListView planlist;
    public int no=0;
    private static final int RC_PHOTO_PICKER = 23;
    
    ArrayList<Plan> plandetail=new ArrayList<>();
    ImageButton addbtn;
    ImageView companyimg;
    Uri SelectedImageUri;
    Uri imagePath=null;
    PlanDataBaseHelper db;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        db = new PlanDataBaseHelper(this);


        planlist=findViewById(R.id.listView);
        addbtn=findViewById(R.id.addplanbtn);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(CompanyActivity.this);
                final View mView = getLayoutInflater().inflate(R.layout.customdialog, null);
                final EditText mcompanyname = (EditText) mView.findViewById(R.id.etcompany);
                final EditText mplanid = (EditText) mView.findViewById(R.id.etplanid);
                final EditText mplandetail = (EditText) mView.findViewById(R.id.etplandetail);
                final EditText mcustcare = (EditText) mView.findViewById(R.id.etcustomercare);
                final RadioGroup mtype=(RadioGroup) mView.findViewById(R.id.type);
                companyimg=(ImageView)mView.findViewById(R.id.companyimg_customdialog) ;
                companyimg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/jpeg");
                        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                        startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);

                    }
                });

                Button madd = (Button) mView.findViewById(R.id.btnadd);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                madd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!mcompanyname.getText().toString().isEmpty() && !mplanid.getText().toString().isEmpty())
                        {
                            int selectedid= mtype.getCheckedRadioButtonId();
                            RadioButton selectedbtn=mView.findViewById(selectedid);
                            Plan plan=new Plan(mcompanyname.getText().toString(),
                                    mplanid.getText().toString(),
                                    mplandetail.getText().toString(),
                                    mcustcare.getText().toString(),
                                    selectedbtn.getText().toString(),
                                    SelectedImageUri.toString());
                            // Inserting Plans
                            boolean res=db.addPlan(plan);

                            if(res) {
                                Toast.makeText(CompanyActivity.this,
                                        "Plan Added Successfully",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else{ Toast.makeText(CompanyActivity.this,
                                    "Plan Added failed",
                                    Toast.LENGTH_SHORT).show();

                            }

                            dialog.dismiss();
                        }else{
                            Toast.makeText(CompanyActivity.this,
                                    "Complete Details First",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

        plandetail= db.getAllPlans();
        final MyListAdapter adapter= new MyListAdapter(this,plandetail);
        planlist.setAdapter(adapter);
        planlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapter.getItem(i).getPlanid();
                final int pos=i;
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(CompanyActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog2, null);
                final EditText mcompanyname = (EditText) mView.findViewById(R.id.etdcompname);
                final EditText mplanid = (EditText) mView.findViewById(R.id.etdplanid);
                final EditText mplandetail = (EditText) mView.findViewById(R.id.etdplandetail);
                final EditText mcustcare = (EditText) mView.findViewById(R.id.etdcustcare);
                final RadioButton mtype=(RadioButton) mView.findViewById(R.id.dtype);


                mcompanyname.setText(plandetail.get(pos).getCompany().toString());
                mplanid.setText(plandetail.get(pos).getPlanid().toString());
                mplandetail.setText(plandetail.get(pos).getPlandetail().toString());
                mcustcare.setText(plandetail.get(pos).getCustomercare().toString());
                mtype.setText(plandetail.get(pos).getType().toString());
                mtype.setChecked(true);


                Button mdel = (Button) mView.findViewById(R.id.btnddel);
                Button mupdate = (Button) mView.findViewById(R.id.btndupdate);
                Button mmsg = (Button) mView.findViewById(R.id.btndmsg);
                Button mcall = (Button) mView.findViewById(R.id.btndcall);
                mdel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                          db.deletePlan((Plan) plandetail.get(pos));

                            Toast.makeText(getApplicationContext(), "Plan Deleted Successfully", Toast.LENGTH_SHORT).show();

                    }
                });
                mupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Plan plan=new Plan(mcompanyname.getText().toString(),
                                mplanid.getText().toString(),
                                mplandetail.getText().toString(),
                                mcustcare.getText().toString(),
                                mtype.getText().toString(),
                                SelectedImageUri.toString());
                        
                            db.updatePlan(plan);
                            Toast.makeText(getApplicationContext(), "Plan Updated Successfully", Toast.LENGTH_SHORT).show();
                        

                    }
                });
                mcall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:"+ Uri.encode(mcustcare.getText().toString().trim())));
                        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(callIntent);


                    }
                });
                mmsg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT, plandetail.get(pos).getData());
                            startActivity(intent);
                        }
                });
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RC_PHOTO_PICKER && resultCode==RESULT_OK)
        {   SelectedImageUri =data.getData();
            Glide.with(this).load(SelectedImageUri).into(companyimg);

        }

    }

}

