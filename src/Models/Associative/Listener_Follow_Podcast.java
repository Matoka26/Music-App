package Models.Associative;

public class Listener_Follow_Podcast {
    private int listener_id;
    private int podcast_id;

    public Listener_Follow_Podcast(int listener_id, int podcast_id) {
        this.listener_id = listener_id;
        this.podcast_id = podcast_id;
    }

    @Override
    public String toString(){
        return listener_id + " Follows podcast " + podcast_id;
    }
}
