/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author User
 */
public class Tickets {
    private int ticket_id ;
    private String plate_no;
    private String ticket_date;
    private int slot_id;
        private String firstname;
            private String lastname;
    
    public Tickets(  String plate_no, int slot_id,String first_name,String last_name){
   this.plate_no=plate_no;
   this.slot_id=slot_id;
   this.firstname=first_name;
   this.lastname=last_name;
   Date date = Calendar.getInstance().getTime();  
   DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
   ticket_date = dateFormat.format(date);  
      
  
}
public Tickets(){ 
}
    public int   ticket_id(){
  return this.ticket_id;
}
    public String plate_no(){
        return this.plate_no;
}
    public String ticketdate(){
        return this.ticket_date;
}

    public void addinticketsANDprint(int slot_id) throws ClassNotFoundException, SQLException{
        this.slot_id=slot_id;
    Connection conn=DBconnect.connect();
   try{  String sql = "Select * from  tickets";
      Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery(sql);
     rs.last();
     int previousid=rs.getInt(1);
     ticket_id=previousid+1;
     PreparedStatement pstmt = conn.prepareStatement("INSERT INTO tickets (slotid,tickt_date,car_no) VALUES(?,?,?)");
     pstmt.setInt(2, slot_id);
     pstmt.setString(3, ticket_date);
     pstmt.setString(4, plate_no);
      System.out.println("your ticket id:"+ticket_id+
                    "your Slot number:"+this.slot_id+
                       "your plate number"+plate_no+
                       "The transacion date"+ticket_date
            );

}
     catch(SQLException e){
     System.out.print(e);
     
     }
    }
        
      
    
    
}
