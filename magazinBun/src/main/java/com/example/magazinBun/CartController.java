package com.example.magazinBun;

import conn.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CartController implements Initializable {

    @FXML
    private Button mainButtonCart;

    @FXML
    private GridPane productContainercart;

    @FXML
    private Button order;



    @FXML
    private void orderButtonClick(){
        String aux, prdls = "";
        for(Product product : loggedInUser.cart){
            System.out.println("id "+product.getId_product());
            aux = String.valueOf(product.getId_product());
            System.out.println("aux "+aux);
            prdls = String.join(",", prdls, aux);

        }

        int usrId = loggedInUser.getId()-1;
        Order order = new Order(usrId, "", "");
        try {

            dbConnection dbC = new dbConnection();
            Connection connection = dbC.getConnection();

            order.setAddress();


            String updateSQL = "INSERT INTO comenzi(id_client, adresa, prodIds) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setInt(1, order.getId_user());
            preparedStatement.setString(2, order.getAddress());
            preparedStatement.setString(3, prdls);
            System.out.println(updateSQL);
            preparedStatement.executeUpdate();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }


    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToShopMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load((getClass().getResource("ShopMenu.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int column = 0;
        int row = 1;
        for( Product product : loggedInUser.cart){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ProductVBox.fxml"));
            VBox productBOX = null;
            try {
                productBOX = fxmlLoader.load();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ProductVBoxController productController = fxmlLoader.getController();
            productController.cartButtonVBox.setDisable(true);
            productController.cartButtonVBox.setVisible(false);
            productController.favsButtonVBox.setDisable(true);
            productController.favsButtonVBox.setVisible(false);
            productController.setData(product);

            if (column == 3) {
                column = 0;
                row++;
            }
            productContainercart.add(productBOX,column++,row);
            GridPane.setMargin(productBOX, new Insets(10));
        }
    }
}