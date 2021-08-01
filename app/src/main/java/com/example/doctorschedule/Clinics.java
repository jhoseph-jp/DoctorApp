package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctorschedule.AdaptersView.AdapterClinic;
import com.example.doctorschedule.AdaptersView.AdapterClinic;
import com.example.doctorschedule.PagesConstructor.ClinicaObject;
import com.example.doctorschedule.User.MainPageUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Clinics extends AppCompatActivity {

    ImageView clinicsback;
    TextView clinic, clinicAddress, clinicPhone, ClinicEmail;
    RecyclerView recyclerView;
    private String JSON_URL = "https://doctor-schedule-api.herokuapp.com/clinicas";
  //  AdapterClinic adapterClinic;


    List<ClinicaObject> clinicaObjectList = new ArrayList<>();

    CardView cardItem;

    // Button buscarcep;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinics);

        clinicsback = findViewById(R.id.clinics_back);
        clinic = findViewById(R.id.clinic_name);
        clinicAddress = findViewById(R.id.clinic_address);
        clinicPhone = findViewById(R.id.clinic_phone);
        ClinicEmail = findViewById(R.id.clinic_email);
        // buscarcep = findViewById(R.id.buscar);
        //  cardItem = findViewById(R.id.card_view_clinics);
        recyclerView = findViewById(R.id.recycle);


     //   clinicaObjectList = new ArrayList<>();


     //   Tarefa tarefa = new Tarefa();
     //   tarefa.execute();


        //Lastagem clinicas
          this.criar_clinicas();

        AdapterClinic adapter = new AdapterClinic(clinicaObjectList);


        //configurar o recyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

       // adapter.notifyDataSetChanged();

        clinicsback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backtoboarclin = new Intent(getApplicationContext(), MainPageUser.class);
                startActivity(backtoboarclin);
            }
        });

    }

    public void criar_clinicas(){
        ClinicaObject clinicaObject = new ClinicaObject("clinica teste1", "iji 41","441255","clinica@gmail.com");
        clinicaObjectList.add(clinicaObject);

        clinicaObject = new ClinicaObject("clinica teste2", "iji 42","441255","clinica@gmail.com");
        clinicaObjectList.add(clinicaObject);

        clinicaObject = new ClinicaObject("clinica teste3", "iji 43","441255","clinica@gmail.com");
        clinicaObjectList.add(clinicaObject);

        clinicaObject = new ClinicaObject("clinica teste4", "iji 44","441255","clinica@gmail.com");
        clinicaObjectList.add(clinicaObject);

        clinicaObject = new ClinicaObject("clinica teste5", "iji 45","441255","clinica@gmail.com");
        clinicaObjectList.add(clinicaObject);
    }

    private class Tarefa extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(JSON_URL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                InputStream is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer out = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    out.append(line + "\n");
                }
                is.close();
                return
                        out.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Clinics.this);
            progressDialog.setMessage("Aguarde...carregando dados");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            //Este método é acionado após doInBackground acessar o serviço web e baixar os dados da consulta
            try {
                List<ClinicaObject> clinicaObjectList = new ArrayList<>();
                JSONArray jsonArray = null;
                JSONObject jsonObject = null;

                jsonArray = new JSONArray(s);

                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);

                    ClinicaObject clinicaObject = new ClinicaObject();

                    clinicaObject.setId(jsonObject.getString("id"));
                    clinicaObject.setNome_clinica(jsonObject.getString("nome_clinica"));
                    clinicaObject.setEndereco(jsonObject.getString("endereco"));
                    clinicaObject.setTelefone(jsonObject.getString("telefone"));
                    clinicaObject.setEmail(jsonObject.getString("email"));

                    clinicaObjectList.add(clinicaObject);
                }
                progressDialog.hide();
            } catch (Exception e) {
                Toast.makeText(Clinics.this, "Falha na consulta", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
           // PutDataIntoRecycleView(clinicaObjectList);
        }

        private void showdata() {
            if (clinicaObjectList != null){
                for (ClinicaObject clinicaObject: clinicaObjectList){
                    clinic.append(clinicaObject.getNome_clinica()+"\n");
                    clinicAddress.append(clinicaObject.getEndereco()+"\n");
                    clinicPhone.append(clinicaObject.getTelefone()+"\n");
                    ClinicEmail.append(clinicaObject.getEmail()+"\n");
                }
            }

        }

    }

   /* private void PutDataIntoRecycleView(List<ClinicaObject> clinicaObjectList) {

        AdapterClinic adapter = new AdapterClinic();
        //configurar o recyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }*/
}

  /*  public void buca(View view) {
        Tarefa tarefa = new Tarefa();
        tarefa.execute("https://doctor-schedule-api.herokuapp.com/clinicas");
    }*/


