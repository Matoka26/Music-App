-----------CREATE TALBES-----------------

create table APP_USER(
    user_id         number(6) PRIMARY KEY,
    first_name      varchar2(20) NOT NULL, 
    last_name       varchar2(20) NOT NULL,
    email           varchar2(30) NOT NULL,
    username        varchar2(30) NOT NULL UNIQUE,
    password        varchar2(30) NOT NULL,
    profile_pic     varchar2(255),
    phone_number    varchar2(10) UNIQUE,
    register_date   date DEFAULT SYSDATE,
    deleted         number(1) DEFAULT 0 check(deleted = 1 or deleted = 0)
);

create table ARTIST(
    artist_id           number(6) PRIMARY KEY,
    user_id             number(6),
    description         varchar2(255),
    monthly_listeners   number(9),
    verified            number(1) CHECK (verified = 1 or verified = 0),
    lable               varchar2(30)
);

create table LISTENER(
    listener_id         number(6) PRIMARY KEY,
    user_id             number(6),
    time_played         number(9) DEFAULT 0
);


create table TRACK(
    track_id        number(6) PRIMARY KEY,
    artist_id       number(6),
    name            varchar2(30) NOT NULL,
    picture         varchar2(255) NOT NULL,
    release_date    date DEFAULT SYSDATE
);

create table PODCAST(
    podcast_id      number(6) PRIMARY KEY,
    track_id        number(6),
    topic           varchar2(30),
    description     varchar2(255)
);
        
create table ALBUM(
    album_id      number(6) PRIMARY KEY,
    track_id      number(6),
    genre	  varchar2(20)
);

create table EPISODE(
    episode_id      number(6) PRIMARY KEY,
    podcast_id      number(6),
    title           varchar2(70) NOT NULL,
    host            varchar2(30) NOT NULL,
    guest           varchar2(30),
    duration        number(6) NOT NULL,
    plays           number(9) DEFAULT 0
);

create table SONG(
    song_id     number(9) PRIMARY KEY,
    album_id    number(6),
    title       varchar2(30) NOT NULL,
    duration    number(6) NOT NULL,
    plays       number(9) DEFAULT 0
);

create table LISTENER_LIKES_SONG(
    listener_id     number(6),
    song_id         number(9)
);


------------ADD CONSTRAINTS---------------

alter table ARTIST 
    add constraint fk_user_id FOREIGN KEY(user_id)
                    REFERENCES APP_USER(user_id);
                    
alter table LISTENER
    add constraint fk_user_id_2 FOREIGN KEY(user_id)
                    REFERENCES APP_USER(user_id);
        
alter table TRACK
    add constraint fk_artist_id_track FOREIGN KEY(artist_id)
        references ARTIST(artist_id);
        
alter table PODCAST
    add constraint fk_track_id_podcast FOREIGN KEY(track_id)
        references TRACK(track_id) on delete cascade;

alter table ALBUM
    add constraint fk_track_id_album FOREIGN KEY(track_id)
        references TRACK(track_id) on delete cascade;

alter table EPISODE
    add constraint fk_podcast_id_episode FOREIGN KEY(podcast_id)
        references PODCAST(podcast_id) ON DELETE CASCADE;

alter table SONG
    add constraint fk_album_id_song FOREIGN KEY(album_id)
        references ALBUM(album_id) ON DELETE CASCADE;

alter table LISTENER_LIKES_SONG
    add (constraint pk_lis_saves_song PRIMARY KEY(listener_id,song_id),
         constraint fk_listener_id_lis_saves_song FOREIGN KEY(listener_id) references LISTENER(listener_id) on delete cascade,
         constraint fk_song_id_lis_saves_song FOREIGN KEY(song_id) references SONG(song_id) on delete cascade
         );

