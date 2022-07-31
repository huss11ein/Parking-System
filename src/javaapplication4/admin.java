/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author User
 */
 public class admin {
 public String firstname;
 public String lastname;
 public String platenumber;
 public String username;
 public String password;
 public String typedif;
 private int id;
 public boolean check_id_C(int Id) throws SQLException, ClassNotFoundException{
     boolean check=false;
         Connection con=DBconnect.connect();
         String query = "select * from customer";
         Statement stmt = con.createStatement();
          java.sql.ResultSet rs =  stmt.executeQuery(query);
            while (rs.next()) {
                int idfromdb=rs.getInt(1);
            if(Id==idfromdb)
            {
            check=true;
            firstname=rs.getString(2);
            lastname=rs.getString(3);
            platenumber=rs.getString(4);
            id=rs.getInt(1);
           
            }
            
            }
       return check; }
     public boolean check_id_AO(int Id) throws SQLException, ClassNotFoundException{
         boolean check=false;
         Connection con=DBconnect.connect();
         String query = "select * from users WHERE id=?";
 PreparedStatement  pst = con.prepareStatement(query);
 pst.setInt(1,Id);
          java.sql.ResultSet rs =  pst.executeQuery();
            while (rs.next()) {
                int idfromdb=rs.getInt(1);
            if(Id==idfromdb)
            {
            check=true;
            username=rs.getString(2);
            password=rs.getString(3);
            typedif=rs.getString(4);          
            }
            
            }
            System.out.print(check);
       return check;
        }
          public boolean check_id_AO(int Id,String yu) throws SQLException, ClassNotFoundException{
         boolean check=false;
         Connection con=DBconnect.connect();
         String query = "select * from users WHERE id=? AND usertype=?";
 PreparedStatement  pst = con.prepareStatement(query);
 pst.setInt(1,Id);
 pst.setString(2,yu);
          java.sql.ResultSet rs =  pst.executeQuery();
            while (rs.next()) {
                int idfromdb=rs.getInt(1);
            if(Id==idfromdb)
            {
            check=true;
            username=rs.getString(2);
            password=rs.getString(3);
            typedif=rs.getString(4);          
            }
            
            }
            System.out.print(check);
       return check;
        }

 public void update_C ( String fn, String ln, String pn) throws SQLException, ClassNotFoundException{
  Connection con=DBconnect.connect();
         String query ="update customer  SET username=?, lastname =?,platenumber=? where id=?";
 PreparedStatement  pst = con.prepareStatement(query);
 pst.setString(1, fn);
 pst.setString(2, ln);
 pst.setString(3, pn);
 pst.setInt(4, id);
 pst.executeUpdate();
    }
  public void update_AO ( int ID,String un, String pass, String tp) throws SQLException, ClassNotFoundException{
  Connection con=DBconnect.connect();
         String query ="update users  SET username=?, password =?, usertype=? where id=? ";
 PreparedStatement  pst = con.prepareStatement(query);
 pst.setString(1, un);
 pst.setString(2, pass);
 pst.setString(3, tp);
 pst.setInt(4, ID);
 pst.executeUpdate();
    }
 public void del_C (int userID) throws SQLException, ClassNotFoundException{
                Connection con=DBconnect.connect();
         String query ="delete from customer where id=?  ";
 PreparedStatement  pst = con.prepareStatement(query);
 pst.setInt(1, userID);
 pst.executeUpdate();
    }
  public void del_AO (int userID) throws SQLException, ClassNotFoundException{
                Connection con=DBconnect.connect();
         String query ="delete from users where id=?  ";
 PreparedStatement  pst = con.prepareStatement(query);
 pst.setInt(1, userID);
 pst.executeUpdate();
    }
   public void Add_AO(String usr, String pswrd, String Role) throws SQLException, ClassNotFoundException{
       Connection con=DBconnect.connect();
        String query ="insert into users (username, password, usertype) values ('"+usr+"','"+pswrd+"','"+Role+"')";
   PreparedStatement  pst = con.prepareStatement(query);
 pst.executeUpdate(); 
   }
   public void Add_C(String Fname, String Lname, String platenum) throws SQLException, ClassNotFoundException{
       Connection con=DBconnect.connect();
        String query ="insert into customer (username, lastname, platenumber) values (?,?,?)";
   PreparedStatement  pst = con.prepareStatement(query);
   pst.setString(1, Fname);
   pst.setString(2, Lname);
   pst.setString(3, platenum);
 pst.executeUpdate(); 
   }
   public boolean checkuser(String usrname,String typ) throws ClassNotFoundException, SQLException{
    boolean check=true;
         Connection con=DBconnect.connect();
         String query = "select * from users WHERE username=? and usertype=?";
          PreparedStatement ps = con.prepareStatement(query);
          ps.setString(1, usrname);
          ps.setString(2, typ);
          java.sql.ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String usernamefromdb=rs.getString(2);
                String typefromdb=rs.getString(4);
            if(usrname.equals(usernamefromdb)||typefromdb.equals(typ))
            {
            check=false;
           
            }
            }
       return check;
   
   
   }
    
 
        
}
