package com.example.four_team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class loginActivity extends AppCompatActivity {

    private TextView login_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_end=findViewById(R.id.login_email);

        Intent intent = getIntent();
        String email = intent.getStringExtra("str");

        login_end.setText(email);
    }
}