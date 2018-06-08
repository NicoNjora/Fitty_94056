package com.fitty.njora.nicollet.fitty_94056;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;
    private static final int RC_SIGN_IN = 123;

    //For demo purpose, I have provided two sample URLs. One for Privacy Policy and another for Terms of Service
    private static final String PP_URL = "https://iteritory.com/msadrud/install-or-setup-apache-ignite-in-windows-step-by-step-tutorial/";
    private static final String TOS_URL = "https://iteritory.com/msadrud/install-or-setup-apache-ignite-in-windows-step-by-step-tutorial/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instantiateUser();

    }

    // Choose authentication providers
    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build(),
            new AuthUI.IdpConfig.GoogleBuilder().build());



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }

    private void instantiateUser(){
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if ( mFirebaseUser != null) {
            // already signed in
        } else {
            // not signed in

            // Create and launch sign-in intent
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setTheme(R.style.AuthUITheme)
                            .setLogo(R.mipmap.ic_account_circle_black_48dp)
                            .setTosUrl(TOS_URL)
                            .setPrivacyPolicyUrl(PP_URL)
                            .setIsSmartLockEnabled(true)
                            .build(),
                    RC_SIGN_IN);




        }


    }

}
