public class user {
    private String user_id;
    private String firstname;
    private String lastname;
    private String password;

    public user(String user_id, String firstname, String lastname, String password) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
