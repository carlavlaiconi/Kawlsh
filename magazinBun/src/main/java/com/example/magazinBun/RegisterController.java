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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RegisterController implements Initializable{

    @FXML
    private TextField emailFieldR;
    @FXML
    private PasswordField passwordFieldR;
    @FXML
    private Button registerButtonR;
    @FXML
    private TextField usernameFieldR;
    @FXML
    private Label validate_label;
    @FXML
    private Button mainButtonReg;

    private Stage stage;
    private Scene scene;
    private Parent root;

    void setLabel(){
        validate_label.setVisible(false);
    }

    @FXML
    int RegisterMeOnAction(ActionEvent event) throws IOException, SQLException {
        if(register_new()==1)
            return 1;
        String username= usernameFieldR.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Account.fxml"));
        root=loader.load();
        AccountController acCtrl = loader.getController();
        System.out.println(acCtrl);

        acCtrl.displayName(username);
        //root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Account.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        return 0;
    }

    public static boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static int isPasswordValid(String password) {
        // check if the password is at least 8 characters long
        if (password.length() < 8) {
            return 1;
        }
        // use a regular expression to check if the password contains at least one uppercase letter and one number
        String passwordRegex = "^(?=.*[0-9])(?=.*[A-Z]).*$";
        Pattern pat = Pattern.compile(passwordRegex);
        if (password == null) {
            return 2;
        }
        if(pat.matcher(password).matches()== false)
            return 5;
        return 0;
    }

    public int register_new () throws SQLException {
        dbConnection connection = new dbConnection();
        Connection conn = connection.getConnection();
        String email=emailFieldR.getText();
        if(!(isEmailValid(email))){
            validate_label.setVisible(true);
            validate_label.setText("Email format not valid!");
            return 1;
        }else {
            String username = usernameFieldR.getText();
            String password = passwordFieldR.getText();
            if (isPasswordValid(password) == 1) {
                validate_label.setVisible(true);
                validate_label.setText("Password must have at least 8 characters!");
                return 1;
            } else if (isPasswordValid(password) == 2) {
                validate_label.setVisible(true);
                validate_label.setText("You must eneter a valid password!");
                return 1;
            } else if (isPasswordValid(password) == 5) {
                validate_label.setVisible(true);
                validate_label.setText("Password must contain at least one uppercase letter and one number");
                return 1;
            }else {
                String sql = "INSERT INTO users (Nume, Email, Parola) VALUES (?, ?, ?);";
                Statement stmt = conn.createStatement();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, password);
                preparedStatement.execute();
                conn.close();
                stmt.close();
            }
        }
        conn.close();
        return 0;
    }
    public void switchToShopMenu(ActionEvent event) throws IOException {
        root= FXMLLoader.load((getClass().getResource("ShopMenu.fxml")));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLabel();
    }
}
