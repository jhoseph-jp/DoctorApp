package com.example.doctorschedule.AdaptersView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorschedule.MedicalType;
import com.example.doctorschedule.PagesConstructor.ClinicaObject;
import com.example.doctorschedule.PagesConstructor.SpecialtyModel;
import com.example.doctorschedule.R;

import java.util.ArrayList;

public class AdapterSpec extends RecyclerView.Adapter<AdapterSpec.ViewSpecHolder>{

    ArrayList<SpecialtyModel> speclist;
    Context context;
    public AdapterSpec() {
        speclist = new ArrayList<>();
    }

    public void setSpecData(ArrayList<SpecialtyModel> speclist){
        this.speclist = speclist;
    }


    @NonNull
    @Override
    public AdapterSpec.ViewSpecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View specView =  layoutInflater.inflate(R.layout.custom_list_specialty_card,parent,false);
        return new AdapterSpec.ViewSpecHolder(specView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSpec.ViewSpecHolder holder, int position) {
        SpecialtyModel specialtyModel = speclist.get(position);
        holder.name_spec.setText(specialtyModel.getEspecialidade());

        holder.name_spec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MedicalType.class);

                i.putExtra("esp",speclist.toString());

                context.startActivity(i) ;
            }
        });

    }

    @Override
    public int getItemCount() {
        return speclist.size();
    }

    public class ViewSpecHolder extends RecyclerView.ViewHolder {

        TextView name_spec;

        public ViewSpecHolder(@NonNull View itemView) {
            super(itemView);

          //  name_spec = itemView.findViewById(R.id.specialty_name);
            name_spec = itemView.findViewById(R.id.listfuncao);

        }
    }
}
