package com.example.magazinBun;


import conn.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.awt.Label;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class LoginController {

    @FXML
    private TextField emailFieldSI;

    @FXML
    private Button logInButtonLI;

    @FXML
    private PasswordField passwordFieldSI;
    @FXML
    private Button mainButtonSI;

    @FXML
    private Button registerButtonLI;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private static String currentUserId;
    private User user;


    @FXML
    void switchToAccount(ActionEvent event) throws IOException, SQLException {
        loginButton();
        Parent root;
        if(loggedInUser.isAdmin==0)
         root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SlideShow.fxml")));
        else
             root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void loginButton() throws SQLException {
        dbConnection connection = new dbConnection();
        Connection conn = connection.getConnection();
        String sql = "SELECT Email, Parola, id, admin from users";
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        boolean loggedIn = false;
        while (res.next() && loggedIn==false) {
            if (emailFieldSI.getText().equals(res.getString("Email")) && passwordFieldSI.getText().equals(res.getString("Parola"))) {
                loggedIn = true;


                if(res.getInt("admin")==1)
                    loggedInUser.isAdmin=1;
           }

        }
        loggedInUser.setId(res.getInt("id"));
        conn.close();
        }

    @FXML
    void switchToRegister(ActionEvent event) throws IOException, SQLException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Register.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
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

    }

