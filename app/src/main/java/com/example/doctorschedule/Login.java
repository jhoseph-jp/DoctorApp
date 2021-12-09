package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.doctorschedule.User.MainPageUser;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    ImageView backLoginOnB1Btn;
    Button SignInBtn;
    Button LoginBtn;
    ProgressBar loginProgress;
    LocalStorage localStorage;
    TextInputEditText emailLogin, passLogin;
    String url = "https://doctor-schedule-api.herokuapp.com/login";
    String email, senha, idget;
    ProgressDialog progressDialog;
    private String tokenUser, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //vincular variael
        backLoginOnB1Btn = findViewById(R.id.backLoginOnB1Btn);
        LoginBtn = findViewById(R.id.LoginBtn);
        SignInBtn = findViewById(R.id.SignInBtn);
        loginProgress = findViewById(R.id.login_progressBar);
        emailLogin = findViewById(R.id.txtEmailLogin);
        passLogin = findViewById(R.id.txtSenhaLogin);

        localStorage = new LocalStorage(Login.this);

        //chamar botao


        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckLogin();
            }
        });

        SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callSignupPage = new Intent(getApplicationContext(), SignUp.class);
                startActivity(callSignupPage);
            }
        });
    }

    public void BackBoard(View v){
        this.finish();
    }

    private void CheckLogin(){
        email = emailLogin.getText().toString();
        senha = passLogin.getText().toString();

        if (email.isEmpty() || senha.isEmpty()){
            Toast.makeText(this, "Email ou senha é necessario!", Toast.LENGTH_SHORT).show();
        } else{
            SendLogin();
        }
    }

    private void SendLogin(){
        progressDialog = new ProgressDialog(Login.this);
        progressDialog.setMessage("Aguarde...Fazendo login");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        JSONObject params = new JSONObject();
        try {
            params.put("email", email);
            params.put("senha", senha);
        }catch (JSONException e){
            e.printStackTrace();
        }
        String data = params.toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http = new Http(Login.this, url);
                http.setMethod("post");
                http.setData(data);
                http.send();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code = http.getStatusCode();
                        if (code == 200){
                            try {
                                JSONObject response = new JSONObject(http.getReponse());
                                String token = response.getString("token");
                                localStorage.setToken(token);

                                if (response.getJSONObject("user").getString("role").equals("doctor")){
                                    String tokenMed = response.getString("tokenHash");

                                    Intent doctorDash = new Intent(getApplicationContext(), DoctorDashboard.class);
                                    doctorDash.putExtra("tokenmed", tokenMed);
                                    setResult(RESULT_OK, doctorDash);
                                    progressDialog.hide();
                                    startActivity(doctorDash);
                                    finish();
                                }

                                else {

                                    id = response.getJSONObject("user").getString("id").toString();
                                   tokenUser = response.getString("tokenHash");
                                    Intent userpag = new Intent(getApplicationContext(), MainPageUser.class);
                                    userpag.putExtra("iduser", id);
                                    userpag.putExtra("tokenUser", tokenUser);
                                    setResult(RESULT_OK, userpag);
                                    progressDialog.hide();
                                    startActivity(userpag);
                                    finish();
                                }
                            } catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                        else if (code == 422){
                            try {
                                progressDialog.hide();
                                JSONObject reponse = new JSONObject(http.getReponse());
                                String msg = reponse.getString("message");
                                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                        else if (code == 401){
                            try {
                                progressDialog.hide();
                                JSONObject reponse = new JSONObject(http.getReponse());
                                String msg = reponse.getString("message");
                                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                        else{
                            progressDialog.hide();
                            Toast.makeText(Login.this, "Erro" + code, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();
    }
}