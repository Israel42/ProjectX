package com.example.projectx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signUp extends AppCompatActivity  {
    EditText name, email, password;
    Button signin, signup;
    TextView createaccount;
    private FirebaseAuth auth;
    String mname,memail,mpassword;
    FirebaseFirestore firestore;
    DocumentReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name=findViewById(R.id.userName);
        email=findViewById(R.id.enterEmailAddress);
        password=findViewById(R.id.password);
        signin=findViewById(R.id.signin);
        signup=findViewById(R.id.signup);
        createaccount=findViewById(R.id.createnewaccount);

        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        if (user!=null){
            startActivity(new Intent(signUp.this,MainActivity.class));
            finish();
        }
        firestore=FirebaseFirestore.getInstance();
        signin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                memail=email.getText().toString().trim();
                mpassword=password.getText().toString().trim();
                auth.signInWithEmailAndPassword(memail,mpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(signUp.this,MainActivity.class));
                            finish();
                        }
                    }
                });
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mname=name.getText().toString().trim();
                memail=email.getText().toString().trim();
                mpassword=password.getText().toString().trim();
                auth.createUserWithEmailAndPassword(memail,mpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if ( task.isSuccessful()){
                            String key=auth.getCurrentUser().getUid();
                            reference=firestore.collection("users").document(key);
                            Map<String,Object>map= new HashMap<>();
                            map.put("User Name",mname);
                            map.put("Email",memail);
                            reference.set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(signUp.this,"Successful",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(signUp.this,MainActivity.class));
                                        finish();
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });
       createaccount.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               name.setVisibility(View.VISIBLE);
               signup.setVisibility(View.VISIBLE);
           }
       });
    }
}