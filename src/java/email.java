

/**
 *
 * @author amitamurthy1
 */
public class email {
    
    private String date;
    private String sender;
    private String recipient;
    private String title;
    private String Content;
    private int status;
    private int email_id;
    private int reply_id;
    private int notstat;
    private String senderFirstname;
    private String senderLastname;
    private int trash;

 


    public email(String date, String sender, String recipient, String title, String Content, int status, int email_id, int reply_id, int notstat , int trash,String first,String last) {
        this.date = date;
        this.sender = sender;
        this.recipient = recipient;
        this.title = title;
        this.Content = Content;
        this.status = status;
        this.email_id = email_id;
        this.reply_id = reply_id;
        this.notstat = notstat;
        this.trash = trash;
        this.senderFirstname = first;
        this.senderLastname = last;
        

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public int getNotstat() {
        return notstat;
    }

    public void setNotstat(int notstat) {
        this.notstat = notstat;
    }

    public String getSenderFirstname() {
        return senderFirstname;
    }

    public void setSenderFirstname(String senderFirstname) {
        this.senderFirstname = senderFirstname;
    }

    public String getSenderLastname() {
        return senderLastname;
    }

    public void setSenderLastname(String senderLastname) {
        this.senderLastname = senderLastname;
    }

    public int getTrash() {
        return trash;
    }

    public void setTrash(int trash) {
        this.trash = trash;
    }
    
    
    
}
