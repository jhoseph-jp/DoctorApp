package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doctorschedule.PagesConstructor.Users;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SignUp4th extends AppCompatActivity {
    ProgressDialog progressDialog;
    ImageView back_SignUp4Btn;
    Button SignUp4thNextBtn;
    TextInputEditText fone;
    String namePg4, cpfPg4, rgPg4, emailPg4,passPg4, datePg4, generoPg4, cepPg4, endPg4, numPg4, estadoPg4, cidadePg4;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up4th);

        back_SignUp4Btn = findViewById(R.id.back_SignUp4Btn);
        SignUp4thNextBtn = findViewById(R.id.SignUp4thNextBtn);
        fone = findViewById(R.id.txtFone);

        namePg4 = getIntent().getStringExtra("fullName");
        cpfPg4 = getIntent().getStringExtra("cpf");
        rgPg4 = getIntent().getStringExtra("rg");
        emailPg4 = getIntent().getStringExtra("emailPerson");
        fone.getText().toString();
        passPg4 = getIntent().getStringExtra("pass");
        datePg4 = getIntent().getStringExtra("nascimento");
        generoPg4 = getIntent().getStringExtra("genero");
        cepPg4 = getIntent().getStringExtra("cep");
        endPg4 = getIntent().getStringExtra("end");
        numPg4 = getIntent().getStringExtra("num");
        estadoPg4 = getIntent().getStringExtra("estado");
        cidadePg4 = getIntent().getStringExtra("cidade");

    }

    public void BackPage3 (View v){
        this.finish();
    }

    public void completeform(View v){

        progressDialog = new ProgressDialog(SignUp4th.this);
        progressDialog.setMessage("Aguarde...carregando dados");
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        String url = "https://doctor-schedule-api.herokuapp.com/user";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,response -> Toast.makeText(this, "Cadastro concluido", Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()){
            //Add parameters
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("nome",namePg4);
                params.put("cpf",cpfPg4);
                params.put("rg",rgPg4);
                params.put("email",emailPg4);
                params.put("password",passPg4);
                params.put("telefone",fone.getText().toString());
                params.put("data_nascimento",datePg4);
                params.put("genero",generoPg4);
                params.put("cep",cepPg4);
                params.put("endereco",endPg4);
                params.put("numnero",numPg4);
                params.put("estado",estadoPg4);
                params.put("cidade",cidadePg4);
                return params;
            }

        };

        requestQueue = Volley.newRequestQueue(SignUp4th.this);
        requestQueue.add(stringRequest);
        progressDialog.hide();
        Intent finish = new Intent(getApplicationContext(),OnBoarding.class);

        startActivity(finish);


    }
/*
    protected class fillUsers extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
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
            progressDialog = new ProgressDialog(SignUp4th.this);
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
                Toast.makeText(SignUp4th.this, "Falha na consulta", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
                // e.printStackTrace();
            }
        }

        private void parseJSON(String data) {
            try {
                if (data.contains("erro")) {
                    Toast.makeText(SignUp4th.this, "Falha na consulta", Toast.LENGTH_SHORT).show();
                } else {
                    JSONObject jsonObject = new JSONObject(data);//JSONObject recebe o JSON da consulta
                    //Depois de pegar o conteúdo no JSON, preenche os textviews
                    Users users = new Users();
                    users.setNome(jsonObject.getString("nome"));
                    users.setCpf(jsonObject.getString("cpf"));
                    users.setNome(jsonObject.getString("rg"));
                    users.setNome(jsonObject.getString("email"));
                    users.setNome(jsonObject.getString(""));
                    users.setNome(jsonObject.getString("telefone"));
                    users.setNome(jsonObject.getString("data_nascimento"));
                    users.setNome(jsonObject.getString("genero"));
                    users.setNome(jsonObject.getString("cep"));
                    users.setNome(jsonObject.getString("endereco"));
                    users.setNome(jsonObject.getString("numero"));
                    users.setNome(jsonObject.getString("estado"));
                    users.setNome(jsonObject.getString("cidade"));
                }
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }
    }//FIM ASYNCTASK*/
}