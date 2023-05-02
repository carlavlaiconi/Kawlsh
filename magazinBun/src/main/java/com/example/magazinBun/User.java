package com.example.magazinBun;

import conn.dbConnection;

import java.sql.*;
import java.util.ArrayList;

public class User implements changeable {

    public static int id_user;
    public int id;
    public String username;
    public String email;
    public int phone;
    public String parola;
    public int purchaseTotalCash;
    public PreparedStatement stmt;
    public ArrayList<Product> myCart = new ArrayList<Product>();
    String address;

    public User(String username, String email, String parola) {
        this.username = username;
        this.email = email;
        this.parola = parola;
    }

    public User(){

    }

    public void setPhone(int phone) throws SQLException {
        dbConnection dbConn = new dbConnection();
        Connection conn = dbConn.getConnection();
        try {
            stmt = conn.prepareStatement("SELECT tel FROM user WHERE id ="+(loggedInUser.getId()-1));
            ResultSet rs = stmt.executeQuery();
            phone= rs.getInt("tel");

        } catch (SQLException e) {
            System.err.println(e);

        }
        conn.close();
        stmt.close();

    }

    public User(int id){
        this.id=id;
    }

    public static int getId_user() { return id_user; }

    public String getName() { return username; }

    public String getEmail() { return email; }

    public String getParola() { return parola; }







        public boolean isUsernameAvailable(String username) {

        dbConnection dbConn = new dbConnection();
        Connection conn = dbConn.getConnection();
            try {
                stmt = conn.prepareStatement("SELECT * FROM user WHERE username = ?");
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                return !rs.next();
            } catch (SQLException e) {
                System.err.println(e);
                return false;
            }
        }


    @Override
    public void setData() {
        ;

    }

    @Override
    public void getData() {
        ;

    }

    @Override
    public void change(String name, int id) {

        String SQL = "UPDATE users SET parola='"+name+"' WHERE id="+ id +";";

        System.out.println("STMT: "+SQL);

        dbConnection dbC = new dbConnection();
        Connection conn = dbC.getConnection();

        try{
            Statement stmt= conn.createStatement();
            stmt.executeUpdate(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static User userPass(int id){

        User usr=null;
        String SQL="Select id from users WHERE id="+id;


        dbConnection dbC = new dbConnection();
        Connection conn = dbC.getConnection();

        try{
            Statement stmt= conn.createStatement();
            ResultSet res = stmt.executeQuery(SQL);
            while(res.next())
                usr= new User(res.getInt("id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usr;

    }
}




