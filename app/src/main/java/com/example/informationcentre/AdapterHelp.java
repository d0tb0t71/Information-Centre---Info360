package com.example.informationcentre;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterHelp extends RecyclerView.Adapter<AdapterHelp.MyViewHolder> {

    Context context;
    ArrayList<ModelHelp> list;

    public AdapterHelp(Context context, ArrayList<ModelHelp> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.help_sample_card,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ModelHelp modelHelp = list.get(position);
        holder.help_name.setText(modelHelp.getName());
        holder.help_mobile.setText(modelHelp.getMobile());
        holder.help_mssg.setText(modelHelp.getMssg());

        holder.call_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                String number= modelHelp.getMobile();
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

        TextView help_name,help_mobile,help_mssg;
        ImageView call_help;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            help_name=itemView.findViewById(R.id.help_name);
            help_mobile=itemView.findViewById(R.id.help_mobile);
            help_mssg=itemView.findViewById(R.id.help_mssg);
            call_help=itemView.findViewById(R.id.call_help);


        }
    }
}
