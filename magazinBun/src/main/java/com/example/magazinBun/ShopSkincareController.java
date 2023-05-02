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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ShopSkincareController implements Initializable {

    @FXML
    private Button mainButtonSSkincare;

    @FXML
    private GridPane productContainerSk;

    @FXML
    private TextField searchBarSSkincare;

    private List<Product> skincare;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            skincare = new ArrayList<>(skincare());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int column = 0;
        int row = 1;
        try {
            for (Product product : skincare) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ProductVBox.fxml"));
                VBox productBOX = fxmlLoader.load();
                ProductVBoxController productController = fxmlLoader.getController();
                productController.setData(product);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                productContainerSk.add(productBOX,column++,row);
                GridPane.setMargin(productBOX, new Insets(10));

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    private List<Product> skincare() throws SQLException {

        List<Product> ls =new ArrayList<>();
        dbConnection connection = new dbConnection();
        Connection conn = connection.getConnection();
        String sql = "SELECT nume, pret, brand, img, id_produs from produse WHERE id_categorie=4";
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            Product product = new Product(0,null,null,null);
            byte[] imageData = new byte[0];
            imageData =res.getBytes("img");
            Image image = new Image(new ByteArrayInputStream(imageData));
            product.setName(res.getString("nume"));
            product.setBrand(res.getString("brand"));
            product.setPrice(res.getString("pret"));
            product.setId_product(res.getInt("id_produs"));
            product.setImage(image);
            ls.add(product);
        }
        conn.close();
        stmt.close();
        return ls;
    }



    public void switchToShopMenu(ActionEvent event) throws IOException {
        root= FXMLLoader.load((getClass().getResource("ShopMenu.fxml")));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
