package com.example.util;

import com.example.model.User;

import java.sql.*;

public class JdbcUserDAO {
    private static final String URL = "jdbc:mariadb://localhost:3306/adt_t4_demo1_clase";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public void insertUser(User user){
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";

        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()){
                user.setId(rs.getLong(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
