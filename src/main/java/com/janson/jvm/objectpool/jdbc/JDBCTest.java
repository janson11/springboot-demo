package com.janson.jvm.objectpool.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2023/2/25 16:46
 **/
public class JDBCTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/architecture?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=Asia/Shanghai",
                "root",
                "123456");

        PreparedStatement preparedStatement = connection.prepareStatement("select * from `architecture`.orders;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("id"));
            System.out.println(resultSet.getString("user_id"));
        }

        resultSet.close();
        preparedStatement.close();
//        connection.close();
//        Exception in thread "main" java.sql.SQLNonTransientConnectionException: No operations allowed after connection closed.
        System.out.println("-----------------");
        PreparedStatement preparedStatement2 = connection.prepareStatement("select * from `architecture`.orders;");
        ResultSet resultSet2 = preparedStatement2.executeQuery();
        while (resultSet2.next()) {
            System.out.println(resultSet2.getString(1));
            System.out.println(resultSet2.getString(2));
        }

        resultSet2.close();
        preparedStatement2.close();


        connection.close();


    }
}
