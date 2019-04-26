package com.example.yasmine.donate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button login;
    private TextView userRegisteration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText)  findViewById(R.id.username_field);
        password = (EditText) findViewById(R.id.password_field);
        login = (Button) findViewById(R.id.reg_btn);
        userRegisteration = (TextView) findViewById(R.id.tv_reg);
        forgotPassword = (TextView)findViewById(R.id.tvForgotPassword);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            finish();
            startActivity(new Intent(LoginActivity.this, DonationActivity.class));
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkDetails()) {
                    validate(email.getText().toString(), password.getText().toString());
                }
            }
        });

        userRegisteration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this , RegistrationActivity.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, PasswordActivity.class));
            }
        });

    }

        private Boolean checkDetails()
        {
            Boolean result = false;

            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();

            if(userEmail.equals("") && userPassword.equals("")){
                Toast.makeText(LoginActivity.this , "please enter email and password" , Toast.LENGTH_SHORT).show();
            }

            else if(userEmail.equals("")){
                Toast.makeText(LoginActivity.this , "please enter email" , Toast.LENGTH_SHORT).show();
            }

            else if(userPassword.equals("")){
                Toast.makeText(LoginActivity.this , "please enter password" , Toast.LENGTH_SHORT).show();
            }

            else {
                result =true;
            }

            return result;
        }

        private void validate(String useremail, String userPassword) {

            progressDialog.setMessage("Wait until you are verified!");
            progressDialog.show();


                firebaseAuth.signInWithEmailAndPassword(useremail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            checkEmailVerification();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }



    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        startActivity(new Intent(LoginActivity.this, DonationActivity.class));

    }

}
