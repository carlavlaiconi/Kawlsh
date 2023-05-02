package com.example.magazinBun;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PassChangeController {

    public User u;

    @FXML
    private PasswordField pass;

    @FXML
    private PasswordField confirmPass;
    public void saveClick(ActionEvent event) {

        User usr=null;

        if(pass.getText().isEmpty()){
            pass.setPromptText("Type in a password!");
        }
        else {
            if(confirmPass.getText().isEmpty()){
                confirmPass.setPromptText("Confirm the password!");
            }
            else if(confirmPass.getText().equals(pass.getText()))
            {
                usr=User.userPass(loggedInUser.id);
                usr.change(pass.getText(), loggedInUser.id-1);
            }
            else
                pass.setPromptText("Passwords do not match!");
        }

    }

    public void switchToShopMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Account.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
