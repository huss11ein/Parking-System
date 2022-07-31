/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class person {
    private  boolean check;
    private String myuser;
    private String mypassword;
    
    public void rezero(){
    myuser=null;
    mypassword=null;
    
    }
     public  boolean Checking(String username,String password,String type)throws ClassNotFoundException, SQLException{
      check=false;
         Connection con = null;
         System.out.print(type);
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    String connectionURL="jdbc:sqlserver://localhost:1433;databaseName=ParkingSystem;user=admin;password=admin";
        con=DriverManager.getConnection(connectionURL);
     PreparedStatement   st = con.prepareStatement("SELECT * FROM users WHERE username=? and password=? and usertype=?");
     st.setString(1, username);
     st.setString(2, password);
     st.setString(3, type);
     ResultSet results = st.executeQuery();
     int count=0;
     while (results.next()) { 
   count++;
     }
      if(count==1){
         check=true; 
     }
   return check; }
}
