package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doctorschedule.AdaptersView.AdapterClinic;
import com.example.doctorschedule.AdaptersView.AdapterEspecialidade;
import com.example.doctorschedule.PagesConstructor.ClinicaObject;
import com.example.doctorschedule.PagesConstructor.EspecialidadeModel;
import com.example.doctorschedule.User.MainPageUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProfessionalAreas extends AppCompatActivity {

    ImageView backboardProf;

    RecyclerView recyclerViewEsp;
    private String url = "https://doctor-schedule-api.herokuapp.com/especialidades";
    AdapterEspecialidade adapter;

    ArrayList<EspecialidadeModel> especiaList;


  //  ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional_areas);

        backboardProf = findViewById(R.id.prof_area_back);
        recyclerViewEsp = findViewById(R.id.recycleViewArea);

        recyclerViewEsp.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterEspecialidade();
        recyclerViewEsp.setAdapter(adapter);
        especiaList = new ArrayList<>();
        getDataEspcial();

    }

    private void getDataEspcial() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Carregando..");
        progressDialog.show();
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        EspecialidadeModel especialidadeModel = new EspecialidadeModel();
                        especialidadeModel.setNome_especialidade(jsonObject.getString("nome_especialidade"));
                        especialidadeModel.setNomeEClininca(jsonObject.getJSONObject("clinica").getString("nome_clinica"));
                        especialidadeModel.setEspEnd(jsonObject.getJSONObject("clinica").getString("endereco"));
                        especialidadeModel.setEspETelefone(jsonObject.getJSONObject("clinica").getString("telefone"));
                        especialidadeModel.setEspEmail(jsonObject.getJSONObject("clinica").getString("email"));
                        especiaList.add(especialidadeModel);
                    }
                }
                catch (JSONException e){
                    Toast.makeText(ProfessionalAreas.this,"Um erro ocorreu no Json",Toast.LENGTH_SHORT).show();
                }
                adapter.setEspData(especiaList);
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ProfessionalAreas.this,"Um erro ocorreu",Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    public void backuserMain(View view) {
        this.finish();
    }
}