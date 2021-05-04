package com.joseortale.ortalesoft.moviesteca.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.joseortale.ortalesoft.moviesteca.R;
import com.joseortale.ortalesoft.moviesteca.view.fragments.MoviesFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MoviesFragment fragment = MoviesFragment.newInstance();
        setFragment(fragment);
    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.root_fragment, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 0) {
            super.onBackPressed();
        } else  {
            getFragmentManager().popBackStack();
        }
    }
}