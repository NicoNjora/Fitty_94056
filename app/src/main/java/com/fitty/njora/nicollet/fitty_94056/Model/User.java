package com.fitty.njora.nicollet.fitty_94056.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class User {


    public String email;
    private String uid;
    private Integer weight;



    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String uid, String email, Integer weight) {
        this.uid = uid;
        this.email = email;
        this.weight = weight;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("email", email);
        result.put("weight", weight);

        return result;
    }
}
