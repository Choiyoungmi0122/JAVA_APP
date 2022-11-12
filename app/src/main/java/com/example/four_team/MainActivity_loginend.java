package com.example.four_team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity_loginend extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_loginend);

        mFirebaseAuth = FirebaseAuth.getInstance();

        Button btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //로그아웃 하기
                mFirebaseAuth.signOut();

                Intent intent = new Intent(MainActivity_loginend.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //탈퇴처리
        //mFirebaseAuth.getCurrentUser().delete();
    }
}