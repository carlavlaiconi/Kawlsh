package com.example.magazinBun;

import conn.dbConnection;
import javafx.scene.image.Image;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Product implements changeable{

    public int id;
    private String name;
    private Image image;
    private String price;
    private String brand;
    private int Id_product;



    public Product(int id, String name, String price, String brand) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;
    }

    public int getId_product() {
        return Id_product;
    }

    public int getId() {
        return id;
    }

    public void setId_product(int id_product) {
        Id_product = id_product;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public Image getImage() { return image; }

    @Override
    public void setData() {

    }

    @Override
    public void getData() {

    }

    @Override
    public void change(String newName, int id) {

       String SQL="UPDATE produse SET pret='"+newName+"' WHERE id_produs="+this.id+";";

        dbConnection dbc = new dbConnection();
        Connection conn = dbc.getConnection();

        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
