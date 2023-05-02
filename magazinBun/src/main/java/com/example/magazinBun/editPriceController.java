package com.example.magazinBun;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class editPriceController {

    @FXML
    private TextField price;

    @FXML
    private Button save;

    public String price1 = null;

    public void saveClick(ActionEvent event) {
        if(price.getText().isEmpty())
            price.setPromptText("Type in a price!");
        else
        {
            price1 = price.getText();
            Stage stageToClose = (Stage) save.getScene().getWindow();
            stageToClose.close();
        }


    }
}
