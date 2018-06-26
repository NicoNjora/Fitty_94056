package com.fitty.njora.nicollet.fitty_94056;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {


    private static final String TAG = "z";
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;
    private static final int RC_SIGN_IN = 123;

    //For demo purpose, I have provided two sample URLs. One for Privacy Policy and another for Terms of Service
    private static final String PP_URL = "https://nicolletnjora.com/";
    private static final String TOS_URL = "https://nicolletnjora.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
                Intent main = new Intent(this, MainActivity.class);
                startActivity(main);
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...

                Log.e(TAG, "Authentication failed!");

            }
        }
    }

    private void instantiateUser(){
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if ( mFirebaseUser != null) {
            // already signed in
            Intent main = new Intent(this, MainActivity.class);
            startActivity(main);


        } else {
            // not signed in

            // Create and launch sign-in intent
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setTheme(R.style.AuthUITheme)
                            .setLogo(R.drawable.ic_weights_black)
                            .setTosUrl(TOS_URL)
                            .setPrivacyPolicyUrl(PP_URL)
                            .setIsSmartLockEnabled(false)
                            .build(),
                        RC_SIGN_IN);
        }

    }

}
