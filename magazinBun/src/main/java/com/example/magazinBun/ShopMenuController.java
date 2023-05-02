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

public class ShopMenuController {

    @FXML
    private Button foundationButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button lipstickButton;

    @FXML
    private Button paletesButton;

    @FXML
    private Button parfumesButton;
    @FXML
    private Button exitButton;

    @FXML
    private Button skincareButton;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void SwitchToShopPalettes(ActionEvent event) throws IOException {
        root= FXMLLoader.load((getClass().getResource("ShopPalettes.fxml")));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToShopLipsticks(ActionEvent event) throws IOException {
        root= FXMLLoader.load((getClass().getResource("ShopLipsticks.fxml")));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToShopFoundations(ActionEvent event) throws IOException {
        root= FXMLLoader.load((getClass().getResource("ShopFoundations.fxml")));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToShopSkincare(ActionEvent event) throws IOException {
        root= FXMLLoader.load((getClass().getResource("ShopSkincare.fxml")));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToShopParfumes(ActionEvent event) throws IOException {
        root= FXMLLoader.load((getClass().getResource("ShopParfumes.fxml")));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToSlideShow(ActionEvent event) throws IOException {
        root= FXMLLoader.load((getClass().getResource("SlideShow.fxml")));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchToFavourites(ActionEvent event) throws IOException {
        root= FXMLLoader.load((getClass().getResource("Favourites.fxml")));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToCart(ActionEvent event) throws IOException {
        root= FXMLLoader.load((getClass().getResource("Cart.fxml")));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void exitFuctionSM() throws IOException {
        Stage stageToClose = (Stage) exitButton.getScene().getWindow();
        stageToClose.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SlideShow.fxml"));
        root=loader.load();
        SlideShowController SSc = loader.getController();;
        SSc.stopSlider();
    }

}
