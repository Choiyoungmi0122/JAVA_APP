package com.example.four_team;

import static com.example.four_team.R.id.et_signEmail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class loginActivity extends AppCompatActivity
{
    private FirebaseAuth mFirebaseAuth;     //파이어베이스 인증처리
    private DatabaseReference mDatabaseRef;   //실시간 데이터베이스
    private EditText  et_email, et_pw, et_pw2;      //회원가입 입력필드

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("fourteam");       //"앱이름"

        //기입항목
        et_email = findViewById(et_signEmail);
        et_pw = findViewById(R.id.et_signPW);


        Button login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //로그인요청
                String stremail = et_email.getText().toString();
                String strpw = et_pw.getText().toString();


                mFirebaseAuth.signInWithEmailAndPassword(stremail, strpw).addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //로그인 성공!
                            Intent intent = new Intent(loginActivity.this, MainActivity_loginend.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(loginActivity.this, "가입하지 않은 계정입니다.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });



        Button login_sign_button = findViewById(R.id.login_sign_button);
        login_sign_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                //회원가입 화면으로 이동
                Intent intent = new Intent(loginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }
}