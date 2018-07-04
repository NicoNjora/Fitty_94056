package com.fitty.njora.nicollet.fitty_94056;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.fitty.njora.nicollet.fitty_94056.Fragments.AccountFragment;
import com.fitty.njora.nicollet.fitty_94056.Fragments.MapsFragment;
import com.fitty.njora.nicollet.fitty_94056.Fragments.SessionFragment;
import com.fitty.njora.nicollet.fitty_94056.Fragments.WorkoutFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class MainActivity extends AppCompatActivity{

    private ActionBar toolbar;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //toolbar.setTitle("Gym");
        loadFragment(new MapsFragment());

    }



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_gym:
                    toolbar.setTitle("Gym");
                    fragment = new MapsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_activity:
                    toolbar.setTitle("Activity");
                    fragment = new WorkoutFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_session:
                    toolbar.setTitle("Session");
                    fragment = new SessionFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_account:
                    toolbar.setTitle("Account");
                    fragment = new AccountFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }




    public void openProfile(View v)
    {
        //Start your activity here
        Intent profile = new Intent(this, ProfileActivity.class);
        startActivity(profile);
    }

    public void openSettings(View view)
    {
        //Start your activity here
        Intent settings = new Intent(this, SettingsActivity.class);
        startActivity(settings);
    }


    public void onLogout(View view) {


        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();

                    }
                });
    }



    public void addSession(View view) {

        //Start your activity here
        Intent i = new Intent(this, AddSessionActivity.class);
        startActivity(i);

    }


    public void openSupport(View view) {
        //Start your activity here
        Intent support = new Intent(this, SupportActivity.class);
        startActivity(support);

    }

    public void openLegal(View view) {
        //Start your activity here
        Intent legal = new Intent(this, LegalActivity.class);
        startActivity(legal);

    }

    public void addWorkout(View view) {
        //Start your activity here
        Intent i = new Intent(this, AddWorkoutActivity.class);
        startActivity(i);

    }
}

