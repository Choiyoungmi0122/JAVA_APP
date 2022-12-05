package com.example.myapplication;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;     //파이어베이스 인증처리
    private DatabaseReference mDatabaseRef;   //실시간 데이터베이스
    private EditText  et_email;
    private EditText et_pw, et_pw2;      //회원가입 입력필드
    private Button pwcheck, submit, codecom, cert;      //비밀번호 같은지확인, 회원가입버튼, 인증코드버튼, 이메일 인증보내는 버튼

    // 비밀번호 정규식
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$");

    // 파이어베이스 인증 객체 생성
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //파이어베이스 인증객체 선언
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
                String stremail = et_email.getText().toString().trim();

                String strpw = et_pw.getText().toString().trim();

                stremail= stremail + "@office.deu.ac.kr";


                ActionCodeSettings actionCodeSettings =
                        ActionCodeSettings.newBuilder()
                                // URL you want to redirect back to. The domain (www.example.com) for this
                                // URL must be whitelisted in the Firebase Console.
                                .setUrl("firebase-adminsdk-be4so@four-team.iam.gserviceaccount.com")
                                // This must be true
                                .setHandleCodeInApp(true)
                                .setIOSBundleId("com.example.ios")
                                .setAndroidPackageName(
                                        "com.example.android",
                                        true, /* installIfNotAvailable */
                                        "12"    /* minimumVersion */)
                                .build();


                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.sendSignInLinkToEmail(stremail, actionCodeSettings)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "Email sent.");
                                }
                            }
                        });
                Intent intent = getIntent();

// Confirm the link is a sign-in with email link.
                if (mFirebaseAuth.isSignInWithEmailLink(stremail)) {
                    // Retrieve this from wherever you stored it
                    String email = "noreply@four-team.firebaseapp.com";

                    // The client SDK will parse the code from the link for you.
                    mFirebaseAuth.signInWithEmailLink(email, stremail)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "Successfully signed in with email link!");
                                        AuthResult result = task.getResult();
                                        // You can access the new user via result.getUser()
                                        // Additional user info profile *not* available via:
                                        // result.getAdditionalUserInfo().getProfile() == null
                                        // You can check if the user is new or existing:
                                        // result.getAdditionalUserInfo().isNewUser()
                                    } else {
                                        Log.e(TAG, "Error signing in with email link", task.getException());
                                    }
                                }
                            });
                }
                //Firebase Auth 진행
//                mFirebaseAuth.createUserWithEmailAndPassword(stremail, strpw).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {       //회원가입이 성공했을 때
//                        if (task.isSuccessful()) {
//                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
//                            UserAccount account = new UserAccount();
//                            account.setIdToken(firebaseUser.getUid());
//                            account.setEmailI(firebaseUser.getEmail());
//                            account.setPassword(strpw);
//
//                            //setValue : database 에 insert 행위
//                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);
//
//                            Toast.makeText(SignUpActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
//
//                        } else {
//                            Toast.makeText(SignUpActivity.this, "회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });
            }
        });
    }
}