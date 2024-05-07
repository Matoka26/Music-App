package models.associatives;

public class Listener_Follow_Artist {
    private int listener_id;
    private int artist_id;

    public Listener_Follow_Artist(int listener_id, int artist_id) {
        this.listener_id = listener_id;
        this.artist_id = artist_id;
    }

    @Override
    public String toString() {
        return listener_id + " Follows " + artist_id;
    }
}
