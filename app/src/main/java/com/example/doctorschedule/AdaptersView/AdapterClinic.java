//package com.example.doctorschedule.AdaptersView;
/*
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorschedule.PagesConstructor.ClinicaObject;
import com.example.doctorschedule.R;

import java.util.List;

public class AdapterClinic extends RecyclerView.Adapter<AdapterClinic.ViewHolder>{

    LayoutInflater inflater;
    List<ClinicaObject> clinicaObject;

    public AdapterClinic(Context ctx, List<ClinicaObject> clinicaObject){
        this.inflater = LayoutInflater.from(ctx);
        this.clinicaObject = clinicaObject;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custum_list_clinic,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.clinicaNome.setText(clinicaObject.get(position).getNome_clinica());
        holder.clinicaEnd.setText(clinicaObject.get(position).getEndereco());
        holder.clinicaTel.setText(clinicaObject.get(position).getTelefone());
        holder.clinicaEmail.setText(clinicaObject.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return 2;
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
*/