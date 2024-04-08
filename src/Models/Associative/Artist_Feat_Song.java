package Models.Associative;

public class Artist_Feat_Song {
    private int artist_id;
    private int song_id;

    public Artist_Feat_Song(int artist_id, int song_id) {
        this.artist_id = artist_id;
        this.song_id = song_id;
    }

    @Override
    public String toString() {
        return artist_id + " Feats " + song_id;
    }
}
