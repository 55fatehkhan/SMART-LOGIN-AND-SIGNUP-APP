package com.m.login_signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class Signup_Form extends AppCompatActivity {
    EditText txtEmail, txtPassword, txtConfirmPassword, txtFullName, txtUsername;
    Button btn_register;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);

        getSupportActionBar().setTitle("SIGN-UP HERE");

        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtConfirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
        txtFullName = (EditText) findViewById(R.id.txtFullName);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        btn_register = (Button) findViewById(R.id.btn_register);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        firebaseAuth = FirebaseAuth.getInstance();




        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            String email = txtEmail.getText().toString().trim();
            String password = txtPassword.getText().toString().trim();
            String confirmPassword = txtConfirmPassword.getText().toString().trim();

            if(TextUtils.isEmpty(email)){
                Toast.makeText(Signup_Form.this, "Enter Email Address", Toast.LENGTH_SHORT).show();
                return;
            }

           if (TextUtils.isEmpty(password)){
               Toast.makeText(Signup_Form.this, "Enter Password", Toast.LENGTH_SHORT).show();
               return;
           }

           if(TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(Signup_Form.this, "Please Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
           }

           if (password.length()<6){
               Toast.makeText(Signup_Form.this, "Password too Short", Toast.LENGTH_SHORT).show();

           }

           progressBar.setVisibility(View.VISIBLE);

           if (password.equals(confirmPassword)){

               firebaseAuth.createUserWithEmailAndPassword(email, password)
                       .addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {

                               progressBar.setVisibility(View.GONE);


                               if (task.isSuccessful()) {

                                   startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                   Toast.makeText(Signup_Form.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                               } else {

                                   Toast.makeText(Signup_Form.this, "Authentication Failed", Toast.LENGTH_SHORT).show();

                               }


                           }
                       });


           }

           else {
               Toast.makeText(Signup_Form.this, "Password Doesn't Match", Toast.LENGTH_SHORT).show();
           }







            }
        });


    }
}
