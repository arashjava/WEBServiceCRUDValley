package com.example.arash.mywebservice;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CompanyAdd extends AppCompatActivity {

    Button button;
    EditText companyName, accountNo;

    Boolean edtRecord = false;

    String server_url = "http://192.168.1.2/FinAddCompany.php";

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company);

        button = (Button) findViewById(R.id.bn);
        companyName = (EditText) findViewById(R.id.companyName);
        accountNo = (EditText) findViewById(R.id.accountNo);

        builder = new AlertDialog.Builder(CompanyAdd.this);

        // this part is for edit company
        Intent intent = getIntent();
        String comName = intent.getStringExtra("companyName");
        final String companyId = intent.getStringExtra("companyId");
        String accNo = intent.getStringExtra("accountNo");


        if (comName != null) {                                       //  this means edit the record
            edtRecord = true;
            server_url = "http://192.168.1.2/FinEdtCompany.php";
            companyName.setText(comName);
            accountNo.setText(accNo);
        }
        //       Toast.makeText(this, "server_url : "+ server_url, Toast.LENGTH_SHORT).show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String company_name, account_no;
                company_name = companyName.getText().toString();
                account_no = accountNo.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                builder.setTitle("Server Response");
                                builder.setMessage("Response : " + response);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        companyName.setText("");
                                        accountNo.setText("");
                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                finish();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CompanyAdd.this, "Error happend in CompanyAdd", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("companyName", company_name);
                        params.put("accountNo", account_no);
//                        System.out.println("company is : "+ company_name);
                        if (edtRecord) {
                            //                           System.out.println("companyId is : "+ companyId);
                            params.put("companyCode", companyId);
                        }
                        return params;
                    }
                };

                MySingleton.getInstance(CompanyAdd.this).addToRequestQue(stringRequest);
            }
        });
    }
}
