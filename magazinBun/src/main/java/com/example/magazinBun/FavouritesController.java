package com.example.magazinBun;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FavouritesController implements Initializable {

    @FXML
    private Button mainButtonFavourites;

    @FXML
    private GridPane productContainerFav;

    public List<Product> favs= new ArrayList<>();

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToShopMenu(ActionEvent event) throws IOException {
        root= FXMLLoader.load((getClass().getResource("ShopMenu.fxml")));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int column = 0;
        int row = 1;
        for( Product product : loggedInUser.favs){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ProductVBox.fxml"));
            VBox productBOX = null;
            try {
                productBOX = fxmlLoader.load();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ProductVBoxController productController = fxmlLoader.getController();
            productController.favsButtonVBox.setDisable(true);
            productController.favsButtonVBox.setVisible(false);
            productController.setData(product);

            if (column == 3) {
                column = 0;
                row++;
            }
            productContainerFav.add(productBOX,column++,row);
            GridPane.setMargin(productBOX, new Insets(10));
        }
    }
}
