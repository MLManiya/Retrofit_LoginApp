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

import com.example.retrofit_loginapp.Modal.LoginData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Activity extends AppCompatActivity {
    EditText email,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InstanceClass.CallApi().loginuser(email.getText().toString(),password.getText().toString()).enqueue(new Callback<LoginData>() {
                    @Override
                    public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                        if(response.body().getConnection()==1)
                        {
                            if(response.body().getResult()==1)
                            {
                                Toast.makeText(Login_Activity.this,"Loggedin Successfully",Toast.LENGTH_LONG).show();
                                Log.d("RRR", "onResponse: "+response.body().getConnection());
                                editor.putInt("login",1);
                                editor.putInt("sellerid", Integer.parseInt(response.body().getRegister().getId()));
                                editor.putString("sellername",response.body().getRegister().getName());
                                editor.putString("selleremail",response.body().getRegister().getEmail());
                                editor.commit();

                                Intent intent = new Intent(Login_Activity.this, Main_Page.class);
//                                intent.putExtra("email",email.getText().toString());
//                                intent.putExtra("password",password.getText().toString());
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(Login_Activity.this,"Login Fail",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Login_Activity.this, Register_Activity.class);
                                startActivity(intent);
                            }
                        }
                        else
                        {
                            Toast.makeText(Login_Activity.this,"Something Went Wrong",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginData> call, Throwable t) {
                        Log.d("RRR", "onFailure: "+t.getLocalizedMessage());
                    }
                });
            }
        });
    }
}