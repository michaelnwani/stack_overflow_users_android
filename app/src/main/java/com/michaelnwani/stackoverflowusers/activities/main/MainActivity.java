package com.michaelnwani.stackoverflowusers.activities.main;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.michaelnwani.stackoverflowusers.R;
import com.michaelnwani.stackoverflowusers.fragments.users.UsersFragment;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final String BACK_STACK_ROOT_TAG = "root_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsersFragment usersFragment = new UsersFragment();

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(R.id.container, usersFragment)
                .addToBackStack(BACK_STACK_ROOT_TAG)
                .commit();

    }
}
