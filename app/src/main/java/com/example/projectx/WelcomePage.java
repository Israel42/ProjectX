package com.example.projectx;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomePage extends AppCompatActivity {
    Button getstarted;
    VideoView videoView;
    MediaPlayer mediaPlayer;
    int CurrentPosition;
    PrefManager manager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);
        getstarted=findViewById(R.id.started);
        videoView=findViewById(R.id.videoviewintro);
        manager=new PrefManager(getApplicationContext());
        if (!manager.FirstLaunch()){
            starthomeactivity();
        }
        Uri uri=Uri.parse("android.resource://"
                + getPackageName()
                + "/"
                + R.raw.demohotelfinding);
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

        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(WelcomePage.this,Phonenumber.class));
               finish();
            }
        });

    }
    private void starthomeactivity() {
        manager.setFirstTimeLaunch(false);
        Intent intent=new Intent(getApplicationContext(),Phonenumber.class);
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


}
