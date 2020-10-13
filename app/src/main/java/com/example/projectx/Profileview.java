package com.example.projectx;

public class Profileview {
    String name;
    String phone;
    String imageUrl;
    String userRating;
    String userReview;

    public Profileview(String name, String phone, String imageUrl, String userRating, String userReview) {
        this.name = name;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.userRating = userRating;
        this.userReview = userReview;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getUserReview() {
        return userReview;
    }

    public void setUserReview(String userReview) {
        this.userReview = userReview;
    }
}
