package com.example.magazinBun;

import conn.dbConnection;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class ManageProductsController {

    @FXML
    private TextField search;

    @FXML
    private TextField price;

    @FXML
    private Button addPrice;
    @FXML
    private TableView<Product> productsTable;
    private ObservableList<Product> products = FXCollections.observableArrayList();
    private Thread filterThread;

    private Product p;


    public void initialize() throws SQLException {



        dbConnection connection = new dbConnection();
        Connection conn = connection.getConnection();
        String sql = "SELECT id_produs, nume, pret, brand from produse";
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            Product product = new Product(0, null, null, null);
            product.id = res.getInt("id_produs");
            product.setName(res.getString("nume"));
            product.setBrand(res.getString("brand"));
            product.setPrice(res.getString("pret"));
            products.add(product);
        }
        conn.close();
        stmt.close();

        TableColumn<Product, Void> editColumn = new TableColumn<>("Edit");
        editColumn.setCellFactory(param -> new TableCell<Product, Void>() {
            private final Button button = new Button("Edit");

            {
                button.setOnAction(event -> {

                    Product product = getTableView().getItems().get(getIndex());


                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editPrice.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load(), 600, 400);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Stage stage = new Stage();
                    stage.setScene(scene);

                    editPriceController controller = fxmlLoader.getController();

                    stage.showAndWait();

                    String newPrice = controller.price1;

                    product.change(newPrice, product.id);

                    Parent root = null;
                    try {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("manageProducts.fxml")));
                         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                         scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }



                });
            }

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }
        });

        productsTable.setItems(products);
        productsTable.getColumns().add(editColumn);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            if (filterThread != null) {
                filterThread.interrupt();
            }
            filterThread = new Thread(() -> {
                if (!newValue.isEmpty()) {
                    ObservableList<Product> filteredProducts = products.filtered(product ->
                            product.getName().toLowerCase().contains(newValue.toLowerCase())
                    );
                    Platform.runLater(() -> productsTable.setItems(filteredProducts));
                } else {
                    Platform.runLater(() -> productsTable.setItems(products));
                }
            });
            filterThread.start();
        });
    }

public void switchToShopMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


}