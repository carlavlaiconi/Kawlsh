package com.example.magazinBun;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class AdminController {

    @FXML
    private Button mainButtonA;
    @FXML
    private Button addProducts;

    @FXML
    private Button manageProducts;

    @FXML
    private Button ordersList;

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void addProductsClick(ActionEvent event) throws IOException {

        root= FXMLLoader.load((getClass().getResource("addProduct.fxml")));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void manageProductsClick(ActionEvent event) throws IOException {

        root= FXMLLoader.load((getClass().getResource("manageProducts.fxml")));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void ordersListClick(ActionEvent event) throws IOException {
        root= FXMLLoader.load((getClass().getResource("manageOrders.fxml")));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToShopMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) mainButtonA.getScene().getWindow();
        stage.close();
    }

}
