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

       private List<ClinicaObject> clinicaObjectlist;

    public AdapterClinic(List<ClinicaObject> clinicaObjectList) {
        this.clinicaObjectlist = clinicaObjectList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custum_list_clinic,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClinicaObject clinicaObject = clinicaObjectlist.get(position);
        holder.clinicaNome.setText(clinicaObject.getNome_clinica());
        holder.clinicaEnd.setText(clinicaObject.getEndereco());
        holder.clinicaTel.setText(clinicaObject.getTelefone());
        holder.clinicaEmail.setText(clinicaObject.getEmail());
    }

    @Override
    public int getItemCount() {
        return clinicaObjectlist.size();
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
