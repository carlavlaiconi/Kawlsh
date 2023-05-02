package com.example.magazinBun;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductVBoxController implements Initializable {

    @FXML
    public Button cartButtonVBox;

    @FXML
    public Button favsButtonVBox;

    @FXML
    private ImageView imageProductVBox;

    @FXML
    private Label labelBrandVBox;

    @FXML
    private Label labelNameVBox;
    @FXML
    private Pane productVBXpane;

    @FXML
    private Label labelPriceVBox;
    public void setData(Product product){
        imageProductVBox.setImage(product.getImage());
        labelBrandVBox.setText(product.getBrand());
        labelNameVBox.setText(product.getName());
        labelPriceVBox.setText(product.getPrice()+ " lei");
        cartButtonVBox.setUserData(product);
        favsButtonVBox.setUserData(product);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageProductVBox.setPreserveRatio(true);
        imageProductVBox.setSmooth(true);
        imageProductVBox.setCache(true);

    }


    public void AddToCart(ActionEvent event) {

        Button clickedButton = (Button) event.getSource();
        Product product = (Product) clickedButton.getUserData();
        loggedInUser.cart.add(product);

    }

    public void AddToFav(ActionEvent event) {

        Button clickedButton = (Button) event.getSource();
        Product product = (Product) clickedButton.getUserData();
        loggedInUser.favs.add(product);

    }
}
