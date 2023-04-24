package com.example.m1;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    public Connection get_Connection(){
        Connection conn=null;
        try{
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/modul","postgres","1");

        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }
}
