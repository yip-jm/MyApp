package com.example.myapplication.utils;


import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtils {
    private static final String TAG = "connect2mysql";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String dbNname = "test";
    private static String user = "root";
    private static String password = "123456";

    public static Connection getConn(){
        Connection connection  = null;
        try{
            Class.forName(driver);
            String ip = "172.26.82.147";
            connection = DriverManager.getConnection("jdbc:mysql://" + ip +":3306/test?useSSL=false", user, password);

        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
