package view;

import persistence.repositories.*;
import service.Audit;

public abstract class Console {
    protected Audit audit = Audit.getInstance();

    protected ArtistRepository artistRepo;
    protected ListenerRepository listenerRepo;
    protected PodcastRepository podcastRepo;
    protected AlbumRepository albumRepo;
    protected SongRepository songRepo;
    protected EpisodeRepository episodeRepo;
    protected TrackRepository trackRepo;

    public abstract void startMenu();
}
