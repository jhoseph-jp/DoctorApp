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
import com.example.doctorschedule.PagesConstructor.Users;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {

    ImageView backToOnBoardingSignUp;
    TextView accountView;
    Button SignUpFstBtn;
    TextInputEditText fullName, cpf, rg, emailPerson, pass, confrimPass;

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

    }

    public void BackOnBorarding( View v){
        this.finish();
    }

    public void callNextSignUpPage(View view){
       /* if (!validateFullName() | !validatecpf() | !validatecrg() | !validateEmail() | !validatePassword() | validateconfirmPassword | validPassMatches){
            return;
        }*/

        Intent callStep2 = new Intent(getApplicationContext(),SignUp2nd.class);
        callStep2.putExtra("fullName", fullName.getText().toString());
        callStep2.putExtra("cpf",cpf.getText().toString());
        callStep2.putExtra("rg",rg.getText().toString());
        callStep2.putExtra("emailPerson",emailPerson.getText().toString());
        callStep2.putExtra("pass",pass.getText().toString());
        callStep2.putExtra("confrimPass",confrimPass.getText().toString());

        Pair [] pairs = new Pair[3];

        pairs[0] = new Pair<View,String>(backToOnBoardingSignUp,"transitionBackPage");
        pairs[1] = new Pair<View,String>(accountView,"transitionTittleName");
        pairs[2] = new Pair<View,String>(SignUpFstBtn,"transitionNextBtn");

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
            startActivity(callStep2, options.toBundle());
    }
    //Validation function
    private boolean validateFullName(){
        String val = fullName.getText().toString().trim();
        if (val.isEmpty()){
            fullName.setError("O campo não pode estar vazio");
            return false;
        }
        else{
            fullName.setError(null);
            return true;
        }

        }
    private boolean validatecpf(){
        String val = cpf.getText().toString().trim();
        if (val.isEmpty()){
            cpf.setError("O campo não pode estar vazio");
            return false;
        }
        else{
            cpf.setError(null);
            return true;
        }

    }
    private boolean validatecrg(){
        String val = rg.getText().toString().trim();
        if (val.isEmpty()){
            rg.setError("O campo não pode estar vazio");
            return false;
        }
        else{
            rg.setError(null);
            return true;
        }

    }
    private boolean validateEmail(){
        String val = emailPerson.getText().toString().trim();
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
            return true;
        }

    }
    private boolean validatePassword(){
        String val = pass.getText().toString().trim();
        String checkPassword = ("^" +
               // "(?=.*[0-9])" +
         //"(?=.*[a-z])" +
         //"(?=.*[A-Z])" +
               "(?=.*[a-zA-Z])" +
              //  "(?=.*[@#$%^&+=])" +
                "(?=\\S+$)" +
               ".{4,}" +
                "$");

        if (val.isEmpty()){
            pass.setError("O campo não pode estar vazio");
            return false;
        }
        else if (!val.matches(checkPassword)){
            pass.setError("Senha deve conter o minimo de 6 digitos!");
            return false;
        }
        else{
            pass.setError(null);
            return true;
        }

    }

    private boolean validateconfirmPassword(){
        String val = confrimPass.getText().toString().trim();
        String checkPassword = ("^" +
                // "(?=.*[0-9])" +
                //"(?=.*[a-z])" +
                //"(?=.*[A-Z])" +
                "(?=.*[a-zA-Z])" +
                //  "(?=.*[@#$%^&+=])" +
                "(?=\\S+$)" +
                ".{4,}" +
                "$");

        if (val.isEmpty()){
            pass.setError("O campo não pode estar vazio");
            return false;
        }
        else if (!val.matches(checkPassword)){
            pass.setError("Senha deve conter o minimo de 6 digitos!");
            return false;
        }
        else{
            pass.setError(null);
            return true;
        }

    }

    private boolean validPassMatches(){
        String passwordS = pass.getText().toString().trim();
        String repas = confrimPass.getText().toString().trim();

        if (!passwordS.matches(repas)){
            confrimPass.setError("A senha não está igual");
            return false;
        }
        else {
            confrimPass.setError(null);
            return true;
        }
    }
}