package models.app_user;

import java.sql.Date;
public abstract class App_User {
    protected int user_id;
    protected String first_name;
    protected String last_name;
    protected String email;
    protected String username;
    protected String password;
    protected String profile_pic;
    protected Date register_date;
    protected String phone_number;
    protected Boolean deleted;

    public App_User(int user_id, String first_name, String last_name, String email,
                    String username,String password, String profile_pic, Date register_date,
                    String phone_number, Boolean deleted) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.profile_pic = profile_pic;
        this.register_date = register_date;
        this.phone_number = phone_number;
        this.deleted = deleted;
    }

    public int getUser_id() { return this.user_id; }
    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public Boolean getDeleted(){return deleted;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser_id(int user_id){ this.user_id = user_id; }
    public void setDeleted(Boolean deleted){ this.deleted = deleted;}
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString(){
        return username + " (" + user_id + ")\n" +
                "----------------\n" +
                "Profile pic: " + profile_pic + "\n" +
                "Full name: " + first_name + " " + last_name + '\n' +
                "Contact " + email + '\n' +
                "Phone: " + phone_number + '\n' +
                "Since: " + register_date + '\n';
    }
}
