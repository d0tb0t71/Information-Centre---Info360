package com.example.informationcentre;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterContact extends RecyclerView.Adapter<AdapterContact.MyViewHolder> {

    Context context;
    ArrayList<ModelContact> list;

    public AdapterContact(Context context, ArrayList<ModelContact> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.contact_sample_view,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ModelContact modelContact = list.get(position);
        holder.contact_mobile.setText(modelContact.getMobile());
        holder.contact_name.setText(modelContact.getName());

        holder.call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                String number= modelContact.getMobile();
                callIntent.setData(Uri.parse("tel:" + number));
                context.startActivity(callIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView contact_name,contact_mobile;
        ImageView call_btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            contact_name = itemView.findViewById(R.id.contact_name);
            contact_mobile = itemView.findViewById(R.id.contact_mobile);
            call_btn = itemView.findViewById(R.id.call_btn);

        }
    }
}
