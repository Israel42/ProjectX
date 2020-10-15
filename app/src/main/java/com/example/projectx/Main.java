package com.example.projectx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.gms.maps.GoogleMap;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Main extends AppCompatActivity {
    MeowBottomNavigation navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation=findViewById(R.id.bottomnavigation);
        navigation.show(1,true);
        navigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_place_24));
        navigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_bookmark_24));
        navigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_account_circle_24));
        navigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                int item=model.getId();
                Fragment fragment=null;
                switch (item){
                    case 1:
                        fragment=new mapFragment();
                        break;
                    case 2:
                        fragment=new myBookings();
                        break;
                    case 3:
                        fragment=new profileFragment();
                        break;
                    default:
                }
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framenavigation,fragment);
                fragmentTransaction.commit();
                return null;
            }
        });
        }
    }