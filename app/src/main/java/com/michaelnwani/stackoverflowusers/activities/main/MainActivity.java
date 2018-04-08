package com.michaelnwani.stackoverflowusers.activities.main;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.michaelnwani.stackoverflowusers.R;
import com.michaelnwani.stackoverflowusers.fragments.users.UsersFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String BACK_STACK_ROOT_TAG = "root_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(R.id.container, UsersFragment.newInstance())
                .addToBackStack(BACK_STACK_ROOT_TAG)
                .commit();
    }
}
