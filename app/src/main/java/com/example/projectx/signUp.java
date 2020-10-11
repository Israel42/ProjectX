package com.example.projectx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    EditText fname,lname,age,phonenumber;
    RadioGroup gender;
    RadioButton choose;
    ProgressBar pr;
    Button signin;
    TextView createaccount;
    private FirebaseAuth auth;
    String mfname,mlname,mage,mphonenumber, mgender;
    FirebaseFirestore firestore;
    DocumentReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        pr=findViewById(R.id.progress);
        fname=findViewById(R.id.firstName);
        lname=findViewById(R.id.lastName);
        age=findViewById(R.id.age);
        firestore=FirebaseFirestore.getInstance();
        phonenumber=findViewById(R.id.phoneNumber);
        phonenumber.setInputType(InputType.TYPE_NULL);
        phonenumber.setText(getIntent().getStringExtra("phone"));
        gender=findViewById(R.id.radiogroup);
        signin=findViewById(R.id.signin);
        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             mfname=fname.getText().toString();
             mlname=lname.getText().toString();
             mage=age.getText().toString();
             mphonenumber=getIntent().getStringExtra("phone");
             int selectedid= gender.getCheckedRadioButtonId();
             choose=findViewById(selectedid);
             mgender=choose.getText().toString();
             int agenum=Integer.parseInt(mage);
             int min=18;
             int max=90;
             if (agenum<min||agenum>max){
                 age.setError("You are underage or is empty!");
                 age.setFocusable(true);
                 return;
             }
             if(mfname.isEmpty()||mlname.isEmpty()){
                 fname.setError("First name must not be empty!");
                 lname.setError("Last name must not be empty!");
                 fname.setFocusable(true);
                 lname.setFocusable(true);
                 return;
             }
                pr.setVisibility(View.VISIBLE);
            String uid=auth.getCurrentUser().getUid();
             reference=firestore.collection("Users").document(uid);
             Map <String,Object> map=new HashMap<>();
             map.put("First Name",mfname);
             map.put("Last Name", mlname);
             map.put("Age",mage);
             map.put("Gender",mgender);
             map.put("Phone Number", mphonenumber);

             reference.set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                 @Override
                 public void onComplete(@NonNull Task<Void> task) {
                     if (task.isSuccessful()){
                         pr.setVisibility(View.INVISIBLE);
                         startActivity(new Intent(signUp.this,MainActivity.class));
                         finish();
                     }else {
                         Toast.makeText(signUp.this,task.getException().toString(), Toast.LENGTH_SHORT).show();
                     }
                 }
             });
            }
        });

    }
}