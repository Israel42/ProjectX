package com.example.projectx;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity  {
    EditText FirstName,LastName,Age,PhoneNumber;
    private static int PICK_IMAGE=22;
    ImageView Profile;
    Button SignUp;
    ProgressBar progressBar;
    Uri ImageUri;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        FirstName=findViewById(R.id.firstName);
        LastName=findViewById(R.id.lastName);
        Age=findViewById(R.id.age);
        PhoneNumber=findViewById(R.id.phone_number);
        progressBar=findViewById(R.id.progress);
        Profile=findViewById(R.id.profile);
        SignUp=findViewById(R.id.signin);
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PICK_IMAGE&&data!=null&&data.getData()!=null){
            ImageUri=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),ImageUri);
                Profile.setImageBitmap(bitmap);
                Picasso.get().load(ImageUri).fit().into(Profile);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
}