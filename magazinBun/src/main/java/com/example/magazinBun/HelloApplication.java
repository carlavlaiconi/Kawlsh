package com.example.magazinBun;

import conn.dbConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class HelloApplication extends Application {
    @Override
    public void start(@NotNull Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SlideShow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) throws SQLException, IOException {
        Properties prop = new Properties();
        InputStream input = new FileInputStream("src/main/java/com/example/magazinBun/Prp.properties");
        prop.load(input);
        ProgrProperties.dbcon = prop.getProperty("link_db.url");
        dbConnection dbConn = new dbConnection();
        Connection conn = dbConn.getConnection();
        Statement stmt = conn.createStatement();
        conn.close();
        launch();
    }
}















//dbConnection dbConn = new dbConnection();
//Connection conn = dbConn.getConnection();

//Statement stmt = conn.createStatement();

//conn.close();
