package javaapplication4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat; 
import java.time.LocalTime;
import java.util.Calendar;
import javax.swing.JOptionPane;


public class operatorstation {
    public String start_time;
    public int slot_id;
    private String end_date;
    private long parkinghours;
    private long parkingmins;
    private long pay; 
    public String platenumber;
    private int ticket_id;

    public operatorstation() {
        this.pay = 0;
        this.parkinghours = 0;
        this.parkingmins = 0;
        this.end_date = null;
        this.start_time = null;
        this.slot_id = 0;

    }
    public void rezero(){
     this.pay = 0;
        this.parkinghours = 0;
        this.parkingmins = 0;
        this.end_date = null;
        this.start_time = null;
        this.slot_id = 0;
    
    }
    
 public  boolean CALCpay(int tiketid) throws ParseException, ClassNotFoundException, SQLException
 {long finalpay=0;

    Connection conn=DBconnect.connect();      
      Statement st = conn.createStatement();
      ResultSet r = st.executeQuery("select * from tickets "+"WHERE parked_hours IS NULL AND parked_mins IS NULL");
      System.out.print(tiketid);
      while (r.next())
      {
          int x = r.getInt(1);
           // 1 refer to columnIndex (ticket_id)
          if (x == tiketid){
              start_time = r.getString(3); //  start date from id tickets ,3 refer to columnIndex 
               slot_id = r.getInt(2);
               platenumber=r.getString(4);
          }// search about slot id , 1 refer to columnIndex (slot_id)
      }
          System.out.print(start_time);
      System.out.print(slot_id);
      System.out.print(platenumber);

 if(slot_id!=0){
     this.ticket_id=tiketid;
        SimpleDateFormat sdf = new SimpleDateFormat( "dd-MM-yyyy HH:mm:ss"); 
         Date date = Calendar.getInstance().getTime();  
                end_date = sdf.format(date);
             
             Date d2 = sdf.parse(end_date); 
        Date d1 = sdf.parse(start_time); 


           long difference_In_Time = d2.getTime() - d1.getTime();  // Calucalte time difference in milliseconds 

           double difference_In_Minutes =  (difference_In_Time / 60000);
             double difference_In_Hours=difference_In_Minutes/60;
             

           parkinghours=(long) (difference_In_Minutes/60);
            parkingmins=(long) ((difference_In_Hours-(double)parkinghours)*60);
           
         double shouldpay = (parkinghours+parkingmins/60)*10  ;
          finalpay=(long) Math.round(shouldpay);
         pay=finalpay;
  return true;

         }
 else{       JOptionPane.showMessageDialog(null,"The ID is not found");
 return false;

 }
        
 }
 public long getpay(){
 return pay;
 }
 
      public  void slotStatues(int slot) throws ClassNotFoundException, SQLException
      {
              Connection conn=DBconnect.connect();      
              String query = "update slots set slot_status = 'empty', car_no = 'empty' where slot_id = ?";
              PreparedStatement preparedStmt = conn.prepareStatement(query);
              preparedStmt.setInt(1, slot);
              // execute the java preparedstatement
               preparedStmt.executeUpdate();
       
              conn.close();
      }
      public void addinshifts() throws ClassNotFoundException, SQLException{
     Connection conn=DBconnect.connect();  
     String query = "update shits set parkedhours = parkedhours+?, parkedmins =parkedmins+?, payment=car_payment+? where slotid = ?";
              PreparedStatement Stmt = conn.prepareStatement(query);
              Stmt.setLong(1, parkinghours);
              Stmt.setLong(2,parkingmins);
              Stmt.setLong(3,pay);
              Stmt.setInt(4,slot_id);
Stmt.executeUpdate();
      
      }
      public void completetickets() throws ClassNotFoundException, SQLException{
       Connection conn=DBconnect.connect();  
     String query = "update tickets set parked_hours = ?, parked_mins =? where ticket_id = ?";
              PreparedStatement Stmt = conn.prepareStatement(query);
              Stmt.setLong(1, parkinghours);
              Stmt.setLong(2,parkingmins);
              Stmt.setLong(3,ticket_id);
              Stmt.executeUpdate();

      
      
      }
          
      
      
}