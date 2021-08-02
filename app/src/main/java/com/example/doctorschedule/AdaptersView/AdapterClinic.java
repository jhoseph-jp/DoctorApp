package com.example.doctorschedule.AdaptersView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorschedule.Clinics;
import com.example.doctorschedule.PagesConstructor.ClinicaObject;
import com.example.doctorschedule.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterClinic extends RecyclerView.Adapter<AdapterClinic.ViewHolder>{
        private Context context;
       private List<ClinicaObject> mydata;

    public AdapterClinic(Context context, List<ClinicaObject> clinicaObjectList) {
        this.context = context;
        this.mydata = clinicaObjectList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater= LayoutInflater.from(context);
        v = inflater.inflate(R.layout.custum_list_clinic,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //ClinicaObject clinicaObject = mydata.get(position);
        holder.clinicaNome.setText(mydata.get(position).getNome_clinica());
        holder.clinicaEnd.setText(mydata.get(position).getEndereco());
        holder.clinicaTel.setText(mydata.get(position).getTelefone());
        holder.clinicaEmail.setText(mydata.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return mydata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView clinicaNome, clinicaEnd, clinicaTel, clinicaEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            clinicaNome = itemView.findViewById(R.id.clinic_name);
            clinicaEnd = itemView.findViewById(R.id.clinic_address);
            clinicaTel = itemView.findViewById(R.id.clinic_phone);
            clinicaEmail = itemView.findViewById(R.id.clinic_email);
        }
    }

}
