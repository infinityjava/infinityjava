/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.infinity.resources.utils;

import java.io.File;
import java.sql.*;

/**
 *
 * @author kareem
 */
public class CommonUtils {

    private static Connection conn = null;
    private static String DatabaseURL = "jdbc:mysql://192.168.1.181:3306/infinity";
    //private static String DatabaseURL = "jdbc:mysql://localhost:3306/infinity";
    private static File passwordFile = new File("D:\\Coding\\accessdetails.txt");
    private static String encryptedEntities = "";

    private static Connection createConnection() {
        try {
            encryptedEntities = ReadTextFile.getUsernamePasswordFromFlatFile(passwordFile);
            String encryptedUsername = (encryptedEntities.split(","))[0];
            String encryptedPassword = (encryptedEntities.split(","))[1];
            CryptoUtil cu = new CryptoUtil();
            String decryptedUsername = cu.decrypt(encryptedUsername);
            String decryptedPassword = cu.decrypt(encryptedPassword);
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DatabaseURL, decryptedUsername, decryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    private CommonUtils() {
    }

    public static Connection database_connect(String username, String password) {


        if (conn == null) {
            try {

                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(DatabaseURL, username, password);
                if (conn != null) {
                    System.out.println("Database connection established in constants ");
                }
            } catch (Exception e) {
                System.out.println("Cannot connect to database server");
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static Connection database_connect() {
        if (conn != null) {
            return conn;
        } else {
            conn = createConnection();
            return conn;
        }
    }
}
