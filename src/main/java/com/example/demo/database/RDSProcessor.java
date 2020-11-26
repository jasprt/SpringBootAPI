package com.example.demo.database;

import com.example.demo.City;
import java.sql.*;
import java.util.ArrayList;

public class RDSProcessor {

    private static final RDSProcessor INSTANCE = new RDSProcessor();

    private RDSProcessor() {
    }

    public static RDSProcessor getInstance() {
        return INSTANCE;
    }

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String PASSWORD = "1184";
        String URI = "jdbc:mysql://localhost:3306/world";
        String USERNAME = "root";
        return DriverManager.getConnection(URI, USERNAME, PASSWORD);
    }

    public ArrayList<City> queryCity(String query) throws Exception {
        Connection connection = RDSProcessor.getInstance().getConnection();
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        ResultSet result = statement.executeQuery(query);
        ArrayList<City> list = new ArrayList<>();
        while (result.next()) {
            City city = new City(result.getString(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5));
            list.add(city);
        }

        statement.close();
        connection.close();
        return list;

    }
}
