package com.example.four_team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class loginActivity extends AppCompatActivity {
    Button sign_button, login_btn;
    private EditText email, pw;
    private TextView login_email, login_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //회원가입 버튼
        sign_button = findViewById(R.id.login_sign_button);

        sign_button.setOnClickListener(v-> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });
        //값 찾기
        email = findViewById(R.id.signEmail);
        pw = findViewById(R.id.signPW);
        //로그인 버튼

        login_btn = findViewById(R.id.login_button);

        login_btn.setOnClickListener(view -> {
            String useremail = email.getText().toString();
            login_email=findViewById(R.id.login_email);
            login_pw = findViewById(R.id.login_password);


        });


        Intent intent = getIntent();
        String email = intent.getStringExtra("str");


    }
}