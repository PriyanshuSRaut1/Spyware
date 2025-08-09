/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Database 
{
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    public Database() throws SQLException
    {
        try{
             
            //MAKE SURE YOU KEEP THE mysql_connector.jar file in java/lib folder
            //ALSO SET THE CLASSPATH
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/spyware","root","raut123");
            System.out.println("connetion success.....");
             
           }
        catch (ClassNotFoundException e) 
        {
            System.out.println("exception : "+e);
        }
    }
        //ip:username,password
        //return boolean
    public Boolean checkAdminLogin(String uname,String pwd)
    {
        try {
            pst=con.prepareStatement("select * from admin where username=? and password=?");           
            pst.setString(1, uname); //this replaces the 1st  "?" in the query for username
            pst.setString(2, pwd);    //this replaces the 2st  "?" in the query for password
            //executes the prepared statement
            rs=pst.executeQuery();
            
            if(rs.next())
            {
                //TRUE iff the query founds any corresponding data
                return true;
            }
            else
            {
                return false;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("error while validating"+e);
            return false;
        }
    }
    
    public Boolean checkCurrentUserExist(String pwd)
    {
        try {
            pst=con.prepareStatement("select * from admin where username=?");           
            pst.setString(1, pwd);
            rs=pst.executeQuery();
            if(rs.next())
            {
                return true;
            }
            else
            {
                return false;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("error while validating"+e);
            return false;
        }
    }
    
      public Boolean checkCurrentPwdExist(String pwd)
    {
        try {
            pst=con.prepareStatement("select * from admin where password=?");           
            pst.setString(1, pwd);
            rs=pst.executeQuery();
            if(rs.next())
            {
                return true;
            }
            else
            {
                return false;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("error while validating"+e);
            return false;
        }
    }
      
     
    
    public Boolean changePassword(String pwd1)
    {
         try {
            pst=con.prepareStatement("update admin set password='"+pwd1+"'");           
            //executes the prepared statement
            int n=pst.executeUpdate();           
            if(n==1)
            {
                //TRUE iff the query founds any corresponding data
                return true;
            }
            else
            {
                return false;
            }
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("error while val"+e);
            return false;
        }
    }
    
    public ResultSet getClientDetails(String empid){
        try {
            pst=con.prepareStatement("select * from client_logs where empid=? order by id desc limit 1,1");
            pst.setString(1, empid);
            rs=pst.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int CheckClientAlert(String empid){
        try {
            pst=con.prepareStatement("select count(*) as tot from client_logs where empid=? and alert = 1 order by id desc limit 1");
            pst.setString(1, empid);
            rs=pst.executeQuery();
            if(rs.next()){
                pst=con.prepareStatement("update client_logs set alert=0 where empid='"+empid+"'");           
                pst.executeUpdate();      
                return rs.getInt("tot");
            }else{
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int CheckClientExist(String empid){
        try {
            pst=con.prepareStatement("select count(*) as tot from client_logs where empid=? order by id desc");
            pst.setString(1, empid);
            rs=pst.executeQuery();
            if(rs.next()){
                if(rs.getInt("tot") > 0){
                    return 1;
                }
            }else{
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int addClientLog(String name,String empid,String practicalno,String dept)
    {
     
        int alert = 0;
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
            String dt=dtf.format(now);  
            
            alert = CheckClientExist(empid);
            
            pst=con.prepareStatement("insert into client_logs ( `name`, `pcno`, `empid`, `department`, `login_datetime`, `alert`) values(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, name);
            pst.setString(2, practicalno);
            pst.setString(3, empid);
            pst.setString(4, dept);
            pst.setString(5, dt);
            pst.setInt(6, alert);
            
            int n=pst.executeUpdate();
            ResultSet keys = pst.getGeneratedKeys(); 
            if(n==1)
            {
                keys.next(); 
                int key = keys.getInt(1);
                return key;
            }
            else
            {
                return 0;
            }
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("error while validating"+e);
            return 0;
        }
    }
    public Boolean updateClientLog(String id)
    {
         try {
             DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
            String dt=dtf.format(now);
            
            pst=con.prepareStatement("update client_logs set logout_datetime='"+dt+"' where empid='"+id+"' order by id desc limit 1");           
            //executes the prepared statement
            int n=pst.executeUpdate();           
            if(n==1)
            {
                //TRUE iff the query founds any corresponding data
                return true;
            }
            else
            {
                return false;
            }
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("error while val"+e);
            return false;
        }
    }
    
    
    public Boolean updateAlertMsg(String id ,String msg)
    {
         try {
                       
            pst=con.prepareStatement("update client_logs set alert_msg='"+msg+"' where empid='"+id+"' order by id desc limit 1");           
            //executes the prepared statement
            int n=pst.executeUpdate();           
            if(n==1)
            {
                //TRUE iff the query founds any corresponding data
                return true;
            }
            else
            {
                return false;
            }
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("error while val"+e);
            return false;
        }
    }
    
    
      public ResultSet searchClients(String sdate,String edate,String dept)
    {
        try {
            sdate=this.dateformat(sdate,"yyyy-MM-dd hh:mm aa","yyyy-MM-dd HH:mm:ss");
            edate=this.dateformat(edate,"yyyy-MM-dd hh:mm aa","yyyy-MM-dd HH:mm:ss");
            System.out.println("start date"+sdate);
            System.out.println("end date "+edate);
            if(sdate.equals("")){
                pst=con.prepareStatement("select * from client_logs where department=? order by id desc");   
            }else{
                pst=con.prepareStatement("select * from client_logs where department=? and login_datetime BETWEEN '"+sdate+"' AND '"+edate+"' order by id desc");
                System.out.println("select * from client_logs where department=? and login_datetime BETWEEN '"+sdate+"' AND '"+edate+"' order by id desc");
            }           
            pst.setString(1, dept);
            rs=pst.executeQuery();
            return rs;
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("error while validating"+e);
            return null;
        }
    }
    
    public String dateformat(String date,String fromformat,String toformat)
    {
        try {
            DateFormat uformatter = new SimpleDateFormat(fromformat);
//            System.out.println(date);
            java.util.Date uctdate = (java.util.Date) uformatter.parse(date);
//            System.out.println(fromformat);
            SimpleDateFormat unewFormat = new SimpleDateFormat(toformat);
            return unewFormat.format(uctdate);
        } catch (ParseException ex) {
            return "";
        }
    }
    
    public ResultSet RefreshClients()
    {
        try {
            pst=con.prepareStatement("select * from client_logs order by id desc");
            rs=pst.executeQuery();
            return rs;
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("error while validating"+e);
            return null;
        }
    }
}

