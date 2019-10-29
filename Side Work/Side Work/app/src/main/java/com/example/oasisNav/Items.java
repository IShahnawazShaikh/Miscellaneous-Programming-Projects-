package com.example.oasisNav;

public class Items {
  private String first_name,last_name,email,phone,id;

    public Items(String first_name, String last_name, String email, String phone, String id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }


    public String getLast_name() {
        return last_name;
    }


    public String getEmail() {
        return email;
    }


    public String getPhone() {
        return phone;
    }

    public String getId() {
        return id;
    }

}
