package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doctorschedule.PagesConstructor.EspecialidadeModel;
import com.example.doctorschedule.PagesConstructor.Users;
import com.google.android.material.textfield.TextInputEditText;

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

public class SignUp3rd extends AppCompatActivity {

    ProgressDialog progressDialog;
    ImageView backSignUp3Btn;
    Button SignUp3rdNextBtn;
    TextView acc3;
    TextInputEditText cep, end, num, estado, cidade;
    private String url;

    ArrayList<Users> ceplist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up3rd);

        backSignUp3Btn = findViewById(R.id.backSignUp3Btn);
        SignUp3rdNextBtn = findViewById(R.id.SignUp3rdNextBtn);
        acc3 = findViewById(R.id.acc3);
        cep = findViewById(R.id.txtCEP);
        end = findViewById(R.id.txtAddress);
        num = findViewById(R.id.txtNumberhome);
        estado = findViewById(R.id.txtState);
        cidade = findViewById(R.id.txtCity);


    }

    public void BackPage2(View v) {
        this.finish();
    }



    public void callcepsearch(View v) {
        String passCep = cep.getText().toString();

        if(!passCep.isEmpty()) {
            consultaCep consulta = null;
            url = "https://viacep.com.br/ws/" + passCep + "/json/";

            consulta = new consultaCep();
            consulta.execute(url);
        }

        else {
            Toast.makeText(this, "Não encontrado!", Toast.LENGTH_SHORT).show();
        }


    }


    public void callSign3toNext(View view) {

        // getDatacep();

        Intent CallNextStep4 = new Intent(getApplicationContext(), SignUp4th.class);


        String namePg3 = getIntent().getStringExtra("fullName");
        String cpfPg3 = getIntent().getStringExtra("cpf");
        String rgPg3 = getIntent().getStringExtra("rg");
        String emailPg3 = getIntent().getStringExtra("emailPerson");
        String passPg3 = getIntent().getStringExtra("pass");
        String generoPg3 = getIntent().getStringExtra("genero");
        String datePg3 = getIntent().getStringExtra("nascimento");

        CallNextStep4.putExtra("fullName", namePg3);
        CallNextStep4.putExtra("cpf", cpfPg3);
        CallNextStep4.putExtra("rg", rgPg3);
        CallNextStep4.putExtra("emailPerson", emailPg3);
        CallNextStep4.putExtra("pass", passPg3);
        CallNextStep4.putExtra("genero", generoPg3);
        CallNextStep4.putExtra("nascimento", datePg3);

        CallNextStep4.putExtra("cep", cep.getText().toString());
        CallNextStep4.putExtra("end", end.getText().toString());
        CallNextStep4.putExtra("num", num.getText().toString());
        CallNextStep4.putExtra("estado", estado.getText().toString());
        CallNextStep4.putExtra("cidade", cidade.getText().toString());

        Pair[] pairs = new Pair[3];

        pairs[0] = new Pair<View, String>(backSignUp3Btn, "transitionBackPage");
        pairs[1] = new Pair<View, String>(acc3, "transitionTittleName");
        pairs[2] = new Pair<View, String>(SignUp3rdNextBtn, "transitionNextBtn");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp3rd.this, pairs);
        startActivity(CallNextStep4, options.toBundle());


    }
    // CLASSE ASYNCTASK REALIZAR CHAMADA WEB SERVICES
    protected class consultaCep extends AsyncTask<String, Void, String> {

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
            progressDialog = new ProgressDialog(SignUp3rd.this);
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
                Toast.makeText(SignUp3rd.this, "Falha na consulta", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
                // e.printStackTrace();
            }
        }

        private void parseJSON(String data) {
            try {
                if (data.contains("erro")) {
                    Toast.makeText(SignUp3rd.this, "Falha na consulta", Toast.LENGTH_SHORT).show();
                } else {
                    JSONObject jsonObject = new JSONObject(data);//JSONObject recebe o JSON da consulta
                    //Depois de pegar o conteúdo no JSON, preenche os textviews

                    end.setText(jsonObject.getString("logradouro"));
                    estado.setText(jsonObject.getString("uf"));
                    cidade.setText(jsonObject.getString("localidade"));
                }
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }
    }//FIM ASYNCTASK

}