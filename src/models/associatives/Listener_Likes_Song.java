package models.associatives;

public class Listener_Likes_Song {
    private int listener_id;
    private int song_id;

    public Listener_Likes_Song(int listener_id, int song_id) {
        this.listener_id = listener_id;
        this.song_id = song_id;
    }

    @Override
    public String toString() {
        return listener_id + " Likes Song " + song_id;
    }
}
