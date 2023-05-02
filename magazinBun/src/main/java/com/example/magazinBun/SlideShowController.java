package com.example.magazinBun;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SlideShowController implements Initializable {


    @FXML
    private Button accountButtonSS;

    @FXML
    private AnchorPane anchorSlideShow;

    @FXML
    private Button cartButtonSS;

    @FXML
    private ImageView facebookIconSS;

    @FXML
    private Button favouritesButtonSS;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private ImageView image4;

    @FXML
    private ImageView instagramIconSS;

    @FXML
    private ImageView linkedinIconSS;

    @FXML
    private Label logoSliseShow;

    @FXML
    private Button mainButtonSS;

    @FXML
    private Button mainButtonSSmenu;

    @FXML
    private Pane semiOpacPaneSS;

    @FXML
    private Button shopButtonSS;

    @FXML
    private ImageView twitterIconSS;

    @FXML
    private Button exitButtonSS;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private boolean stopped = false;
    private Window window;



    void hidePane(){
        semiOpacPaneSS.setVisible(false);
    }


    void showPane(){
        semiOpacPaneSS.setVisible(true);
    }

    void showIcons(){
        facebookIconSS.setVisible(true);
        instagramIconSS.setVisible(true);
        twitterIconSS.setVisible(true);
        linkedinIconSS.setVisible(true);
    }

    void hideIcons(){
        facebookIconSS.setVisible(false);
        instagramIconSS.setVisible(false);
        twitterIconSS.setVisible(false);
        linkedinIconSS.setVisible(false);
    }
    void showButtonsSS(){
        accountButtonSS.setVisible(true);
        shopButtonSS.setVisible(true);
        cartButtonSS.setVisible(true);
        favouritesButtonSS.setVisible(true);
        exitButtonSS.setVisible(true);
    }

    void hideButtonsSS(){
        accountButtonSS.setVisible(false);
        shopButtonSS.setVisible(false);
        cartButtonSS.setVisible(false);
        favouritesButtonSS.setVisible(false);
        exitButtonSS.setVisible(false);
    }

    public void stopSlider() {
        stopped = true;
    }

    public void slider(){
        new Thread() {

            public void run() {
                int count = 0;
                try {
                    while (!stopped) {
                        switch (count) {
                            case 0:
                                Thread.sleep(3000);
                                TranslateTransition slider1 = new TranslateTransition();
                                slider1.setNode(image1);
                                slider1.setDuration(Duration.seconds(3));
                                slider1.setToX(0);
                                slider1.play();

                                TranslateTransition slider2 = new TranslateTransition();
                                slider2.setNode(image2);
                                slider2.setDuration(Duration.seconds(3));
                                slider2.setToX(0);
                                slider2.play();

                                TranslateTransition slider3 = new TranslateTransition();
                                slider3.setNode(image3);
                                slider3.setDuration(Duration.seconds(3));
                                slider3.setToX(-600);
                                slider3.play();

                                TranslateTransition slider4 = new TranslateTransition();
                                slider4.setNode(image4);
                                slider4.setDuration(Duration.seconds(3));
                                slider4.setToX(-1200);
                                slider4.play();

                                count=1;
                                break;


                            case 1:
                                Thread.sleep(5000);

                                TranslateTransition slider5 = new TranslateTransition();
                                slider5.setNode(image1);
                                slider5.setDuration(Duration.seconds(3));
                                slider5.setToX(600);
                                slider5.play();

                                TranslateTransition slider6= new TranslateTransition();
                                slider6.setNode(image2);
                                slider6.setDuration(Duration.seconds(3));
                                slider6.setToX(600);
                                slider6.play();

                                TranslateTransition slider7 = new TranslateTransition();
                                slider7.setNode(image3);
                                slider7.setDuration(Duration.seconds(3));
                                slider7.setToX(0);
                                slider7.play();

                                TranslateTransition slider8 = new TranslateTransition();
                                slider8.setNode(image4);
                                slider8.setDuration(Duration.seconds(3));
                                slider8.setToX(-600);
                                slider8.play();

                                count=2;
                                break;

                            case 2:
                                Thread.sleep(5000);

                                TranslateTransition slider9= new TranslateTransition();
                                slider9.setNode(image1);
                                slider9.setDuration(Duration.seconds(3));
                                slider9.setToX(1200);
                                slider9.play();

                                TranslateTransition slider10= new TranslateTransition();
                                slider10.setNode(image2);
                                slider10.setDuration(Duration.seconds(3));
                                slider10.setToX(1200);
                                slider10.play();

                                TranslateTransition slider11= new TranslateTransition();
                                slider11.setNode(image3);
                                slider11.setDuration(Duration.seconds(3));
                                slider11.setToX(600);
                                slider11.play();

                                TranslateTransition slider12= new TranslateTransition();
                                slider12.setNode(image4);
                                slider12.setDuration(Duration.seconds(3));
                                slider12.setToX(0);
                                slider12.play();

                                count=3;
                                break;

                            case 3:
                                Thread.sleep(5000);

                                TranslateTransition slider13= new TranslateTransition();
                                slider13.setNode(image1);
                                slider13.setDuration(Duration.seconds(3));
                                slider13.setToX(1200);
                                slider13.play();

                                TranslateTransition slider14= new TranslateTransition();
                                slider14.setNode(image2);
                                slider14.setDuration(Duration.seconds(3));
                                slider14.setToX(1200);
                                slider14.play();

                                TranslateTransition slider15= new TranslateTransition();
                                slider15.setNode(image3);
                                slider15.setDuration(Duration.seconds(3));
                                slider15.setToX(1200);
                                slider15.play();

                                TranslateTransition slider16= new TranslateTransition();
                                slider16.setNode(image4);
                                slider16.setDuration(Duration.seconds(3));
                                slider16.setToX(600);
                                slider16.play();

                                count=0;
                                break;


                        }
                    }
                } catch (Exception e) { e.printStackTrace(); }
            }
        }.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        semiOpacPaneSS.setVisible(false);
        mainButtonSSmenu.setVisible(false);
        hideButtonsSS();
        hideIcons();
        mainButtonSS.setOnAction(actionEvent ->
        {
            showPane();
            mainButtonSSmenu.setVisible(true);
            showButtonsSS();
            showIcons();
        });
        mainButtonSSmenu.setOnAction(actionEvent ->
        {
            hidePane();
            mainButtonSSmenu.setVisible(false);
            hideIcons();
            hideButtonsSS();
        });
        slider();
    }

    public void exitFuctionSS() {
        Stage stageToClose = (Stage) exitButtonSS.getScene().getWindow();
        stageToClose.close();
        stopSlider();
    }

    public void switchToSignIn(ActionEvent event) throws IOException {

        if(loggedInUser.getId()==-1)
        root= FXMLLoader.load((getClass().getResource("SignInBun.fxml")));
        else
            root= FXMLLoader.load((getClass().getResource("Account.fxml")));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToShopMenu(ActionEvent event) throws IOException {
        root= FXMLLoader.load((getClass().getResource("ShopMenu.fxml")));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCart(ActionEvent event) throws IOException {
        root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Cart.fxml")));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToFavourites(ActionEvent event) throws IOException {
        root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Favourites.fxml")));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
