package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
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
    RecyclerView recyclerView;
    private String url = "https://doctor-schedule-api.herokuapp.com/clinicas";
    AdapterClinic adapter;

    ArrayList<ClinicaObject> linicList;


    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinics);

        clinicsback = findViewById(R.id.clinics_back);
        recyclerView = findViewById(R.id.RecyclerViewClinics);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterClinic();
        recyclerView.setAdapter(adapter);
        linicList = new ArrayList<>();
        getData();

    }

    private void getData() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Carregando..");
        progressDialog.show();
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        ClinicaObject clinicaObject = new ClinicaObject();
                        clinicaObject.setNome_clinica(jsonObject.getString("nome_clinica"));
                        clinicaObject.setEndereco(jsonObject.getString("endereco"));
                        clinicaObject.setTelefone(jsonObject.getString("telefone"));
                        clinicaObject.setEmail(jsonObject.getString("email"));
                        linicList.add(clinicaObject);
                    }
                }
                catch (JSONException e){
                    Toast.makeText(Clinics.this,"Um erro ocorreu no Json",Toast.LENGTH_SHORT).show();
                }
                adapter.setData(linicList);
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Clinics.this,"Um erro ocorreu",Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    public void BackmainUserpage(View view) {
        this.finish();
    }
}

