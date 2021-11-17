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

public class SignUp4th extends AppCompatActivity {
    ProgressDialog progressDialog;
    ImageView back_SignUp4Btn;
    Button SignUp4thNextBtn;
    TextInputEditText fone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up4th);

        back_SignUp4Btn = findViewById(R.id.back_SignUp4Btn);
        SignUp4thNextBtn = findViewById(R.id.SignUp4thNextBtn);
        fone = findViewById(R.id.txtFone);

        String namePg4 = getIntent().getStringExtra("fullName");
        String cpfPg4 = getIntent().getStringExtra("cpf");
        String rgPg4 = getIntent().getStringExtra("rg");
        String emailPg4 = getIntent().getStringExtra("emailPerson");
        String telefone = getIntent().getStringExtra("fone");
        String passPg4 = getIntent().getStringExtra("pass");
        String repassPg4 = getIntent().getStringExtra("confrimPass");
        String datePg4 = getIntent().getStringExtra("nascimento");
        String generoPg4 = getIntent().getStringExtra("genero");
        String cepPg4 = getIntent().getStringExtra("cep");
        String endPg4 = getIntent().getStringExtra("end");
        String numPg4 = getIntent().getStringExtra("num");
        String estadoPg4 = getIntent().getStringExtra("estado");
        String cidadePg4 = getIntent().getStringExtra("cidade");

        Toast.makeText(this, endPg4, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, estadoPg4, Toast.LENGTH_SHORT).show();


    }

    public void BackPage3 (View v){
        this.finish();
    }

    public void completeform(View v){

        Intent finish = new Intent(getApplicationContext(),OnBoarding.class);



        startActivity(finish);


    }

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
    }//FIM ASYNCTASK
}