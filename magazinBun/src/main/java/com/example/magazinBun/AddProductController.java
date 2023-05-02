package com.example.magazinBun;

import conn.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AddProductController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private File selectedImage;
    @FXML
    private TextField name, brand, price;

    @FXML
    private Button choosePhoto;

    @FXML
    private ComboBox<Integer> category;

    public void initialize(){
        for(int i=1; i<=5; i++)
            category.getItems().add(i);

    }
    public void saveClick(ActionEvent event) {

        if(name.getText().isEmpty()){
            name.setPromptText("Please input the product name!");
        }
        else if(brand.getText().isEmpty()){
            brand.setPromptText("Please input the brand!");
        }
        else if(price.getText().isEmpty())
        {
            price.setPromptText("Please input the price!");
        }
        else{
            try {

                dbConnection dbC = new dbConnection();
                Connection connection = dbC.getConnection();


                String updateSQL = "INSERT INTO produse(id_categorie, nume, pret, brand, img) VALUES(?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
                preparedStatement.setInt(1, category.getValue());
                preparedStatement.setString(2, name.getText());
                preparedStatement.setString(3, price.getText());
                preparedStatement.setString(4, brand.getText());
                if(selectedImage != null){
                    FileInputStream fis = new FileInputStream(selectedImage);
                    preparedStatement.setBinaryStream(5, fis, (int)selectedImage.length());
                }
                else{
                    preparedStatement.setNull(5, java.sql.Types.BLOB);
                }
                preparedStatement.executeUpdate();

                connection.close();

            } catch (SQLException | java.io.FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public void choosePhotoClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif")
        );
        selectedImage = fileChooser.showOpenDialog(new Stage());
    }


    public void switchToShopMenu(ActionEvent event) throws IOException {
        root= FXMLLoader.load((getClass().getResource("admin.fxml")));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
