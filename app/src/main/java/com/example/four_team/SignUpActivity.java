package com.example.four_team;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;     //파이어베이스 인증처리
    private DatabaseReference mDatabaseRef;   //실시간 데이터베이스
    private EditText  et_email, et_pw, et_pw2;      //회원가입 입력필드
    private Button pwcheck, submit, codecom, cert;      //비밀번호 같은지확인, 회원가입버튼, 인증코드버튼, 이메일 인증보내는 버튼
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("fourteam");       //"앱이름"

        //기입항목
        et_email = findViewById(R.id.et_signEmail);
        et_pw = findViewById(R.id.et_signPW);
        et_pw2 = findViewById(R.id.et_signPW2);

        submit = findViewById(R.id.signupbutton);

        //비밀번호 확인 버튼
        pwcheck = findViewById(R.id.pwcheckbutton);
        pwcheck.setOnClickListener(view -> {
            if(et_pw.getText().toString().equals(et_pw2.getText().toString())){
                pwcheck.setText("일치");
            }else{
                Toast.makeText(SignUpActivity.this, "비밀번호가 다릅니다.", Toast.LENGTH_LONG).show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원가입버튼누르면  회원가입 처리 시작
//                String strname = submit.getText().toString();
                String stremail = et_email.getText().toString().trim();
                String strpw = et_pw.getText().toString().trim();

                //Firebase Auth 진행
                mFirebaseAuth.createUserWithEmailAndPassword(stremail, strpw).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {       //회원가입이 성공했을 때
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                            UserAccount account = new UserAccount();
                            account.setIdToken(firebaseUser.getUid());
                            account.setEmailI(firebaseUser.getEmail());
                            account.setPassword(strpw);

                            //setValue : database 에 insert 행위
                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                            Toast.makeText(SignUpActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(SignUpActivity.this, "회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }
}

