package com.example.myfrag;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private static final String key="101";
    private static final String rt="999";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void Clickbtn(View view) {
        Fragment frag;
        if (view == findViewById(R.id.frag1)) {
            frag = new BlankFragmentOne();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_space, frag);
            ft.commit();

        }
        if (view == findViewById(R.id.frag2)) {
            frag = new BlankFragmentTwo();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_space, frag);
            ft.commit();


        }


    }
     public void Clickbtn1(View view){
         Intent intent = new Intent(MainActivity.this, Profile_Page.class);
         startActivity(intent);
         intent.putExtra(key, rt);
     }

}
