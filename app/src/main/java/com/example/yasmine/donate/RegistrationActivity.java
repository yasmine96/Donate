package com.example.yasmine.donate;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RegistrationActivity extends AppCompatActivity {

    private EditText userName, userEmail, userPassword,userConfirmPassword ,userAge ,userMobile,userLocation ;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    String email, name, age, password , confirmPassword , mobile , location;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        storageReference = firebaseStorage.getReference();




        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //Upload data to the database
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                sendEmailVerification();
                                sendUserData();
                                firebaseAuth.signOut();
                                Toast.makeText(RegistrationActivity.this, "Successfully Registered!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                            }else{
                                Toast.makeText(RegistrationActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
    }

    private Boolean validate() {
        Boolean result = false;

        name = userName.getText().toString();
        password = userPassword.getText().toString();
        email = userEmail.getText().toString();
        age = userAge.getText().toString();
        confirmPassword = userConfirmPassword.getText().toString();
        mobile = userMobile.getText().toString();
        location = userLocation.getText().toString();


        if (name.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty() || age.isEmpty()|| mobile.isEmpty() || location.isEmpty() ) {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }
        else if(!(confirmPassword.equals(password)))
        {
            Toast.makeText(RegistrationActivity.this, "Password not match!", Toast.LENGTH_SHORT).show();
        }
        else {
            result = true;
        }

        return result;
    }


    private void sendEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        sendUserData();
                        Toast.makeText(RegistrationActivity.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                    } else {
                        Toast.makeText(RegistrationActivity.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setupUIViews() {
        userName = (EditText) findViewById(R.id.etUserName);
        userPassword = (EditText) findViewById(R.id.etPassword);
        userEmail = (EditText) findViewById(R.id.etEmail);
        regButton = (Button) findViewById(R.id.etRegister);
        userLogin = (TextView) findViewById(R.id.etUserlogin);
        userAge = (EditText) findViewById(R.id.etAge);
        userConfirmPassword =(EditText) findViewById(R.id.etConfirmPassword);
        userMobile =(EditText) findViewById(R.id.etMobile_no);
        userLocation =(EditText) findViewById(R.id.etLocation);
    }


    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
    }

}

