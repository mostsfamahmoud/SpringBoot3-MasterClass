package com.luv2code.springboot.thymeleafdemo.model;

import java.util.List;

public class Student {
    private String firstName;
    private String lastName;
    private String country;
    private String favoriteLanguage;
    private List<String> favoriteOperatingSystems;

    public Student() {
    }

    public Student(String firstName, String lastName, String country, String favoriteLanguage, List<String> favoriteOperatingSystem) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.favoriteLanguage = favoriteLanguage;
        this.favoriteOperatingSystems = favoriteOperatingSystem;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFavoriteLanguage() {
        return favoriteLanguage;
    }

    public void setFavoriteLanguage(String favoriteLanguage) {
        this.favoriteLanguage = favoriteLanguage;
    }

    public List<String> getFavoriteOperatingSystems() {
        return favoriteOperatingSystems;
    }

    public void setFavoriteOperatingSystems(List<String> favoriteOperatingSystems) {
        this.favoriteOperatingSystems = favoriteOperatingSystems;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", favoriteLanguage='" + favoriteLanguage + '\'' +
                ", favoriteOperatingSystems=" + favoriteOperatingSystems +
                '}';
    }
}
