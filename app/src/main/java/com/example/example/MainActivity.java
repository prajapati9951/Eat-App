package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    Button btnSignUp,btnSignIn;
    TextView tvSlogan;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignUp=(Button) findViewById(R.id.btnSignUp);
        //btnSignIn=(Button) findViewById(R.id.btnSignIn);
        submit=(Button)findViewById(R.id.btnlogin);
        tvSlogan=findViewById(R.id.slogan);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/NABILA.TTF");
        tvSlogan.setTypeface(face);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(i);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(i);
            }
        });
    }
}
