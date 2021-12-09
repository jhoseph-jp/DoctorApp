package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doctorschedule.PagesConstructor.ClinicaObject;
import com.example.doctorschedule.PagesConstructor.SpecialtyModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClinicList extends AppCompatActivity {

    ListView clinicList;
    String idClninc;
    List<ClinicaObject> clinicL = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_list);

        clinicList = findViewById(R.id.ListViewClinincx);

        idClninc = getIntent().getStringExtra("idclinic");

        getDataClinicList();
    }

    public void BackSchedulingClinicList(View v){
        this.finish();
    }

    private void getDataClinicList() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Carregando..");
        progressDialog.show();
        String url = "https://doctor-schedule-api.herokuapp.com/especialidades/" + idClninc;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObjectSpec = response.getJSONObject(i);
                        ClinicaObject clinicaObject =new ClinicaObject();
                        clinicaObject.setNome_clinica(jsonObjectSpec.getJSONObject("clinica").getString("nome_clinica"));
                        clinicaObject.setEndereco(jsonObjectSpec.getJSONObject("clinica").getString("endereco"));
                        clinicL.add(clinicaObject);
                    }
                } catch (JSONException e) {
                    Toast.makeText(ClinicList.this, "Um erro ocorreu no Json", Toast.LENGTH_SHORT).show();
                }

                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_2, android.R.id.text1, clinicL) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        ClinicaObject clinc = clinicL.get(position);
                        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                        TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                        text1.setText(clinc.getNome_clinica());
                        text2.setText(clinc.getEndereco());

                        Intent intent = new Intent(getApplicationContext(),Scheduling.class);
                        intent.putExtra("clinicName", text1.getText().toString());
                        intent.putExtra("EndClinic", text2.getText().toString());
                        setResult(RESULT_OK, intent);

                        return view;
                    }
                };
                clinicList.setAdapter(adapter);

                clinicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        finish();
                    }
                });

                progressDialog.dismiss();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ClinicList.this, "Um erro ocorreu", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}