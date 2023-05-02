package com.example.magazinBun;
import conn.dbConnection;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class ManageOrdersController {

    @FXML
    private TextField search;

    @FXML
    private TableView<Order> OrdersTable;
    private ObservableList<Order> orders = FXCollections.observableArrayList();
    private Thread filterThread;

    public Order selectedOrder;


    public void initialize() throws SQLException {


        TableColumn<Order, Void> editColumn = new TableColumn<>("Confirm");
        editColumn.setCellFactory(param -> new TableCell<Order, Void>() {
            private final Button button = new Button("Confirm!");
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
        chooseOrderClick();

        OrdersTable.getColumns().add(editColumn);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            if (filterThread != null) {
                filterThread.interrupt();
            }
            filterThread = new Thread(() -> {
                if (!newValue.isEmpty()) {
                    ObservableList<Order> filteredOrders = orders.filtered(order ->
                            String.valueOf(order.getId_command()).contains(newValue)
                    );
                    Platform.runLater(() -> OrdersTable.setItems(filteredOrders));
                } else {
                    Platform.runLater(() -> OrdersTable.setItems(orders));
                }
            });
            filterThread.start();
        });
    }

    @FXML
    private void chooseOrderClick() throws SQLException {
            selectedOrder = null;

        dbConnection connection = new dbConnection();
        Connection conn = connection.getConnection();
        String sql = "SELECT id_comanda, id_client, data, adresa, prodIds, confirmed  from comenzi";
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            Order order = new Order(-1, "", -1, "", false);
            order.setId_user(res.getInt("id_client"));
            order.address=(res.getString("adresa"));
            order.id_command=(res.getInt("id_comanda"));
            order.setProductList(res.getString("prodIds"));
            order.setConfirmed(res.getBoolean("confirmed"));
            orders.add(order);
        }


            for(Order o: orders) {
                if(o.getConfirm()==false)
                    o.status="Unconfirmed";
                else
                    o.status="Confirmed";

                o.confirm = new Button();
                o.confirm.setUserData(o);
                o.confirm.setText("Confirm!");


                o.confirm.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            ConfirmButtonClick(o.confirm);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }

            OrdersTable.setItems(orders);
            conn.close();
            stmt.close();

        }


    private void ConfirmButtonClick(Button conf) throws SQLException {

        int confirmed = 0;
        Order ord = (Order) conf.getUserData();
        for(Order o: orders)
            if(o.getId_command()== ord.getId_command())
            {
                o.confirmed=true;
                confirmed=1;

            }

        String SQL="UPDATE comenzi SET confirmed="+ confirmed+" WHERE id_comanda="+ord.getId_command()+";";
        dbConnection dbConn = new dbConnection();
        Connection connection = dbConn.getConnection();

        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        connection.close();

//       orders.clear();
//
//        List<Order> studentList = Student.getStudents();
//        for(Student s: studentList)
//            if(s.userId == activeStudent.userId){
//                for(regEntry ren: s.regEntries)
//                    if(ren.value==0)
//                        entries.add(ren);
//
//                break;
//            }
//
//        Map<Integer, String> subjNames=Subject.initSubject();
//
        for(Order o: orders) {
            if(o.getConfirm()==false)
                o.status="Unconfirmed";
            else
                o.status="Confirmed";

            o.confirm = new Button();
            o.confirm.setUserData(o);

            o.confirm.setText("Confirm!");
            o.confirm.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        ConfirmButtonClick(o.confirm);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

      OrdersTable.setItems(orders);

    }

    public void switchToShopMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


}