
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author amitamurthy1
 */
public class welcome {
    
    
    private ArrayList<email> inboxMails;
    private ArrayList<email> allMails;
    private ArrayList<email> sentMails;
    private ArrayList<user> systemUsers;
    private ArrayList<notification> notifications;
    private ArrayList<email> trashMails;
    private email Current;
    private email Previous;
    private email trashEmail;
    private String replyContent;
    private boolean notify;
    private boolean notifyCompose;
    private String forwardTo;
    int email_id;
    int reply_id;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDate localDate = LocalDate.now();
    private String composeRecepient;
    private String composeTitle;
    private String composeContent;
    


    public welcome(String un) {
        String username = un;
        inboxMails = new ArrayList<email>();
        trashMails = new ArrayList<email>();
        allMails = new ArrayList<email>();
        sentMails = new ArrayList<email>();
        systemUsers = new ArrayList<user>();
        notifications = new ArrayList<notification>();
        
        
   
        
       final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/murthya7617";
       Connection connection =null;
       Statement statement = null;
       ResultSet rs =null;
       
       
       try{
          
           
           connection = DriverManager.getConnection(DATABASE_URL, "murthya7617","1600500");
           statement=connection.createStatement();
           
           
           rs = statement.executeQuery("Select v.*,s.Firstname, s.Lastname  from user s, email v where s.User_ID = v.sender and v.Recipient = '"+username+"' and v.trash=0");
           
           while(rs.next())
           {
               
               inboxMails.add(new email(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11),rs.getString(12)));
           }
           

        
           
           rs = statement.executeQuery("Select v.*,s.Firstname, s.Lastname  from user s, email v where s.User_ID = v.recipient and v.Sender = '"+username+"'");
           while(rs.next())
           {
              sentMails.add(new email(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11),rs.getString(12)));
           }
           
           rs = statement.executeQuery("Select v.*,s.Firstname, s.Lastname  from user s, email v where (s.User_ID = v.recipient or s.User_ID = v.sender)and v.trash=0");
           while(rs.next())
           {
              allMails.add(new email(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11),rs.getString(12)));
           }
           
           
           
           rs = statement.executeQuery("Select * from user");
           while(rs.next())
           {
               systemUsers.add(new user(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
           }
           
           rs = statement.executeQuery("Select * from notification where Recipient='"+username+"' order by date desc");
           while(rs.next())
           {
               notifications.add(new notification(rs.getString(1), rs.getString(2), rs.getString(3)));
           }
           
           rs = statement.executeQuery("Select v.*,s.Firstname, s.Lastname  from user s, email v where s.User_ID = v.sender and v.Recipient = '"+username+"' and v.trash=1");
           while(rs.next())
           {
               
               trashMails.add(new email(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11),rs.getString(12)));
           }
           
           }
       catch(SQLException e)
           {
           e.printStackTrace();
          
           }
       finally{
           try{
               connection.close();
               statement.close();
               rs.close();
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
       }
        
        
        
        
    }

   

    public void setInboxMails(ArrayList<email> inboxMails) {
        this.inboxMails = inboxMails;
    }

    public ArrayList<email> getSentMails() {
        return sentMails;
    }

    public void setSentMails(ArrayList<email> sentMails) {
        this.sentMails = sentMails;
    }

    public ArrayList<user> getSystemUsers() {
        return systemUsers;
    }

    public void setSystemUsers(ArrayList<user> systemUsers) {
        this.systemUsers = systemUsers;
    }

    
    public ArrayList<notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<notification> notifications) {
        this.notifications = notifications;
    }

    public ArrayList<email> getAllMails() {
        return allMails;
    }

    public void setAllMails(ArrayList<email> allMails) {
        this.allMails = allMails;
    }

    public email getCurrent() {
        return Current;
    }

    public void setCurrent(email Current) {
        this.Current = Current;
    }

    public email getPrevious() {
        return Previous;
    }

    public void setPrevious(email Previous) {
        this.Previous = Previous;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    public String getForwardTo() {
        return forwardTo;
    }

    public void setForwardTo(String forwardTo) {
        this.forwardTo = forwardTo;
    }

    public DateTimeFormatter getDtf() {
        return dtf;
    }

    public void setDtf(DateTimeFormatter dtf) {
        this.dtf = dtf;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public int getEmail_id() {
        return email_id;
    }

    public void setEmail_id(int email_id) {
        this.email_id = email_id;
    }

    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.reply_id = reply_id;
    }
    
     public String getComposeRecepient() {
        return composeRecepient;
    }

    public void setComposeRecepient(String composeRecepient) {
        this.composeRecepient = composeRecepient;
    }

    public String getComposeTitle() {
        return composeTitle;
    }

    public void setComposeTitle(String composeTitle) {
        this.composeTitle = composeTitle;
    }

    public String getComposeContent() {
        return composeContent;
    }

    //used for compose email
    public void setComposeContent(String composeContent) {
        this.composeContent = composeContent;
    }

    public boolean isNotifyCompose() {
        return notifyCompose;
    }

    public void setNotifyCompose(boolean notifyCompose) {
        this.notifyCompose = notifyCompose;
    }

    public ArrayList<email> getTrashMails() {
        return trashMails;
    }

    public void setTrashMails(ArrayList<email> trashMails) {
        this.trashMails = trashMails;
    }

    public email getTrashEmail() {
        return trashEmail;
    }

    public void setTrashEmail(email trashEmail) {
        this.trashEmail = trashEmail;
    }

    public ArrayList<email> getInboxMails() {
        return inboxMails;
    }
    
    


    
    
    
    
     public String getInMails()
     {
       
        
        String title = "";
        int stat =1;
        for(int i=0; i<inboxMails.size(); i++)
        {
            if(inboxMails.get(i).getStatus()==0)
            {
              
              
       final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/murthya7617";
       Connection connection =null;
       Statement statement = null;
       ResultSet rs =null;
       
       
       try{
          
           
           connection = DriverManager.getConnection(DATABASE_URL, "murthya7617","1600500");
           statement=connection.createStatement();
           
           
           int upload = statement.executeUpdate("Update email set Status = 1 where Email_ID='"+inboxMails.get(i).getEmail_id()+"' and Reply_ID='"+inboxMails.get(i).getReply_id()+"' ");
       
           }
       catch(SQLException e)
           {
           e.printStackTrace();
          
           }
       finally{
           try{
               connection.close();
               statement.close();
               rs.close();
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
       }
       
              title = "(new)"+inboxMails.get(i).getTitle();
              inboxMails.get(i).setTitle(title);
              inboxMails.get(i).setStatus(stat);
            
       
            }
        }
        return "inbox";
    }
    
    

    
    public String viewEmail(int email_id, int reply_id, int notStat){
        
        
        
        int id= email_id;
        int rid = reply_id;
        int count =0;
        String message = "";
        
        for(int i=0; i<allMails.size(); i++)
        {
            if(allMails.get(i).getEmail_id()==id)
            {
                if(allMails.get(i).getReply_id()==rid)
                {
               Current = new email(allMails.get(i).getDate(), allMails.get(i).getSender(), allMails.get(i).getRecipient(), allMails.get(i).getTitle(), allMails.get(i).getContent(), allMails.get(i).getStatus(), allMails.get(i).getEmail_id(), allMails.get(i).getReply_id(), allMails.get(i).getNotstat(), allMails.get(i).getTrash(), allMails.get(i).getSenderFirstname(), allMails.get(i).getSenderLastname());
               count =i;
               break;
                
                }
                
                
            }
        }
        
         for(int i=0; i<allMails.size(); i++)
        {
            if(allMails.get(i).getEmail_id()==id)
            {
                if(allMails.get(i).getReply_id()==rid-1)
                {
               Previous = new email(allMails.get(i).getDate(), allMails.get(i).getSender(), allMails.get(i).getRecipient(), allMails.get(i).getTitle(), allMails.get(i).getContent(), allMails.get(i).getStatus(), allMails.get(i).getEmail_id(), allMails.get(i).getReply_id(), allMails.get(i).getNotstat(), allMails.get(i).getTrash(), allMails.get(i).getSenderFirstname(), allMails.get(i).getSenderLastname());
               break;
                
                }
                
                
            }
        }
         
         message = "Your message to "+ allMails.get(count).getRecipient() +" is read";
         
         if(notStat ==0)
         {
       final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/murthya7617";
       Connection connection =null;
       Statement statement = null;
       ResultSet rs =null;
       
       
       try{
          
           
           connection = DriverManager.getConnection(DATABASE_URL, "murthya7617","1600500");
           statement=connection.createStatement();
           
           int upload = statement.executeUpdate("Insert into notification values('"+dtf.format(localDate)+"','"+allMails.get(count).getSender()+"','"+message+"')");
           upload = statement.executeUpdate("Update email set notificationStatus= 1 where Email_ID='"+id+"' and  Reply_ID = '"+rid+"' ");
       
           }
       catch(SQLException e)
           {
           e.printStackTrace();
          
           }
       finally{
           try{
               connection.close();
               statement.close();
               rs.close();
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
       } 
      notStat =1;
      allMails.get(count).setNotstat(notStat);
      Current.setNotstat(notStat);
         }
         
         
        

         if(Previous != null)
         {
             return "viewEmail1";
         }
         else
         {
            return "viewEmail2"; 
         }
             
    
       
    }
    
    public String reply(int email_id, int reply_id, String recipient, String sender, String title)
    {
        int eid = email_id;
        int rid = reply_id+1;
        String r = recipient;
        String s = sender;
        String t = "RE:"+title; 
        int status = 0;
        int n =0;
        
        if(notify == true)
        {
            n= 1;
        }
        else
        {
            n=0;
        }  
        
            
       final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/murthya7617";
       Connection connection =null;
       Statement statement = null;
       ResultSet rs =null;
       
       
       try{
          
           
           connection = DriverManager.getConnection(DATABASE_URL, "murthya7617","1600500");
           statement=connection.createStatement();
           
            rs = statement.executeQuery("Select Reply_ID from email where Email_ID='"+eid+"' order by Reply_ID desc");
           
           if(rs.next())
           {
               rid = rs.getInt(1)+1;
           }
           
           int upload = statement.executeUpdate("Insert into email values('"+dtf.format(localDate)+"', '"+r+"', '"+s+"', '"+t+"', '"+replyContent+"', '"+status+"', '"+email_id+"', '"+rid+"','"+ n+"',0)");
           
           }
       catch(SQLException e)
           {
           e.printStackTrace();
          
           }
       finally{
           try{
               connection.close();
               statement.close();
               rs.close();
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
       }
        
        return "reply";
    }
    
    public String forward(int email_id, int reply_id, String recipient, String title, String content)
    {
        int eid = email_id;
        int rid = reply_id+1;
        String s = recipient;
        String t = "FWD:"+title; 
        String c= content;
        int status = 0;
        int n =0;
        
        if(notify == true)
        {
            n= 1;
        }
        else
        {
            n=0;
        }  
        
            
       final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/murthya7617";
       Connection connection =null;
       Statement statement = null;
       ResultSet rs =null;
       
       
       try{
          
           
           
           connection = DriverManager.getConnection(DATABASE_URL, "murthya7617","1600500");
           statement=connection.createStatement();
           
           rs = statement.executeQuery("Select Reply_ID from email where Email_ID='"+eid+"' order by Reply_ID desc");
           
           if(rs.next())
           {
               rid = rs.getInt(1)+1;
           }
           
           int upload = statement.executeUpdate("Insert into email values('"+dtf.format(localDate)+"', '"+s+"', '"+forwardTo+"', '"+t+"', '"+c+"', '"+status+"', '"+email_id+"', '"+rid+"','"+ n+"',0)");
           
           }
       catch(SQLException e)
           {
           e.printStackTrace();
          
           }
       finally{
           try{
               connection.close();
               statement.close();
               rs.close();
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
       }
        
        return "forward";
    }
    
     public void SentEmail() {
       
        for(email a:sentMails)
        {
            System.out.print(a.getRecipient()+ " ");
            System.out.println(a.getTitle());   
        }
        

        
    }
    
   
    public String getEmailByID(int eid, int rid)
    {
         email_id = eid;
         reply_id=rid;
         return "ViewSentEmail";         
    
    }
    
    public email readSelectedEmail()
    {
        for(email e: sentMails)
        {
            if(e.getEmail_id()== email_id && e.getReply_id()==reply_id)
            {
                return e;
            }
        }
        
        return null;
    }
    
    public String composeEmail(String ID)
    {
       int eid=0;
       final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/murthya7617";
       Connection connection =null;
       Statement statement = null;
       ResultSet rs = null;
        int n =0;
        
        if(notifyCompose == true)
        {
            n= 0;
        }
        else
        {
            n=1;
        }  
       
       
       try{
          
           
           connection = DriverManager.getConnection(DATABASE_URL, "murthya7617","1600500");
           statement=connection.createStatement();
           rs = statement.executeQuery("Select email_id from email order by email_id desc");
           
           
           if(rs.next())
           {
               eid = rs.getInt(1)+1;
           }
           
           int result = statement.executeUpdate("Insert into email values('"+dtf.format(localDate)+"','" + ID + "','" + composeRecepient + "','" + composeTitle + "','" + composeContent + "',0,'"+ eid +"',0,'"+n+"',0)");
           
       
           }
       catch(SQLException e)
           {
           e.printStackTrace();
          
           }
       finally{
           try{
               connection.close();
               statement.close();
               rs.close();
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
       }
       return "composeSuccessful";
    }
    
    
    
    public String deleteMail(int email_id, int reply_id)
    {
        int eid = email_id;
        int rid= reply_id;
        
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/murthya7617";
        Connection connection =null;
        Statement statement = null;
        
              
       
       try{
          
           
           connection = DriverManager.getConnection(DATABASE_URL, "murthya7617","1600500");
           statement=connection.createStatement();
           int update = statement.executeUpdate("Update email set trash=1 where email_id ='"+eid+"' and reply_id='"+rid+"'");
               
           
          
       
           }
       catch(SQLException e)
           {
           e.printStackTrace();
          
           }
       finally{
           try{
               connection.close();
               statement.close();
               
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
        
              }
    
       return "deleteSuccessful";
   
        }
    
    
    
    
    public String viewTrash(int email_id, int reply_id)
    {
        int eid = email_id;
        int rid = reply_id;
        
        
         final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/murthya7617";
        Connection connection =null;
        Statement statement = null;
        ResultSet rs =null;
              
       
       try{
          
           
           connection = DriverManager.getConnection(DATABASE_URL, "murthya7617","1600500");
           statement=connection.createStatement();
           rs=statement.executeQuery("Select v.*,s.Firstname, s.Lastname  from user s, email v where s.User_ID = v.sender and email_id='"+eid+"' and reply_id='"+rid+"'");
               
           if(rs.next())
           {
               trashEmail = new email(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11),rs.getString(12));
           }
          
       
           }
       catch(SQLException e)
           {
           e.printStackTrace();
          
           }
       finally{
           try{
               connection.close();
               statement.close();
               rs.close();
               
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
        
              }
       
       
        
        if(trashEmail==null)
        {
            return "inbox";
        }
        else
        {
            return "viewTrash";
        }
        
    }
}
    
    

