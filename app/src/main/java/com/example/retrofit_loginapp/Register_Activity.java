package com.example.retrofit_loginapp;

import static com.example.retrofit_loginapp.Splash_Screen.editor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofit_loginapp.Modal.RegisterClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_Activity extends AppCompatActivity {
    EditText name,email,password;
    Button register,Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = findViewById(R.id.register);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        Login = findViewById(R.id.alreadylogin);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InstanceClass.CallApi().registerUser(name.getText().toString(),email.getText().toString(),password.getText().toString()).enqueue(new Callback<com.example.retrofit_loginapp.Modal.RegisterClass>() {
                    @Override
                    public void onResponse(Call<com.example.retrofit_loginapp.Modal.RegisterClass> call, Response<com.example.retrofit_loginapp.Modal.RegisterClass> response) {
                        Log.d("RRR", "onResponse: "+response.body().getConnection());
                        if(response.body().getConnection()==1)
                        {
                            if(response.body().getResult()==1)
                            {
                                Intent intent = new Intent(Register_Activity.this,Login_Activity.class);
                                editor.putInt("login",1);
                                editor.commit();
                                startActivity(intent);
                                Toast.makeText(Register_Activity.this,"Register Successfully",Toast.LENGTH_LONG).show();
                                Log.d("RRR", "onResponse: "+response.body().getConnection());
                            }
                            else
                            {
                                Toast.makeText(Register_Activity.this,"Register failed",Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(Register_Activity.this,"something went worng",Toast.LENGTH_LONG).show();
                        }
//                        name.getText().clear();
//                        email.getText().clear();
//                        password.getText().clear();
                    }

                    @Override
                    public void onFailure(Call<RegisterClass> call, Throwable t) {
                        Log.d("RRR", "onFailure: "+t.getLocalizedMessage());
                    }
                });
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register_Activity.this, Login_Activity.class);
                startActivity(intent);
            }
        });
    }
}