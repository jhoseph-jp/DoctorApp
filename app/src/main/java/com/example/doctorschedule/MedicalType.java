package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class MedicalType extends AppCompatActivity {

    private String url = "https://doctor-schedule-api.herokuapp.com/medicos";
    ListView funcao;
    String escolhaFeita;
    List<String> espDados = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_type);

        funcao = findViewById(R.id.listfuncao);

        getDataSpec();
    }

    public void BackScheduling(View v) {
        this.finish();
    }

    private void getDataSpec() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Carregando..");
        progressDialog.show();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObjectSpec = response.getJSONObject(i);
                        SpecialtyModel specialtyModel = new SpecialtyModel();
                        specialtyModel.setId(jsonObjectSpec.getString("id"));
                        specialtyModel.setNome_especialidade(jsonObjectSpec.getJSONObject("especialidade").getString("nome_especialidade"));
                        espDados.add(specialtyModel.getNome_especialidade());
                    }

                } catch (JSONException e) {
                    Toast.makeText(MedicalType.this, "Um erro ocorreu no Json", Toast.LENGTH_SHORT).show();
                }
                ArrayAdapter<String> adapterSpec = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, espDados);
                funcao.setAdapter(adapterSpec);
                funcao.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        escolhaFeita = adapterSpec.getItem(position).toString();

                        Intent escolhaEsp = new Intent(getApplicationContext(), Scheduling.class);
                        escolhaEsp.putExtra("espe", escolhaFeita);
                        setResult(RESULT_OK, escolhaEsp);
                        finish();
                    }
                });
                progressDialog.dismiss();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MedicalType.this, "Um erro ocorreu", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

}