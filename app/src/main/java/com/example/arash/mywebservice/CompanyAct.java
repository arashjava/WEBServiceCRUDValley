package com.example.arash.mywebservice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CompanyAct extends AppCompatActivity {

    Button buttonDelete, buttonEdit;
    TextView tv;

    String server_url = "http://192.168.1.2/FindDelCompany.php";
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        Intent intent= getIntent();
        final String companyId= intent.getStringExtra("compId");
        final String companyName= intent.getStringExtra("compName");
        final String accountNo= intent.getStringExtra("acctNo");

        tv= (TextView)findViewById(R.id.company);

        tv.setText("Company : " + companyName);


        buttonDelete = (Button) findViewById(R.id.compDelete);
        buttonEdit = (Button) findViewById(R.id.compEdit);

        builder = new AlertDialog.Builder(CompanyAct.this);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(CompanyAct.this, CompanyAdd.class);
                intent.putExtra("companyName", companyName);
                intent.putExtra("companyId", companyId);
                intent.putExtra("accountNo", accountNo);
 //               Toast.makeText(CompanyAct.this, "Account number here is : " +  accountNo, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                builder.setTitle("Server Response");
                                builder.setMessage("Response : " + response);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                finish();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CompanyAct.this, "Error happend...", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("company_code", companyId);
                        return params;
                    }
                };

                MySingleton.getInstance(CompanyAct.this).addToRequestQue(stringRequest);
            }
        });
    }
}

