package com.example.arash.mywebservice;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class CompanyList extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Company> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list);

        updateData();

        Toast.makeText(this, "Click on company to Edit or Remove it.", Toast.LENGTH_SHORT).show();

    }

    private void updateData() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        BackgroundTask backgroundTask = new BackgroundTask(CompanyList.this);

        final Context ctx = this;
        backgroundTask.getList(new BackgroundTask.arrayCallBack() {
            @Override
            public void onSuccess(ArrayList<Company> companies) {
                adapter = new RecyclerAdapter(companies, ctx);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(CompanyList.this, "Error happend ..." + msg, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();
  //      adapter.notifyDataSetChanged();
    }


}
