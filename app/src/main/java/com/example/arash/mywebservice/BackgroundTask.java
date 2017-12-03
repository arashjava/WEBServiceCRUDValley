package com.example.arash.mywebservice;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by arash on 17/11/17.
 */

public class BackgroundTask {

    Context context;
    ArrayList<Company> arrayList = new ArrayList<>();

    String json_url = "http://192.168.1.2/FinListCompany.php";

    public BackgroundTask(Context context) {
        this.context = context;
    }


    //  conect to database and get the json array
    public void getList(final arrayCallBack onCallBack) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, json_url, (String) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while (count < response.length()) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                Company company = new Company(jsonObject.getString("companyId"), jsonObject.getString("companyName"), jsonObject.getString("accountNo"));
                                arrayList.add(company);
                                count++;

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        onCallBack.onSuccess(arrayList);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error...", Toast.LENGTH_LONG).show();
                error.printStackTrace();
                onCallBack.onFail(error.toString());
            }
        });

        MySingleton.getInstance(context).addToRequestQue(jsonArrayRequest);

    }

    public interface arrayCallBack {
        void onSuccess(ArrayList<Company> contacts);

        void onFail(String msg);
    }


}
