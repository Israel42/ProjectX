package com.example.projectx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Phonenumber extends AppCompatActivity implements View.OnClickListener {
    EditText phone;
    Button login;
    String phonenumber;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoneadd);
        phone=findViewById(R.id.phone_number);
        login=findViewById(R.id.log_in);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
     phonenumber=phone.getText().toString();
     if (phonenumber.isEmpty()||phonenumber.length()<9){
         phone.setError("Phone number is empty or less than 9 digits!");
         phone.setFocusable(true);
         return;
     }
        Intent intent=new Intent(Phonenumber.this,Verify.class);
        intent.putExtra("phonenumber",phonenumber);
        startActivity(intent);
        finish();
    }
}
