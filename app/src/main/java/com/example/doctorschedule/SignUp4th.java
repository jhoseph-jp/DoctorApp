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
}