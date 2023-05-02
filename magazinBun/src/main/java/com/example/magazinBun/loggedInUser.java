package com.example.magazinBun;

import java.util.ArrayList;
import java.util.List;

public class loggedInUser {
    public static int id=-1;
    public static int isAdmin=0;
    private static String email;

    public static List<Product> cart =new ArrayList<>();
    public static List<Product> favs =new ArrayList<>();



    public static int getId() {
        return id;
    }

    public static String getEmail() {
        return email;
    }

    public static void setId(int id) {
        loggedInUser.id = id;
    }

    public static void setEmail(String email) {
        loggedInUser.email = email;
    }
}
