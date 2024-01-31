package com.example.splitease_wip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView home;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        home=findViewById(R.id.btm_nav);
        fragment=new HomeFragment();
        replaceFragment();
        home.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.btm_home) {
                    fragment=new HomeFragment();
                    replaceFragment();
                    return true;
                }
                if (item.getItemId() == R.id.btm_groups) {
                    fragment=new GroupsFragment();
                    replaceFragment();
                    return true;
                }
                if (item.getItemId() == R.id.btm_me) {
                    fragment=new MeFragment();
                    replaceFragment();
                    return true;
                }
                return false;
            }
        });
    }

    private void replaceFragment() {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.screen_holder,fragment,""); fragmentTransaction.commit();

    }
}