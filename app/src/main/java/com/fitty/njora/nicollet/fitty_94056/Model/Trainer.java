package com.fitty.njora.nicollet.fitty_94056.Model;

public class Trainer {

    private String trainer_name;
    private String gym;
    private String email;
    private String photo;
    private int contacts;
    private int rating;

    public Trainer(String trainer_name, String gym, String email, String photo, int contacts, int rating){

        this.trainer_name = trainer_name;
        this.gym = gym;
        this.email = email;
        this.photo = photo;
        this.contacts = contacts;
        this.rating = rating;
    }

    //    Name
    public String getName() {
        return trainer_name;
    }

    public void setName(String trainer_name) {
        this.trainer_name = trainer_name;
    }
    //    Gym
    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
    }
    //    Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //    Photo url
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    //    Contacts
    public Integer getContacts() {
        return contacts;
    }

    public void setContacts(Integer contacts) {
        this.contacts = contacts;
    }
    //    Rating
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

}

