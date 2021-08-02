package com.example.doctorschedule.AdaptersView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorschedule.PagesConstructor.ClinicaObject;
import com.example.doctorschedule.PagesConstructor.EspecialidadeModel;
import com.example.doctorschedule.R;

import java.util.ArrayList;

public class AdapterEspecialidade extends RecyclerView.Adapter<AdapterEspecialidade.ViewEsHolder> {

    ArrayList<EspecialidadeModel> especialidadeModelsList;

public AdapterEspecialidade(){
    especialidadeModelsList = new ArrayList<>();
}

public void setEspData(ArrayList<EspecialidadeModel> especialidadeModelsList){
    this.especialidadeModelsList = especialidadeModelsList;

}

    @NonNull
    @Override
    public ViewEsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.custom_list_area,parent,false);
        return new ViewEsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterEspecialidade.ViewEsHolder holder, int position) {
        EspecialidadeModel especialidadeModel = especialidadeModelsList.get(position);
        holder.nome_especialidade.setText(especialidadeModel.getNome_especialidade());
        holder.nomeEClininca.setText(especialidadeModel.getNomeEClininca());
        holder.espEnd.setText(especialidadeModel.getEspEnd());
        holder.espETelefone.setText(especialidadeModel.getEspETelefone());
        holder.espEmail.setText(especialidadeModel.getEspEmail());
    }

    @Override
    public int getItemCount() {
        return especialidadeModelsList.size();
    }

    public class ViewEsHolder extends RecyclerView.ViewHolder {
        TextView nome_especialidade,  nomeEClininca, espEnd, espETelefone, espEmail;
        public ViewEsHolder(@NonNull View itemView) {
            super(itemView);

            nome_especialidade = itemView.findViewById(R.id.nome_especialidade);
            nomeEClininca = itemView.findViewById(R.id.clinica_nome_area);
            espEnd = itemView.findViewById(R.id.clinica_end_area);
            espETelefone = itemView.findViewById(R.id.clinic_phone_area);
            espEmail = itemView.findViewById(R.id.clinic_email_area);


        }
    }
}
