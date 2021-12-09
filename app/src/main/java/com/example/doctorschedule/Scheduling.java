package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Scheduling extends AppCompatActivity {

    //variable

    DatePickerDialog.OnDateSetListener setListener;
    TextView horainicio, horafinal;
    CardView dateChooseCard, ecolhaHoraInit, ecolhaHoraFinal;
    TextView dateChoiseText, espEcolhida, medicoselected;
    String[] horaConsulta;
    String[] minutoConsulta;
    String[] periodoConsulta;
    CardView especialidade_card;
    CardView medic_card, clinicaCard;
    String date;
    String formatDate;
    LocalStorage localStorage;
    String url = "https://doctor-schedule-api.herokuapp.com/login";
    List<SpecialtyModel> escTipo = new ArrayList<>();
    String url2 = "https://doctor-schedule-api.herokuapp.com/medicos";
    String url3 = "https://doctor-schedule-rails.herokuapp.com/api/v1/patient/appointments?token=";
    TextView clinicName, endClinic;
    String rec, recCl;
   private String recebe, recClinica, recTOken;
    List<SpecialtyModel> clinicaListName = new ArrayList<>();


    static final int ACTIVITY_2_REQUEST = 1;
    static final int ACTIVITY_3_REQUEST = 2;
    static final int ACTIVITY_4_REQUEST = 3;
    String espec;
    String clinicSec;

    int hinit,mininit, hfinal, minfinal;

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
        clinicaCard = findViewById(R.id.medic_Clinic);
        clinicName = findViewById(R.id.MedicClinic);
        endClinic = findViewById(R.id.MedicClinicEnd);
        ecolhaHoraInit = findViewById(R.id.choosehourInit);
        ecolhaHoraFinal = findViewById(R.id.ecolhaHoraFinal);


        Calendar calendar = Calendar.getInstance();

        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        horainicio = findViewById(R.id.haourinit);
        horafinal = findViewById(R.id.hourfinal);

        localStorage = new LocalStorage(Scheduling.this);

        recTOken = getIntent().getStringExtra("tok");

        url3 += recTOken;


        dateChooseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Scheduling.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        dateChoiseText.setText(date);
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

        clinicaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clinicSec = medicoselected.getText().toString();
                if (clinicSec.isEmpty()) {
                    clinicName.setError(null);
                    Toast.makeText(getApplicationContext(), "O tipo de especialista deve ser preenchido primeiro", Toast.LENGTH_SHORT).show();

                } else {

                    Intent medicClnic = new Intent(getApplicationContext(), ClinicList.class);
                    startActivityForResult(medicClnic.putExtra("idclinic", recClinica), ACTIVITY_4_REQUEST);
                }
            }
        });


        ecolhaHoraInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        Scheduling.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hinit = hourOfDay;
                        mininit = minute;

                        Calendar calendar1 = Calendar.getInstance();

                        calendar1.set(0, 0, 0,hinit,mininit);
                        horainicio.setText(DateFormat.format("hh:mm aa", calendar1));

                    }
                }, 12, 0,false
                );
                timePickerDialog.updateTime(hinit,mininit);
                timePickerDialog.show();
            }
        });

        ecolhaHoraFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog2 = new TimePickerDialog(
                        Scheduling.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hfinal = hourOfDay;
                        minfinal = minute;

                        Calendar calendar2 = Calendar.getInstance();

                        calendar2.set(0, 0, 0,hfinal,minfinal);
                        horafinal.setText(DateFormat.format("hh:mm aa", calendar2));

                    }
                }, 12, 0,false
                );
                timePickerDialog2.updateTime(hfinal,minfinal);
                timePickerDialog2.show();
            }
        });

    }

    public void BackApp(View v) {
        this.finish();
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

        JSONObject params = new JSONObject();
        try {
            params.put("dia_consulta", dateChoiseText.getText().toString());
            params.put("hora_inicio", horainicio.getText().toString());
            params.put("hora_final", horafinal.getText().toString());
            params.put("id_medico", recebe.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String data = params.toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http = new Http(Scheduling.this, url3);
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

                                Toast.makeText(getApplicationContext(), "Cadastro concluido", Toast.LENGTH_SHORT).show();
                                 Intent userpag = new Intent(getApplicationContext(), ScheduleDone.class);
                                startActivity(userpag);
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

                recCl = medicoselected.getText().toString();
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
                                specialtyModelMed.setNome_medico(jsonObjectSpec.getString("nome"));
                                specialtyModelMed.setId_clinica(jsonObjectSpec.getJSONObject("especialidade").getString("id_clinica"));
                                clinicaListName.add(specialtyModelMed);
                                if (recCl.matches(clinicaListName.get(i).getNome_medico())) {
                                    recCl = clinicaListName.get(i).getId_clinica();

                                    recClinica = recCl.toString();
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
        } else if (requestCode == ACTIVITY_4_REQUEST) {
            if (resultCode == RESULT_OK) {
                String resultado3 = data.getStringExtra("clinicName");
                clinicName.setText(resultado3);
                String resultado4 = data.getStringExtra("EndClinic");
                endClinic.setText(resultado4);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void NextStepsc(View view) {
        SendConsult();
        Intent proximo = new Intent(getApplicationContext(), ScheduleDone.class);
        startActivity(proximo);

    }

}