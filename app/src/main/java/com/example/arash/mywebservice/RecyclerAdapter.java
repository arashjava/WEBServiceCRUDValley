package com.example.arash.mywebservice;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by arash on 17/11/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    ArrayList<Company> arrayList= new ArrayList<>();
    Context ctx;

    public RecyclerAdapter(ArrayList<Company> arrayList, Context ctx){

        this.arrayList= arrayList;
        this.ctx= ctx;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        MyViewHolder myViewHolder= new MyViewHolder(view);
        return myViewHolder;
    }

    @Override   //  you can assign value to the text views
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.companyId.setText(arrayList.get(position).getCompId());
        holder.companyName.setText(arrayList.get(position).getCompName());
        holder.accountNo.setText(arrayList.get(position).getAcctNo());


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, CompanyAct.class);
                intent.putExtra("compId", holder.companyId.getText().toString() );
                intent.putExtra("compName", holder.companyName.getText().toString() );
                intent.putExtra("acctNo", holder.accountNo.getText().toString() );
                ctx.startActivity(intent);

            }
        });
    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView companyId, companyName, accountNo;

        RelativeLayout relativeLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            companyId= (TextView)itemView.findViewById(R.id.compId);
            companyName= (TextView)itemView.findViewById(R.id.compName);
            accountNo= (TextView)itemView.findViewById(R.id.acctNumber);

            relativeLayout= (RelativeLayout)itemView.findViewById(R.id.relativeLayout);

        }
    }



}
