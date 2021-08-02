package com.example.doctorschedule.AdaptersView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorschedule.PagesConstructor.ClinicaObject;
import com.example.doctorschedule.R;

import java.util.ArrayList;
import java.util.List;


public class AdapterClinic extends RecyclerView.Adapter<AdapterClinic.ViewHolder>{

    ArrayList<ClinicaObject> clinicaList;

    public AdapterClinic() {
        clinicaList = new ArrayList<>();
    }

    public void setData(ArrayList<ClinicaObject> clinicaList){
        this.clinicaList = clinicaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View clinicView =  layoutInflater.inflate(R.layout.custum_list_clinic,parent,false);
        return new ViewHolder(clinicView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClinicaObject clinicaObject = clinicaList.get(position);
        holder.nome_clinica.setText(clinicaObject.getNome_clinica());
        holder.endereco.setText(clinicaObject.getEndereco());
        holder.telefone.setText(clinicaObject.getTelefone());
        holder.email.setText(clinicaObject.getEmail());
    }

    @Override
    public int getItemCount() {
        return clinicaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nome_clinica, endereco, telefone, email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nome_clinica = itemView.findViewById(R.id.clinic_name);
            endereco = itemView.findViewById(R.id.clinic_address);
            telefone = itemView.findViewById(R.id.clinic_phone);
            email = itemView.findViewById(R.id.clinic_email);
        }
    }

}