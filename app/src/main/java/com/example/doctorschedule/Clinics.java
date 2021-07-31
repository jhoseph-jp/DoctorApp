package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctorschedule.PagesConstructor.ClinicaObject;
import com.example.doctorschedule.User.MainPageUser;
import com.example.doctorschedule.WebServices.ConnectionAPI;
import com.example.doctorschedule.WebServices.ConsumirJson;

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
    ArrayAdapter<String> listAdapter;


    private List<ClinicaObject> clinicaObjectList = new ArrayList<>();

    CardView cardItem;

    Button buscarcep;

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
        buscarcep = findViewById(R.id.buscar);
        cardItem = findViewById(R.id.card_view_clinics);

        clinicsback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backtoboarclin = new Intent(getApplicationContext(), MainPageUser.class);
                startActivity(backtoboarclin);
            }
        });

    }

    private class Tarefa extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {
            String retorno = ConnectionAPI.getDados(strings[0]);

            return retorno;
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
            clinicaObjectList = ConsumirJson.jsonDados(s);
            showdata();
            progressDialog.hide();
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

    public void buca(View view) {
        Tarefa tarefa = new Tarefa();
        tarefa.execute("https://doctor-schedule-api.herokuapp.com/clinicas");
    }



    /*protected class ConsultaClinicas extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
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
        protected void onPostExecute(String dados) {
            //Este método é acionado após doInBackground acessar o serviço web e baixar os dados da consulta
            try {
                tvClininca.setText(dados);
                parseJSON(dados);
                progressDialog.hide();
            }catch (Exception e){
                Toast.makeText(Clinics.this, "Falha na consulta", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        }

        //O método abaixo serve para ler o JSON recebido e processar os itens contidos no mesmo
        private void parseJSON(String data){
            try{
                if(data.contains("erro")){
                    Toast.makeText(Clinics.this, "Falha na consulta", Toast.LENGTH_SHORT).show();
                }else {
                    JSONObject jsonObject = new JSONObject(data);//JSONObject recebe o JSON da consulta
                    //Depois de pegar o conteúdo no JSON, preenche os textviews
                    tvClininca.setText(jsonObject.getString("nome_clinica"));
                    //textViewCidade.setText(jsonObject.getString("localidade"));
                }
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }
    }//Fim Classe ConsultaClinicas*/

}