package Models.APP_User;

import java.sql.Date;

public class Listener extends App_User{
    private int listener;
    private int time_played;

    public Listener(int user_id, String first_name, String last_name,
                    String email, String username, String profile_pic,
                    Date register_date, String phone_number, Boolean deleted,
                    int listener, int time_played) {
        super(user_id, first_name, last_name, email, username, profile_pic, register_date, phone_number, deleted);
        this.listener = listener;
        this.time_played = time_played;
    }

    public int getTime_played() {
        return time_played;
    }
    public int getListener() {
        return listener;
    }

    public void setListener(int listener) {
        this.listener = listener;
    }

    public void setTime_played(int time_played) {
        this.time_played = time_played;
    }

    @Override
    public String toString(){
        return super.toString() +
                "Time played: " + time_played;
    }
}
