/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author amitamurthy1
 */
@Named(value = "registration")
@RequestScoped
public class Registration {
    
    private String id;
    private String password;
    private String Firstname;
    private String Lastname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String Firstname) {
        this.Firstname = Firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String Lastname) {
        this.Lastname = Lastname;
    }

   public  Boolean Stringcheck(String word)
        {
         int digitCount =0, charCount=0;
         for(int i=0;i<word.length();i++)
         {    
            if((word.charAt(i) >='a' && word.charAt(i)<='z') || (word.charAt(i)>='A' && word.charAt(i)<='Z'))
            {charCount++;
            
            }
            else if(word.charAt(i) >= '0' && word.charAt(i)<='9')
                digitCount++;
         }   
 
          if(charCount>=1 && digitCount>=1)
       {
        return true;  
       }
       else
       {
       return false;    
       }
     } 
       
    public String register()
    {
        //load the driver
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            return ("Internal Error! Please try again later.");
        }
         
        if(Stringcheck(getId())==true && Stringcheck(getPassword())==true)
        {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        
        try
        {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/murthya7617";
            
            //connect to the database with user name and password
            connection = DriverManager.getConnection(DATABASE_URL, 
                    "murthya7617", "1600500");   
            statement = connection.createStatement();
            //to search an onlineaccount based on id or ssn
            resultSet = statement.executeQuery("Select * from User "
                    + "where User_id = '" + 
                    id +"@my.com'" );
            
            if(resultSet.next())
            {
                 return("Either you have an online account already "
                        + "or your online ID is not available to register");
            }
            else
            {
                int r = statement.executeUpdate("insert into User values ('" + id + "@my.com','"+Firstname+"','"+Lastname+"','" +password + "')");
                return ("Registration Successful! Please "
                         + "return to login your account.");
                
            }   
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return ("Internal Error! Please try again later.");
             
        }
        finally
        {
            try
            {
                resultSet.close();
                statement.close();
                connection.close();
               
            }
            catch (Exception e)
            {
                 
                e.printStackTrace();
            }
        }
    }
        else
        {
            return ("Please put correct credentials");
        }
    }
}
         
    
  
     


        
        
        