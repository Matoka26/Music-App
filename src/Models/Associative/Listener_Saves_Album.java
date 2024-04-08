package Models.Associative;

public class Listener_Saves_Album {
    private int listener_id;
    private int album_id;

    public Listener_Saves_Album(int listener_id, int album_id) {
        this.listener_id = listener_id;
        this.album_id = album_id;
    }

    @Override
    public String toString() {
        return listener_id + " Saved Album " + album_id;
    }
}
