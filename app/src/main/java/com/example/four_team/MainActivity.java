package com.example.four_team;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
//    private Button recruit_btn; //모집하기 버튼 ID
//    private Button place_btn; // 장소게시판 버튼 ID
//    private Button freeboard_btn; //자유게시판 버튼 ID
    private Button login_btn; //로그인 버튼 ID
    private Button sign_btn; //회원가입 버튼 ID

    //로그인화면에서
    private EditText login_email, login_password;
    private Button login_button;        //로그인화면에서의 로그인완료버튼
    private String email, password;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //모집하기 버튼에 대한 화면전환
//        recruit_btn = findViewById(R.id.recruit_btn);
//        recruit_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, recruitActivity.class); //모집하기 activity에 대한 화면전환 객체
//                startActivity(intent); //액티비티 이동.
//            }
//        });
//        //장소게시판 버튼에 대한 화면전환
//        place_btn = findViewById(R.id.place_btn);
//        place_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, PlaceActivity.class); //장소게시판 activity에 대한 화면전환 객체
//                startActivity(intent); //액티비티 이동.
//            }
//        });
//        //자유게시판 버튼에 대한 화면전환
//        freeboard_btn = findViewById(R.id.freeboard_btn);
//        freeboard_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, freeboardActivity.class); //자유게시판 activity에 대한 화면전환 객체
//                startActivity(intent); //액티비티 이동.
//            }
//        });

        //로그인 버튼에 대한 화면전환
        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, loginActivity.class); //activity에 대한 화면전환 객체
                startActivity(intent); //액티비티 이동
            }
        });


        //회원가입 버튼에 대한 화면전환
        sign_btn = findViewById(R.id.sign_btn);
        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class); //자유게시판 activity에 대한 화면전환 객체
                startActivity(intent); //액티비티 이동.
            }
        });
    
    }


//        @Override
//        public void onComplete(@NonNull Task<Void> task) {
//            if (task.isSuccessful()) {
//                Log.d(TAG, "Email sent.");
//            }
//        }
//    });

}