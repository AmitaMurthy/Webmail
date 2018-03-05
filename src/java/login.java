/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.context.FacesContext;

/**
 *
 * @author amitamurthy1
 */
@Named(value = "login")
@SessionScoped
public class login implements Serializable {

    //attributes
    private String id;
    private String password;
    private String firstname;
    private String lastname;
    private welcome user;
    
   
    
    //get methods and set methods
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
    public String getFirstname()
    {
        return firstname;
    }
    public String getLastname()
    {
        return lastname;
    }


    

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setFirstname(String first)
    {
        this.firstname = first;
    }
    public void setLastname(String last)
    {
        this.lastname = last;
    }

    public welcome getUser() {
        return user;
    }

    public void setUser(welcome user) {
        this.user = user;
    }
    
    
    
    
    public String login()
    {
        //load the Driver
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //return to internalError.xhtml
            return ("internalError");
        }
        
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/murthya7617";
        Connection connection = null;  //a connection to the database
        Statement statement = null;    //execution of a statement
        ResultSet resultSet = null;   //set of results
        id = id +"@my.com";
        
        try
        {      
            //connect to the database with user name and password
            connection = DriverManager.getConnection(DATABASE_URL, 
                    "murthya7617", "1600500");   
            statement = connection.createStatement();
            
            resultSet = statement.executeQuery("Select * from user "
                    + "where User_ID ='"+id+"'" );
            
            if(resultSet.next())
            {
                //id is found
                if(password.equals(resultSet.getString(4)))
                {
                    //password is good 
                    //display welcome.xhtml
                    //create an OnlineAccount object
                    user = new welcome(id);
                    return "welcome";
                     
                }
                else
                {
                    id = "";
                    password = "";
                    //display loginNotOK.xhtml
                    return "loginNotOK";    
                }
            }
            else
            {
                id = "";
                password = "";
                //id is not found, display loginNotOK.xhtml
                return "loginNotOK";
                 
            }
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return ("internalError");
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
    public String signOut()
    {
       
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml";

        
    }

        
         
        
} 
    


    


