package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.doctorschedule.AdaptersView.AdapterSpec;
import com.example.doctorschedule.PagesConstructor.ClinicaObject;
import com.example.doctorschedule.PagesConstructor.EspecialidadeModel;
import com.example.doctorschedule.PagesConstructor.SpecialtyModel;
import com.google.android.material.card.MaterialCardView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MedicalType extends AppCompatActivity {

    ImageView specialty_back;
    RecyclerView RecyclerViewSpec;
    private String url = "https://doctor-schedule-api.herokuapp.com/medicos";
    AdapterSpec adapterSpec;
    ListView funcao;

    //ArrayList<SpecialtyModel> specialtyModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_type);

        specialty_back = findViewById(R.id.specialty_back);
      //  RecyclerViewSpec = findViewById(R.id.RecyclerViewSpec);

        //RecyclerViewSpec.setLayoutManager(new LinearLayoutManager(this));
       // adapterSpec = new AdapterSpec();
       // RecyclerViewSpec.setAdapter(adapterSpec);
        //specialtyModels = new ArrayList<>();
        funcao = findViewById(R.id.listfuncao);

        //String r = getIntent().getStringExtra("esp");

       // Toast.makeText(this, r, Toast.LENGTH_SHORT).show();




       // getDataSpec();

        ArrayList<String> equipes = new ArrayList<>();
        equipes.add("Exemple 1");
        equipes.add("Exemple 2");
        equipes.add("Exemple 3");

        ArrayAdapter<String> adapterfuncao = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, equipes);
        funcao.setAdapter(adapterfuncao);

        funcao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), position, Toast.LENGTH_SHORT).show();

                //Intent escolhaEsp = new Intent(getApplicationContext(),Scheduling.class);
               // escolhaEsp.putExtra("esp", position);
              //  startActivity(escolhaEsp);
            }
        });


        specialty_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent specBack = new Intent(getApplicationContext(), Scheduling.class);
                startActivity(specBack);
            }
        });
    }

    /*private void getDataSpec(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Carregando..");
        progressDialog.show();
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObjectSpec = response.getJSONObject(i);
                        SpecialtyModel specialtyModel = new SpecialtyModel();
                        specialtyModel.setEspecialidade(jsonObjectSpec.getJSONObject("especialidade").getString("nome_especialidade"));
                        specialtyModels.add(specialtyModel);
                    }
                }
                catch (JSONException e){
                    Toast.makeText(MedicalType.this,"Um erro ocorreu no Json",Toast.LENGTH_SHORT).show();
                }
                adapterSpec.setSpecData(specialtyModels);
                adapterSpec.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MedicalType.this,"Um erro ocorreu",Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }*/

}