package com.example.projectx;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class SignUp extends AppCompatActivity  {
    EditText FirstName,LastName,Age,PhoneNumber;
    private static int PICK_IMAGE=22;
    ImageView Profile;
    Button SignUp;
    ProgressBar progressBar;
    Uri ImageUri;
    RadioButton radioButton;
    RadioGroup radioGroup;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
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
        radioGroup=findViewById(R.id.radiogroup);
        SignUp=findViewById(R.id.signin);
        auth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();

        String UID=auth.getCurrentUser().getUid();
        documentReference=firebaseFirestore.collection("Users").document(UID);
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this intent helps pick the images
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,PICK_IMAGE);//start the activity file to chose image
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ImageUri!=null){
                    adduserwithProfile();
                }else{
                    userwithoutProfile();
                }
            }
        });
    }
    // if the user uploads picture we call this method
     public void adduserwithProfile(){
        final String fnam,lname,age,phonenum,Gender;
        fnam=FirstName.getText().toString();
        lname=LastName.getText().toString();
        age=Age.getText().toString();
        int selectedid=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(selectedid);
        Gender=radioButton.getText().toString();
        phonenum=getIntent().getStringExtra("UserPhone");
        if (fnam.length()<3 && lname.isEmpty()){
            FirstName.setError("Please Enter Your Name");
            FirstName.setFocusable(true);
            LastName.setError("Please Enter YOur LastName");
            LastName.setFocusable(true);
            return;
        }
        int yourage=Integer.parseInt(age);
        int maxage=80;
        int minage=18;
        if (yourage<minage||yourage>maxage){
            Age.setError("Please Add your Age Clearly");
            Age.setFocusable(true);
            return;
        }
if (Gender.isEmpty()){
    Toast.makeText(this, "Please Select Your Gender", Toast.LENGTH_SHORT).show();
}
        storageReference=firebaseStorage.getReference().child("USER_IMAGE"+System.currentTimeMillis()+"."+getFileExtension(ImageUri));
        storageReference.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   Task<Uri> task=taskSnapshot.getMetadata().getReference().getDownloadUrl();// to get the download url from our firebasestorage
                   task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                       @Override
                       public void onSuccess(Uri uri) {
                             String imageurl;
                             imageurl=uri.toString();//to get the image with extenstion file form firebaseStorage string
                             Map<String,Object> map=new HashMap<>();
                             map.put("FirstName",fnam);
                             map.put("Lastname",lname);
                             map.put("Age",age);
                             map.put("Phonenumber",phonenum);
                             map.put("ImageUrl",imageurl);
                             map.put("Gender",Gender);
                             documentReference.set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                 @Override
                                 public void onComplete(@NonNull Task<Void> task) {
                                     if (task.isSuccessful()){
                                         startActivity(new Intent(getApplicationContext(),Main.class));
                                         finish();
                                     }    else {
                                         Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                                     }
                                 }
                             }).addOnFailureListener(new OnFailureListener() {
                                 @Override
                                 public void onFailure(@NonNull Exception e) {
                                     Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                 }
                             });
                       }
                   });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
              double count=(100.0*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
              //add progress bar here
            }
        });


     }
     // if the user didn't have profile picture this method will be called
     public void userwithoutProfile(){
         final String fnam,lname,age,phonenum,Gender;
         fnam=FirstName.getText().toString();
         lname=LastName.getText().toString();
         age=Age.getText().toString();
         int selectedid=radioGroup.getCheckedRadioButtonId();
         radioButton=findViewById(selectedid);
         Gender=radioButton.getText().toString();
         phonenum=getIntent().getStringExtra("UserPhone");
         if (fnam.length()<3 && lname.isEmpty()){
             FirstName.setError("Please Enter Your Name");
             FirstName.setFocusable(true);
             LastName.setError("Please Enter YOur LastName");
             LastName.setFocusable(true);
             return;
         }
         int yourage=Integer.parseInt(age);
         int maxage=80;
         int minage=18;
         if (yourage<minage||yourage>maxage){
             Age.setError("Please Add your Age Clearly");
             Age.setFocusable(true);
             return;
         }
         Map<String,Object> map=new HashMap<>();
         map.put("FirstName",fnam);
         map.put("Lastname",lname);
         map.put("Age",age);
         map.put("Phonenumber",phonenum);
         map.put("Gender",Gender);
         documentReference.set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
             @Override
             public void onComplete(@NonNull Task<Void> task) {
               if (task.isSuccessful()) {
                   startActivity(new Intent(getApplicationContext(), Main.class));
                   finish();
                 }else {
                   Toast.makeText(SignUp.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                   }
               }
         });
     }
     // this method helps us to add file extensions to our uri file
     public String getFileExtension(Uri uri){
         ContentResolver cr=getContentResolver();
         MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
         return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
     }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PICK_IMAGE&&data!=null&&data.getData()!=null){
            ImageUri=data.getData();// to get the image data to the uri
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),ImageUri); // we have to convert it to bitmap to fit our need
                Profile.setImageBitmap(bitmap);//set the bitmap to our image view
                Picasso.get().load(ImageUri).transform(new CropCircleTransformation()).fit().into(Profile);// we sed picasso to configure the structure of the picture
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
}