import Models.APP_User.Artist;
import Models.APP_User.Listener;
import Models.Episode;
import Models.Song;
import Models.Track.Album;
import Models.Track.Podcast;
import Persistence.Repositories.ArtistRepository;

import java.sql.Date;
import java.util.ArrayList;

public abstract class Main {
    public static void main(String[] args) {
        Date date = Date.valueOf("2003-09-24");


        Artist anotherUser = new Artist(101, "John", "Doe",
                "john.doe@example.com", "johndoe123", "profile_pic_url", date, "7193211",false,
                150, "Great artist", 55000, false, "Record Label X", new ArrayList<>());


        ArtistRepository  artistRepo = ArtistRepository.getInstance();

        artistRepo.add(anotherUser);
        artistRepo.delete(anotherUser);

    }
}