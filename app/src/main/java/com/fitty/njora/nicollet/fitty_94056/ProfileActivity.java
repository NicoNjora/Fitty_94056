 package com.fitty.njora.nicollet.fitty_94056;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

 public class ProfileActivity extends AppCompatActivity {

     private TextView textView_email, textView_name;
     private ImageView  imageView_profile_image ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        textView_email = (TextView) findViewById(R.id.textView_email);
        textView_name = (TextView) findViewById(R.id.textView_name);
        imageView_profile_image = (ImageView) findViewById(R.id.imageView_profile_image);




        getProfileInformation();
    }

     private void getProfileInformation() {

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                // Name, email address, and profile photo Url
                String name = user.getDisplayName();
                String email = user.getEmail();
                String photoUrl = user.getPhotoUrl().toString();

                textView_name.setText(name);
                textView_email.setText(email);
                Glide.with(this)
                        .load(photoUrl)
                        .apply(RequestOptions.circleCropTransform())
                        .into(imageView_profile_image);
            }
            else{

            }
        }


}
