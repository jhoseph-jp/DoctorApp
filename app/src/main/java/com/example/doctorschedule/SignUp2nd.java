package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctorschedule.Common.TestSignUp;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class SignUp2nd extends AppCompatActivity {

    ImageView back_SignUp2Btn;
    TextView account2;
    Button SignUp2ndNextBtn;
    RadioGroup generoEsc;
    RadioButton selecionagenero;
    DatePicker nascimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2nd);

        back_SignUp2Btn = findViewById(R.id.back_SignUp2Btn);
        SignUp2ndNextBtn = findViewById(R.id.SignUp2ndNextBtn);
        account2 = findViewById(R.id.acc2);
        nascimento = findViewById(R.id.nascimento);
        generoEsc = findViewById(R.id.generoEsc);
    }

    public void BackPage1(View v) {
        this.finish();
    }

    public void callSign2toNext(View view) {
        Intent callNextStep3 = new Intent(getApplicationContext(), SignUp3rd.class);



        if (!validadeGenero() | !validadeNascimento()){
         return;
        }

        selecionagenero = findViewById(generoEsc.getCheckedRadioButtonId());
        String generoescolhido = selecionagenero.getText().toString();

        int day = nascimento.getDayOfMonth();
        int month = nascimento.getMonth();
        int year = nascimento.getYear();

        String date = day + "/" + month + "/" + year;


        String namePg2 = getIntent().getStringExtra("fullName");
        String cpfPg2 = getIntent().getStringExtra("cpf");
        String rgPg2 = getIntent().getStringExtra("rg");
        String emailPg2 = getIntent().getStringExtra("emailPerson");
        String passPg2 = getIntent().getStringExtra("pass");
        String repassPg2 = getIntent().getStringExtra("confrimPass");

        callNextStep3.putExtra("fullName", namePg2);
        callNextStep3.putExtra("cpf",cpfPg2);
        callNextStep3.putExtra("rg",rgPg2);
        callNextStep3.putExtra("emailPerson",emailPg2);
        callNextStep3.putExtra("pass",passPg2);
        callNextStep3.putExtra("confrimPass",repassPg2);
         callNextStep3.putExtra("genero",generoescolhido);
         callNextStep3.putExtra("nascimento", date);


        Pair[] pairs = new Pair[3];

        pairs[0] = new Pair<View, String>(back_SignUp2Btn, "transitionBackPage");
        pairs[1] = new Pair<View, String>(account2, "transitionTittleName");
        pairs[2] = new Pair<View, String>(SignUp2ndNextBtn, "transitionNextBtn");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp2nd.this, pairs);
        startActivity(callNextStep3, options.toBundle());
    }

    private boolean validadeGenero() {
        if (generoEsc.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Selecionar um genero", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validadeNascimento(){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = nascimento.getYear();
        int validAge = currentYear - userAge;

        if(validAge < 18){
            Toast.makeText(this, "Você precisa ser maioir de 18 anos!", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }
}
