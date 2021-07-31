package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doctorschedule.Common.TestSignUp;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {

    ImageView backToOnBoardingSignUp;
    TextView accountView;
    Button SignUpFstBtn;
    TextInputLayout fullName, cpf, rg, emailPerson, pass, confrimPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        backToOnBoardingSignUp = findViewById(R.id.backToOnBoardingSignUp);
        SignUpFstBtn = findViewById(R.id.SignUpFstBtn);

        fullName = findViewById(R.id.txtFullNameSignUp);
        cpf = findViewById(R.id.txtCPF);
        rg = findViewById(R.id.txtRg);
        emailPerson = findViewById(R.id.txtEmailSignUp);
        pass = findViewById(R.id.txtPasswordSignUp);
        confrimPass = findViewById(R.id.txtConfirmPasswordSignUp);
        accountView = findViewById(R.id.createAccount);

        backToOnBoardingSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToOnB = new Intent(getApplicationContext(), OnBoarding.class);
                startActivity(backToOnB);
            }
        });

    }

    public void callNextSignUpPage(View view){
       /* if (!validateFullName() | !validatecpf() | !validatecrg() | !validateEmail() | !validatePassword()){
            return;
        }*/

        Intent callStep2 = new Intent(getApplicationContext(),SignUp2nd.class);
        Pair [] pairs = new Pair[3];

        pairs[0] = new Pair<View,String>(backToOnBoardingSignUp,"transitionBackPage");
        pairs[1] = new Pair<View,String>(accountView,"transitionTittleName");
        pairs[2] = new Pair<View,String>(SignUpFstBtn,"transitionNextBtn");

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
            startActivity(callStep2, options.toBundle());
    }
    //Validation function
    private boolean validateFullName(){
        String val = fullName.getEditText().getText().toString().trim();
        if (val.isEmpty()){
            fullName.setError("O campo não pode estar vazio");
            return false;
        }
        else{
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }

        }
    private boolean validatecpf(){
        String val = cpf.getEditText().getText().toString().trim();
        if (val.isEmpty()){
            cpf.setError("O campo não pode estar vazio");
            return false;
        }
        else{
            cpf.setError(null);
            cpf.setErrorEnabled(false);
            return true;
        }

    }
    private boolean validatecrg(){
        String val = rg.getEditText().getText().toString().trim();
        if (val.isEmpty()){
            rg.setError("O campo não pode estar vazio");
            return false;
        }
        else{
            rg.setError(null);
            rg.setErrorEnabled(false);
            return true;
        }

    }
    private boolean validateEmail(){
        String val = emailPerson.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()){
            emailPerson.setError("O campo não pode estar vazio");
            return false;
        }
        else if (!val.matches(checkEmail)){
            emailPerson.setError("Email invalido!");
            return false;
        }
        else{
            emailPerson.setError(null);
            emailPerson.setErrorEnabled(false);
            return true;
        }

    }
    private boolean validatePassword(){
        String val = pass.getEditText().getText().toString().trim();
        String checkPassword = "^" //+
                //"(?=.*[0-9])" +
        // "(?=.*[a-z]" +
        // "(?=.*[A-Z]" +
            //    "(?=.*[a-zA-Z]" +
              //  "(?=.*[@#$%^&+=]" +
            //    "(?=\\S+$)" +
            //    ".{4,}" +
             //   "$";
        ;

        if (val.isEmpty()){
            pass.setError("O campo não pode estar vazio");
            return false;
        }
        else if (!val.matches(checkPassword)){
            pass.setError("Senha deve conter 6 digitos!");
            return false;
        }
        else{
            pass.setError(null);
            pass.setErrorEnabled(false);
            return true;
        }

    }
}