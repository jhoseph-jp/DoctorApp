package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.doctorschedule.AdaptersView.AdapterClinic;
import com.example.doctorschedule.PagesConstructor.ClinicaObject;
import com.example.doctorschedule.User.MainPageUser;
import org.json.JSONArray;
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


    List<ClinicaObject> clinicaObjectList = new ArrayList<>();


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
        recyclerView = findViewById(R.id.recycle);

        //Tarefa tarefa = new Tarefa();
        //tarefa.execute();


        //Lastagem clinicas
         this.criar_clinicas();

        AdapterClinic adapter = new AdapterClinic(this, clinicaObjectList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

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

                    ClinicaObject model = new ClinicaObject();

                    model.setNome_clinica(jsonObject.getString("nome_clinica"));
                    model.setEndereco(jsonObject.getString("endereco"));
                    model.setTelefone(jsonObject.getString("telefone"));
                    model.setEmail(jsonObject.getString("email"));


                    clinicaObjectList.add(model);
                }
                progressDialog.hide();
            } catch (Exception e) {
                Toast.makeText(Clinics.this, "Falha na consulta", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
            //PutDataIntoRecycleView(clinicaObjectList);
        }

    }

   /* private void PutDataIntoRecycleView(List<ClinicaObject>clinicaObjectList) {
        //configurar o recyclerView
        AdapterClinic adapter = new AdapterClinic(this, clinicaObjectList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }*/
}

