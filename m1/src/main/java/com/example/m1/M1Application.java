package com.example.m1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.Statement;

@SpringBootApplication
public class M1Application {

    public static void main(String[] args) {

        SpringApplication.run(M1Application.class, args);

        checkTables();
    }


    private static void checkTables (){
        Connection connection=null;
        Statement statement=null;
        Connect c=new Connect();
        connection=c.get_Connection();
        try{
            String query="create table if not exists Groupss ( id SERIAL PRIMARY KEY , name varchar(10))";
            statement=connection.createStatement();
            statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            String query="create table if not exists People(id SERIAL primary key ,id_group integer default 1,name varchar(15), surname varchar(15), surname2 varchar(15), foreign key(id_group) references Groupss(id))";
            statement=connection.createStatement();
            statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            String query="create table if not exists DetailInformation ( id SERIAL PRIMARY KEY , id_owner integer default 1, status varchar(15), form1 varchar(15), form2 varchar(15), email varchar(30), bal float(10),foreign key (id_owner) references People (id))";
            statement=connection.createStatement();
            statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
