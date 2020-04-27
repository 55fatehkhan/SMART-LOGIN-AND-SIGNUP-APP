package com.m.login_signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.LoginFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn_Form extends AppCompatActivity {
    EditText txtEmail, txtPassword;
    Button btn_login;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in__form);

        getSupportActionBar().setTitle("LOGIN HERE");

        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btn_login = (Button) findViewById(R.id.btn_login);

        firebaseAuth = FirebaseAuth.getInstance();


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(LogIn_Form.this, "Enter Email Address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(LogIn_Form.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (password.length()<6){
                    Toast.makeText(LogIn_Form.this, "Password too Short", Toast.LENGTH_SHORT).show();

                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LogIn_Form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                               startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    Toast.makeText(LogIn_Form.this, "Login Successful", Toast.LENGTH_SHORT).show();


                                } else {

                                    Toast.makeText(LogIn_Form.this, "Login Failed or User not Found", Toast.LENGTH_SHORT).show();

                                }

                            }
                        });





            }
        });








    }

    public void btn_signupForm(View view) {
        startActivity(new Intent(getApplicationContext(), Signup_Form.class));
    }
}
