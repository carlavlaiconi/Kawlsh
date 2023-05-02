package com.example.magazinBun;

import conn.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class AccountController {

    @FXML
    private TextField county;

    @FXML
    private TextField city;

    @FXML
    private TextField address;

    @FXML
    private TextField phone;

    @FXML
    private Button save;
    @FXML
    private Button mainButtonA;


    @FXML
    private Label welcomeLabelA;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button password;


    @FXML
    public void switchToShopMenu(ActionEvent event) throws IOException {
        root= FXMLLoader.load((getClass().getResource("ShopMenu.fxml")));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void displayName(String username){
        welcomeLabelA.setText("Welcome,"+ username+"! Let's set up your information!");
    }

    public void initialize(){
        if(loggedInUser.getId()!=-1){

            int id1=loggedInUser.getId()-1;
            String SQL = "Select judet, oras, adresa, tel FROM users where id="+id1;

            dbConnection dbCon = new dbConnection();
            Connection connection = dbCon.getConnection();

            try {
                Statement stmt = connection.createStatement();
                ResultSet rs= stmt.executeQuery(SQL);
                while(rs.next()){
                    if(rs.getString("judet")!=null)
                        county.setText(rs.getString("judet"));

                    if(rs.getString("oras")!=null)
                        city.setText(rs.getString("oras"));

                    if(rs.getString("adresa")!=null)
                        address.setText(rs.getString("adresa"));

                    if(rs.getString("tel")!=null)
                        phone.setText(rs.getString("tel"));


                }

                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void saveClick(){

        System.out.println("User id: "+ loggedInUser.id);

        if(county.getText().isEmpty()){
            county.setPromptText("Please input your county!");
        }
        else if(city.getText().isEmpty()){
            city.setPromptText("Please input your city!");
        }
        else if(address.getText().isEmpty())
        {
            address.setPromptText("Please input your addess!");
        }
        else if(phone.getText().isEmpty())
        {
            phone.setPromptText("Please input your phone!");
        }
        else{
            try {

                dbConnection dbC = new dbConnection();
                Connection connection = dbC.getConnection();


                String updateSQL = "UPDATE users SET judet = ?, oras = ?, adresa = ?, tel = ? WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
                preparedStatement.setString(1, county.getText());
                preparedStatement.setString(2, city.getText());
                preparedStatement.setString(3, address.getText());
                preparedStatement.setString(4, phone.getText());
                System.out.println("User id: "+ loggedInUser.id);
                preparedStatement.setInt(5, loggedInUser.getId()-1);
                preparedStatement.executeUpdate();

                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public void passwordClick(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("changePassword.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }
}
