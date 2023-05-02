
package com.example.magazinBun;
import conn.dbConnection;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

public class Order implements confirmeable{
    private  int id_user;
    public String address;

    public String status;


    public int id_command;

    private String productList;

    public boolean confirmed;

    public Button confirm;

    public Order(int id_user, String address, String productList) {
        this.address = address;
        this.id_user=id_user;
        this.productList=productList;
    }

    public Order(int id_user, String address, int id_command, String productList, boolean confirmed) {
        this.id_user = id_user;
        this.address = address;
        this.id_command = id_command;
        this.productList = productList;
        this.confirmed = confirmed;
    }

    public void setId_command(int id_command) {
        this.id_command = id_command;
    }

    public int getId_command() {
        return id_command;
    }

    public String getProductList() {
        return productList;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setAddress() throws SQLException {
        String aux = null;
        dbConnection connection = new dbConnection();
        Connection conn = connection.getConnection();
        String sql = "SELECT judet, oras, adresa from users Where id="+(loggedInUser.id-1);
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        while (res.next())
            aux=String.join(res.getString("judet")," ", res.getString("oras"), " ", res.getString("adresa"));

        conn.close();
        stmt.close();
        address=aux;
    }

    public void setProductList(String productList) {
        this.productList = productList;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public int getId_user() {
        return id_user;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public void setConfirm(boolean val) {
        this.confirmed=val;
    }

    @Override
    public boolean getConfirm() {
        return this.confirmed;
    }
}
