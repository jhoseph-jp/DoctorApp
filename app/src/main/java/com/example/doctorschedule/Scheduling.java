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
    TextView dateChoiseText, espEcolhida, medicoselected;
    String[] horaConsulta;
    String[] minutoConsulta;
    String[] periodoConsulta;
    CardView especialidade_card;
    CardView medic_card;

    static final int ACTIVITY_2_REQUEST = 1;
    static final int ACTIVITY_3_REQUEST = 2;

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

    public void BackApp(View v) {
        this.finish();
    }

    public void EnterEsp(View v) {

        Intent medicaltype = new Intent(getApplicationContext(), MedicalType.class);
        startActivityForResult(medicaltype, ACTIVITY_2_REQUEST);

    }

    public void EnterDoc(View v) {
        Intent medicalprof = new Intent(getApplicationContext(), Doctor.class);
        startActivityForResult(medicalprof, ACTIVITY_3_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_2_REQUEST) {
            if (resultCode == RESULT_OK) {
                String resultado = data.getStringExtra("espe");
                espEcolhida.setText(resultado);
            }
        }
       else if (requestCode == ACTIVITY_3_REQUEST) {
            if (resultCode == RESULT_OK) {
                String resultado2 = data.getStringExtra("med");
                medicoselected.setText(resultado2);
            }
        }
       else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}