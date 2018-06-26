package com.fitty.njora.nicollet.fitty_94056;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class AccountFragment extends Fragment {


    public AccountFragment() {
        // Required empty public constructor
    }




    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

//        getNameInformation();

        TextView profile_name_view;
        ImageView profile_image_icon;

        profile_name_view = (TextView)view.findViewById(R.id.profile_name_view);
        profile_image_icon = (ImageView)view.findViewById(R.id.profile_image_icon);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String photoUrl = user.getPhotoUrl().toString();

            profile_name_view.setText(name);
            Glide.with(this)
                    .load(photoUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .into(profile_image_icon);

        }
        return view;

    }

}
