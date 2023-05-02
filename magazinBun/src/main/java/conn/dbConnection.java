package conn;

import com.example.magazinBun.ProgrProperties;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnection {

    public Connection databaseLink;

    public Connection getConnection(){

        String databaseName="magazin";
        String databaseUser="root";
        String databasePassword="";
        //String url="jdbc:mysql://127.0.0.1/" +databaseName;
        String url= ProgrProperties.dbcon +databaseName;


        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
            System.out.println("Connected!");

        }catch (Exception e){
            System.out.println("Error connecting to database: ");
            e.printStackTrace();
        }

        return databaseLink;

    }

}
