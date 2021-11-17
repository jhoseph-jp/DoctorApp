package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctorschedule.User.MainPageUser;

import java.util.Calendar;

public class Scheduling extends AppCompatActivity {

    //variable

    DatePickerDialog.OnDateSetListener setListener;
    NumberPicker hora, minuto, tipodn;
    CardView dateChooseCard;
    TextView dateChoiseText, espEcolhida;
    String[] horaConsulta;
    String[] minutoConsulta;
    String[] periodoConsulta;
    ImageView backtoboardS;
    CardView especialidade_card;
    CardView medic_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduling);

        backtoboardS = findViewById(R.id.scheduling_back);
        dateChooseCard = findViewById(R.id.choose_date);
        dateChoiseText = findViewById(R.id.date_choise);
        horaConsulta = getResources().getStringArray(R.array.horario);
        minutoConsulta = getResources().getStringArray(R.array.minutos);
        periodoConsulta = getResources().getStringArray(R.array.periodo);
        especialidade_card = findViewById(R.id.especialidade_card);
        medic_card = findViewById(R.id.medic_card);
        espEcolhida = findViewById(R.id.especi_text);

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

        //String espcial = getIntent().getStringExtra("esp");
        //espEcolhida.setText(espcial);


        backtoboardS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backtoboardS = new Intent(getApplicationContext(), MainPageUser.class);
                startActivity(backtoboardS);
            }
        });

        especialidade_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent medicaltype = new Intent(getApplicationContext(), MedicalType.class);
                startActivity(medicaltype);
            }
        });

        medic_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent medicalprof = new Intent(getApplicationContext(), Doctor.class);
                startActivity(medicalprof);
            }
        });

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



    }
}