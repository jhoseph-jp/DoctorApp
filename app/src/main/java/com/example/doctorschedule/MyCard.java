package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doctorschedule.PagesConstructor.ClinicaObject;
import com.example.doctorschedule.PagesConstructor.SpecialtyModel;
import com.example.doctorschedule.PagesConstructor.Users;
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
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyCard extends AppCompatActivity {

    //Variables
    EditText nome, rg, cpf, idade, email, tel;
    ProgressDialog progressDialog;
    LocalStorage localStorage;
    private String infoUser;
    String url = "https://doctor-schedule-api.herokuapp.com/user/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_card);

        infoUser = getIntent().getStringExtra("idu");


        //return board
        nome = findViewById(R.id.name_user);
        rg = findViewById(R.id.rg_user);
        cpf = findViewById(R.id.cpf_user);
        idade = findViewById(R.id.idade_user);
        tel = findViewById(R.id.user_phone);
        email = findViewById(R.id.user_email);

        consultaUsers consulta = new consultaUsers();

        url +=infoUser;

        consulta.execute(url);

        nome.setEnabled(false);
        rg.setEnabled(false);
        cpf.setEnabled(false);
        idade.setEnabled(false);
        tel.setEnabled(false);
        email.setEnabled(false);

    }

    public void BackmainPage(View view) {
        this.finish();
    }


    protected class consultaUsers extends AsyncTask<String, Void, String> {

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
        }//FIM DOINGBACKGROUND

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MyCard.this);
            progressDialog.setMessage("Aguarde...carregando dados");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String dados) {
            try {
                parseJSON(dados);
                progressDialog.hide();

            } catch (Exception e) {
                Toast.makeText(MyCard.this, "Falha na consulta", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
                // e.printStackTrace();
            }
        }

        private void parseJSON(String data) {
            try {
                 if (data.contains("erro")) {
                    Toast.makeText(MyCard.this, "Falha na consulta", Toast.LENGTH_SHORT).show();
                } else {
                    JSONObject jsonObject = new JSONObject(data);//JSONObject recebe o JSON da consulta
                    //Depois de pegar o conteúdo no JSON, preenche os textviews

                    nome.setText(jsonObject.getString("nome"));
                    cpf.setText(jsonObject.getString("cpf"));
                    rg.setText(jsonObject.getString("rg"));
                    email.setText(jsonObject.getString("email"));
                    tel.setText(jsonObject.getString("telefone"));
                    idade.setText(jsonObject.getString("data_nascimento"));

                     String pattern = "MM-dd-yyyy";
                     SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                     String date = simpleDateFormat.format(new Date());

                   //  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
                  // simpleDateFormat.format(idade);

                   idade.setText(date);
                }
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }


    }//FIM ASYNCTASK
}