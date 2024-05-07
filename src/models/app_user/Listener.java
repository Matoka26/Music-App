package models.app_user;

import java.sql.Date;
import java.util.Scanner;

public class Listener extends App_User{
    private int listener_id;
    private int time_played;

    public Listener(int user_id, String first_name, String last_name,
                    String email, String username, String password, String profile_pic,
                    Date register_date, String phone_number, Boolean deleted,
                    int listener_id, int time_played) {
        super(user_id, first_name, last_name, email, username,password, profile_pic, register_date, phone_number, deleted);
        this.listener_id = listener_id;
        this.time_played = time_played;
    }

    public int getTime_played() {
        return time_played;
    }
    public int getListener_id() {
        return listener_id;
    }

    public void setListener(int listener_id) {
        this.listener_id = listener_id;
    }

    public void setTime_played(int time_played) {
        this.time_played = time_played;
    }

    @Override
    public String toString(){
        return super.toString() +
                "Time played: " + time_played;
    }

    public static Listener createListener(){
        Scanner sc = new Scanner(System.in);

        String first_name, last_name, email, username, password, profile_pic, phone_number;
        Date register_date;

        System.out.println("First name: ");
        first_name = sc.nextLine();
        System.out.println("Last name: ");
        last_name = sc.nextLine();
        System.out.println("Email: ");
        email = sc.nextLine();
        System.out.println("Username: ");
        username = sc.nextLine();
        System.out.println("Password: ");
        password = sc.nextLine();
        System.out.println("Profile picture: ");
        profile_pic = sc.nextLine();
        System.out.println("Phone number: ");
        phone_number = sc.nextLine();
        register_date = new Date(System.currentTimeMillis());

        return new Listener(0, first_name, last_name, email, username,
                            password, profile_pic, register_date, phone_number, false, 0, 0);
    }
}
