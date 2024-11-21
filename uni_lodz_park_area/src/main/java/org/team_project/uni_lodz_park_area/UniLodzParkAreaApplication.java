package org.team_project.uni_lodz_park_area;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootApplication
public class UniLodzParkAreaApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniLodzParkAreaApplication.class, args);
        String url = "jdbc:mysql://localhost:3306/park";
        String username = "root";
        String password = "root1234";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("MySQL connection successful!");

            String sql = "SELECT * FROM park_area";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    String park_area_msg = resultSet.getString("park_area_msg");
                    System.out.println(park_area_msg);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }

}
