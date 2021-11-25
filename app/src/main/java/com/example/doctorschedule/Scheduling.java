package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doctorschedule.PagesConstructor.SpecialtyModel;
import com.example.doctorschedule.User.MainPageUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Scheduling extends AppCompatActivity {

    //variable

    DatePickerDialog.OnDateSetListener setListener;
    NumberPicker hora, minuto, tipodn;
    CardView dateChooseCard;
    TextView dateChoiseText, espEcolhida, medicoselected;
    String[] horaConsulta;
    String[] minutoConsulta;
    String[] periodoConsulta;
    CardView especialidade_card;
    CardView medic_card;
    String date;
    String formatDate;
    LocalStorage localStorage;
    String url = "https://doctor-schedule-api.herokuapp.com/login";
    List<SpecialtyModel> escTipo = new ArrayList<>();
    String url2 = "https://doctor-schedule-api.herokuapp.com/medicos";
    TextView textView;
    String rec;
    String recebe;
    String veriEsp;

    static final int ACTIVITY_2_REQUEST = 1;
    static final int ACTIVITY_3_REQUEST = 2;
    String espec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduling);

        dateChooseCard = findViewById(R.id.choose_date);
        dateChoiseText = findViewById(R.id.date_choise);
        horaConsulta = getResources().getStringArray(R.array.horario);
        minutoConsulta = getResources().getStringArray(R.array.minutos);
        periodoConsulta = getResources().getStringArray(R.array.periodo);
        especialidade_card = findViewById(R.id.especialidade_card);
        medic_card = findViewById(R.id.medic_card);
        espEcolhida = findViewById(R.id.especi_text);
        medicoselected = findViewById(R.id.Medicname);
        textView = findViewById(R.id.textView);

        Calendar calendar = Calendar.getInstance();

        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        hora = findViewById(R.id.haour);
        minuto = findViewById(R.id.min);
        tipodn = findViewById(R.id.type_choise);

        hora.setMinValue(0);
        hora.setMaxValue(11);
        hora.setDisplayedValues(horaConsulta);
        minuto.setMinValue(0);
        minuto.setMaxValue(2);
        minuto.setDisplayedValues(minutoConsulta);
        tipodn.setMinValue(0);
        tipodn.setMaxValue(3);
        tipodn.setDisplayedValues(periodoConsulta);

        localStorage = new LocalStorage(Scheduling.this);

       /*if (!CheckDate() | !CheckEsp() | !CheckMed()){
            return;
        }*/

        dateChooseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Scheduling.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        date = day + "/" + month + "/" + year;
                        dateChoiseText.setText(date);
                        // CheckDate();
                    }
                }, year, month, day);
                datePickerDialog.show();
            }

        });

        especialidade_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent medicaltype = new Intent(getApplicationContext(), MedicalType.class);
                startActivityForResult(medicaltype, ACTIVITY_2_REQUEST);
            }
        });

        medic_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                espec = espEcolhida.getText().toString();
                if (espec.isEmpty()) {
                    espEcolhida.setError(null);
                    Toast.makeText(getApplicationContext(), "O tipo de especialidade deve ser preenchido primeiro", Toast.LENGTH_SHORT).show();

                } else {

                    Intent medicalprof = new Intent(getApplicationContext(), Doctor.class);
                    startActivityForResult(medicalprof.putExtra("idmedico", recebe), ACTIVITY_3_REQUEST);
                }
            }
        });

    }

    public void BackApp(View v) {
        this.finish();
    }

    /* private void CheckDate(){
        String val = dateChoiseText.getText().toString();
        if (val.isEmpty()){
            dateChoiseText.setError("O campo deve ser preenchido");
        }else{

        }

     }
 */
    private void CheckEsp() {
        espec = espEcolhida.getText().toString();
        if (espec.isEmpty()) {
            Toast.makeText(this, "O tipo de especialidade deve ser preenchido p", Toast.LENGTH_SHORT).show();

        } else {

        }

    }

    private void CheckMed() {
        String val = medicoselected.getText().toString();
        if (val.isEmpty()) {
            medicoselected.setError("O medico deve ser preenchido");
        } else {
            medicoselected.setError(null);
        }

    }

    private void SendConsult() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatDate = formatter.format(dateChoiseText);

        hora.getValue();
        minuto.getValue();
        tipodn.getDisplay().toString();

        JSONObject params = new JSONObject();
        try {
            params.put("dia_consulta", formatDate);
            params.put("hora_inicio", hora);
            params.put("hora_final", minuto);
            params.put("id_medico", espEcolhida);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String data = params.toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http = new Http(Scheduling.this, url);
                http.setMethod("post");
                http.setData(data);
                http.send();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code = http.getStatusCode();
                        if (code == 200) {
                            try {
                                JSONObject response = new JSONObject(http.getReponse());
                                String token = response.getString("token");
                                localStorage.setToken(token);

                                Toast.makeText(getApplicationContext(), "Cadastro concluido", Toast.LENGTH_SHORT).show();
                                // Intent userpag = new Intent(getApplicationContext(), MainPageUser.class);
                                //startActivity(userpag);
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else if (code == 422) {
                            try {
                                JSONObject reponse = new JSONObject(http.getReponse());
                                String msg = reponse.getString("message");
                                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else if (code == 401) {
                            try {
                                JSONObject reponse = new JSONObject(http.getReponse());
                                String msg = reponse.getString("message");
                                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(Scheduling.this, "Erro" + code, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_2_REQUEST) {
            if (resultCode == RESULT_OK) {
                String resultado = data.getStringExtra("espe");
                espEcolhida.setText(resultado);

                rec = espEcolhida.getText().toString();
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Carregando..");
                progressDialog.show();
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url2, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObjectSpec = response.getJSONObject(i);
                                SpecialtyModel specialtyModelMed = new SpecialtyModel();
                                specialtyModelMed.setId(jsonObjectSpec.getString("id"));
                                specialtyModelMed.setNome_especialidade(jsonObjectSpec.getJSONObject("especialidade").getString("nome_especialidade"));
                                escTipo.add(specialtyModelMed);
                                if (rec.matches(escTipo.get(i).getNome_especialidade())) {
                                    rec = escTipo.get(i).getId();

                                    recebe = rec.toString();

                                    //Intent passIdMed = new Intent(getApplicationContext(), Doctor.class);
                                    // passIdMed.putExtra("idm",recebe);
                                }
                            }

                        } catch (JSONException e) {
                            Toast.makeText(Scheduling.this, "Um erro ocorreu no Json", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(Scheduling.this, "Um erro ocorreu", Toast.LENGTH_SHORT).show();
                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(jsonArrayRequest);
            }
        } else if (requestCode == ACTIVITY_3_REQUEST) {
            if (resultCode == RESULT_OK) {
                String resultado2 = data.getStringExtra("med");
                medicoselected.setText(resultado2);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}