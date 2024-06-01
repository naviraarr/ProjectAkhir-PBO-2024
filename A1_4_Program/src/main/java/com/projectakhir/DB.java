package com.projectakhir;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
     Connection con;
    public DB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbjobportal", "root", "");
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
