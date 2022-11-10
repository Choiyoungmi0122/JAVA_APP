package com.example.four_team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    EditText name, email, code, pw, pw2, birthyear, birthdate, birthday;
    Button pwcheck, sumbit, codecom, cert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //기입항목
        name = findViewById(R.id.signName);
        email = findViewById(R.id.signEmail);
        code = findViewById(R.id.signcodetext_confirm);
        pw = findViewById(R.id.signPW);
        pw2 = findViewById(R.id.signPW2);
        birthyear = findViewById(R.id.signBirth);
        birthdate = findViewById(R.id.signBirth2);
        birthday = findViewById(R.id.signBirth3);

        //이메일 인증 버튼
//        cert = findViewById(R.id.signEmail_btn);
//        cert.setOnClickListener(view -> {
//
//        });


        //인증코드 확인 버튼
//        codecom = findViewById(R.id.signcodetext_confirm_btn);
//        codecom.setOnClickListener(view -> {
//
//        });


        //비밀번호 확인 버튼
        pwcheck = findViewById(R.id.pwcheckbutton);
        pwcheck.setOnClickListener(view -> {
            if(pw.getText().toString().equals(pw2.getText().toString())){
                pwcheck.setText("일치");
            }else{
                Toast.makeText(SignUpActivity.this, "비밀번호가 다릅니다.", Toast.LENGTH_LONG).show();
            }
        });

        //제출버튼
        sumbit = findViewById(R.id.signupbutton);
        sumbit.setOnClickListener(view -> {
            Intent intent = new Intent(this, loginActivity.class);
            startActivity(intent);
        });

    }
}