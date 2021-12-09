package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doctorschedule.PagesConstructor.SpecialtyModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends AppCompatActivity {

    ListView medicos;
    String escolhaFeitaMed;
    List<String> medDados = new ArrayList<String>();
   // private String url = "https://doctor-schedule-api.herokuapp.com/medicos";
    String recId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        medicos = findViewById(R.id.ListViewMedicos);

        recId = getIntent().getStringExtra("idmedico");
        getDataMed();

    }

    public void BackSchedulingMedicos(View v){
        this.finish();
    }

    private void getDataMed() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Carregando..");
        progressDialog.show();
        String url = "https://doctor-schedule-api.herokuapp.com/medicos/" + recId;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObjectSpec = response.getJSONObject(i);
                        SpecialtyModel specialtyModelMed = new SpecialtyModel();
                        specialtyModelMed.setNome_medico(jsonObjectSpec.getString("nome"));
                        medDados.add(specialtyModelMed.getNome_medico());
                    }
                } catch (JSONException e) {
                    Toast.makeText(Doctor.this, "Um erro ocorreu no Json", Toast.LENGTH_SHORT).show();
                }
                ArrayAdapter<String> adapterSpec = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, medDados);
                medicos.setAdapter(adapterSpec);
                medicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        escolhaFeitaMed = adapterSpec.getItem(position).toString();

                        Intent escolhaMed = new Intent(getApplicationContext(), Scheduling.class);
                        escolhaMed.putExtra("med", escolhaFeitaMed);
                        setResult(RESULT_OK, escolhaMed);
                        finish();
                    }
                });
                progressDialog.dismiss();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Doctor.this, "Um erro ocorreu", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}