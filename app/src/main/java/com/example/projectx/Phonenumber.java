package com.example.projectx;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import java.util.Calendar;

public class Phonenumber extends AppCompatActivity implements View.OnClickListener {
    EditText phone;
    Button login;
    String phonenumber,countrycode;
    VideoView videoView;
    MediaPlayer mediaPlayer;
    int CurrentPosition;
    CountryCodePicker countryCodePicker;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoneadd);
        phone=findViewById(R.id.phone_number);
        login=findViewById(R.id.log_in);
        login.setOnClickListener(this);
        videoView=findViewById(R.id.videoview);
        countryCodePicker=findViewById(R.id.countrycode);
        Uri uri=Uri.parse("android.resource://"
        + getPackageName()
        + "/"
        + R.raw.projectx);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer=mp;
                mediaPlayer.setLooping(true);
                if (CurrentPosition!=0){
                    mediaPlayer.seekTo(CurrentPosition);
                    mediaPlayer.start();
                }
            }
        });
    }
    @Override
    public void onClick(View view) {
        countrycode=countryCodePicker.getSelectedCountryCode();
        phonenumber=phone.getText().toString();

        if (phonenumber.isEmpty()||phonenumber.length()<9){
            phone.setError("Phone number is empty or less than 9 digits!");
            phone.setFocusable(true);
            return;
        }
        String phonenum=countrycode+phonenumber;
        Intent intent=new Intent(Phonenumber.this,Verify.class);
        intent.putExtra("phonenumber",phonenum);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onPause() {
        super.onPause();
        CurrentPosition=mediaPlayer.getCurrentPosition();
        videoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        mediaPlayer=null;
    }


}
