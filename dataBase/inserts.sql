
-------INSERT INTO USER--------
--FOR LISTENERS--
INSERT INTO APP_USER VALUES (user_index.nextval, 'John', 'Doe', 'john.doe@example.com', 'johndoe', 'parola', 'profile1.jpg', '1234567890', SYSDATE, 0);
INSERT INTO APP_USER VALUES (user_index.nextval, 'Jane', 'Smith', 'jane.smith@example.com', 'janesmith', 'parola', 'profile2.jpg', '9876543210', SYSDATE, 0);
INSERT INTO APP_USER VALUES (user_index.nextval, 'Michael', 'Johnson', 'michael.johnson@example.com', 'michaelj', 'parola', NULL, '5551234567', SYSDATE, 0);
INSERT INTO APP_USER VALUES (user_index.nextval, 'Emily', 'Brown', 'emily.brown@example.com', 'emilyb', 'parola', 'profile4.jpg', '9998887777', SYSDATE, 0);
INSERT INTO APP_USER VALUES (user_index.nextval, 'David', 'Wilson', 'david.wilson@example.com', 'davidw', 'parola', 'profile5.jpg', '4445556666', SYSDATE, 0);
INSERT INTO APP_USER VALUES (user_index.nextval, 'Sarah', 'Taylor', 'sarah.taylor@example.com', 'saraht', 'parola', NULL, '1234517890', SYSDATE, 0);
INSERT INTO APP_USER VALUES (user_index.nextval, 'Christopher', 'Martinez', 'chris.martinez@example.com', 'chrism', 'parola', 'profile7.jpg', '7777777777', SYSDATE, 0);
INSERT INTO APP_USER VALUES (user_index.nextval, 'Jessica', 'Lee', 'jessica.lee@example.com', 'jessical', 'parola', NULL, '9999999999', SYSDATE, 0);
INSERT INTO APP_USER VALUES (user_index.nextval, 'Daniel', 'Thomas', 'daniel.thomas@example.com', 'danield', 'parola', 'profile9.jpg', '1112223333', SYSDATE, 0);
INSERT INTO APP_USER VALUES (user_index.nextval, 'Amanda', 'Clark', 'amanda.clark@example.com', 'amandac', 'parola', 'profile10.jpg', '5556667777', SYSDATE, 0);

--FOR ARTISTS--
INSERT INTO APP_USER VALUES (user_index.nextval, 'Sarah', 'Taylor', 'sarah.taylor@example.com', 'sarahtay', 'parola', NULL, '1234567810', SYSDATE, 0);
INSERT INTO APP_USER VALUES (user_index.nextval, 'Christopher', 'Martinez', 'chris.martinez@example.com', 'chrismart', 'parola', 'profile7.jpg', '7177777777', SYSDATE, 0);
INSERT INTO APP_USER VALUES (user_index.nextval, 'Jessica', 'Lee', 'jessica.lee@example.com', 'jessicalee', 'parola', NULL, '9999993999', SYSDATE, 0);
INSERT INTO APP_USER VALUES (user_index.nextval, 'Daniel', 'Thomas', 'daniel.thomas@example.com', 'danieltom', 'parola', 'profile9.jpg', '9112223333', SYSDATE, 0);
INSERT INTO APP_USER VALUES (user_index.nextval, 'Amanda', 'Clark', 'amanda.clark@example.com', 'amanda', 'parola', 'profile10.jpg', '5516667777', SYSDATE, 0);
INSERT INTO APP_USER VALUES (user_index.nextval, 'Jennifer', 'Lopez', 'jennifer.lopez@example.com', 'jenniferl', 'parola', 'profile11.jpg', '1223334444', SYSDATE, 0);
INSERT INTO APP_USER VALUES (user_index.nextval, 'Kevin', 'White', 'kevin.white@example.com', 'kevinw', 'parola', NULL, '6667778888', SYSDATE, 0);
INSERT INTO APP_USER VALUES (user_index.nextval, 'Rachel', 'Anderson', 'rachel.anderson@example.com', 'rachela', 'parola', 'profile13.jpg', '8888888888', SYSDATE, 0);
INSERT INTO APP_USER VALUES (user_index.nextval, 'Eric', 'Garcia', 'eric.garcia@example.com', 'ericg', 'parola', NULL, '3334445555', SYSDATE, 0);
INSERT INTO APP_USER VALUES (user_index.nextval, 'Michelle', 'Moore', 'michelle.moore@example.com', 'michellem', 'parola', 'profile15.jpg', '7778889999', SYSDATE, 0);

--------INSERT INTO LISTENER------
INSERT INTO LISTENER VALUES (listener_index.nextval,1,0); 
INSERT INTO LISTENER VALUES (listener_index.nextval,2,0);
INSERT INTO LISTENER VALUES (listener_index.nextval,3,0);
INSERT INTO LISTENER VALUES (listener_index.nextval,4,0);
INSERT INTO LISTENER VALUES (listener_index.nextval,5,0);
INSERT INTO LISTENER VALUES (listener_index.nextval,6,0);
INSERT INTO LISTENER VALUES (listener_index.nextval,7,0);
INSERT INTO LISTENER VALUES (listener_index.nextval,8,0);
INSERT INTO LISTENER VALUES (listener_index.nextval,9,0);
INSERT INTO LISTENER VALUES (listener_index.nextval,10,0);

--------INSERT INTO ARTIST--------------
INSERT INTO ARTIST VALUES (artist_index.nextval, 11, 'Legendary rock band with numerous hit albums', 500000, 1, 'Atlantic Records');
INSERT INTO ARTIST VALUES (artist_index.nextval, 12, 'Soulful RandB singer with a powerful voice', 250000, 1, 'Sony Music Entertainment');
INSERT INTO ARTIST VALUES (artist_index.nextval, 13, 'Experimental avant-garde composer pushing musical boundaries', 10000, 0, 'Independent');
INSERT INTO ARTIST VALUES (artist_index.nextval, 14, 'Rising star in the electronic music scene', 75000, 0, 'EDM Records');
INSERT INTO ARTIST VALUES (artist_index.nextval, 15, 'Chart-topping country music duo known for their catchy tunes', 400000, 1, 'Big Machine Label Group');
INSERT INTO ARTIST VALUES (artist_index.nextval, 16, 'Renowned jazz pianist known for his improvisational skills', 50000, 1, 'BlueNote Records');
INSERT INTO ARTIST VALUES (artist_index.nextval, 17, 'Up-and-coming indie rock band with a unique sound', 20000, 0, 'Indie Records');
INSERT INTO ARTIST VALUES (artist_index.nextval, 18, 'Iconic pop singer with a huge following worldwide', 1000000, 1, 'Universal Music Group');
INSERT INTO ARTIST VALUES (artist_index.nextval, 19, 'Talented rapper known for his clever lyrics and storytelling', 300000, 1, 'Interscope Records');
INSERT INTO ARTIST VALUES (artist_index.nextval, 20, 'Acclaimed classical violinist performing worldwide', 150000, 1, 'Deutsche Grammophon');

-----INSERT INTO TRACK-----------------
INSERT INTO TRACK VALUES (track_index.nextval,7, 'Midnight Sonata', 'midnight_sonata.jpg', TO_DATE('1978-05-21', 'YYYY-MM-DD'));
INSERT INTO TRACK VALUES (track_index.nextval, 4, 'Electric Dreams', 'electric_dreams.jpg', TO_DATE('1984-09-12', 'YYYY-MM-DD'));
INSERT INTO TRACK VALUES (track_index.nextval, 9, 'Eternal Flames', 'eternal_flames.jpg', TO_DATE('1976-11-03', 'YYYY-MM-DD'));
INSERT INTO TRACK VALUES (track_index.nextval, 10, 'Crystal Rain', 'crystal_rain.jpg', TO_DATE('1981-03-28', 'YYYY-MM-DD'));
INSERT INTO TRACK VALUES (track_index.nextval, 2, 'Neon Nights', 'neon_nights.jpg', TO_DATE('1990-12-15', 'YYYY-MM-DD'));
INSERT INTO TRACK VALUES (track_index.nextval, 5, 'Starry Night', 'starry_night.jpg', TO_DATE('1979-06-08', 'YYYY-MM-DD'));
INSERT INTO TRACK VALUES (track_index.nextval, 2, 'Moonlit Sonata', 'moonlit_sonata.jpg', TO_DATE('1982-07-19', 'YYYY-MM-DD'));
INSERT INTO TRACK VALUES (track_index.nextval, 10, 'Echoes of Dawn', 'echoes_of_dawn.jpg', TO_DATE('1985-04-03', 'YYYY-MM-DD'));
INSERT INTO TRACK VALUES (track_index.nextval, 5, 'Whispering Winds', 'whispering_winds.jpg', TO_DATE('1969-10-30', 'YYYY-MM-DD'));
INSERT INTO TRACK VALUES (track_index.nextval, 4, 'Rainbow Melodies', 'rainbow_melodies.jpg', TO_DATE('1988-02-18', 'YYYY-MM-DD'));
INSERT INTO TRACK VALUES (track_index.nextval, 6, 'Golden Memories', 'golden_memories.jpg', TO_DATE('1963-08-02', 'YYYY-MM-DD'));
INSERT INTO TRACK VALUES (track_index.nextval, 3, 'Enchanted Garden', 'enchanted_garden.jpg', TO_DATE('1991-10-05', 'YYYY-MM-DD'));
INSERT INTO TRACK VALUES (track_index.nextval, 1, 'Mystic Moonlight', 'mystic_moonlight.jpg', TO_DATE('1987-12-09', 'YYYY-MM-DD'));
INSERT INTO TRACK VALUES (track_index.nextval, 1, 'Magic Moments', 'magic_moments.jpg', TO_DATE('1975-07-27', 'YYYY-MM-DD'));
INSERT INTO TRACK VALUES (track_index.nextval, 8, 'Sapphire Skies', 'sapphire_skies.jpg', TO_DATE('1958-11-14', 'YYYY-MM-DD'));
INSERT INTO TRACK VALUES (track_index.nextval, 1, 'Whispering Woods', 'whispering_woods.jpg', TO_DATE('1969-03-21', 'YYYY-MM-DD'));
INSERT INTO TRACK VALUES (track_index.nextval, 4, 'Dancing Starlight', 'dancing_starlight.jpg', TO_DATE('1972-09-08', 'YYYY-MM-DD'));
INSERT INTO TRACK VALUES (track_index.nextval, 7, 'Evergreen Echoes', 'evergreen_echoes.jpg', TO_DATE('1986-06-12', 'YYYY-MM-DD'));
INSERT INTO TRACK VALUES (track_index.nextval, 8, 'Golden Sunsets', 'golden_sunsets.jpg', TO_DATE('1965-05-03', 'YYYY-MM-DD'));
INSERT INTO TRACK VALUES (track_index.nextval, 2, 'Ocean Serenade', 'ocean_serenade.jpg', TO_DATE('1997-08-23', 'YYYY-MM-DD'));

--------INSERT INTO ALBUM--------
INSERT INTO ALBUM VALUES(album_index.nextval, 1, 'Rock');
INSERT INTO ALBUM VALUES(album_index.nextval, 2 ,'Jazz');
INSERT INTO ALBUM VALUES(album_index.nextval, 3 ,'Blues');
INSERT INTO ALBUM VALUES(album_index.nextval, 4 , 'Reggae');
INSERT INTO ALBUM VALUES(album_index.nextval, 5 , 'Pop');
INSERT INTO ALBUM VALUES(album_index.nextval, 6 , 'Metalcore');
INSERT INTO ALBUM VALUES(album_index.nextval, 7 , 'Pop');
INSERT INTO ALBUM VALUES(album_index.nextval, 8 , 'Indie');
INSERT INTO ALBUM VALUES(album_index.nextval, 9 , 'Hip Hop');
INSERT INTO ALBUM VALUES(album_index.nextval, 10, 'Rap');


-------INSERT INTO PODCAST--------
INSERT INTO PODCAST VALUES(podcast_index.nextval, 11, 'Technology Trends', 'Exploring the latest advancements in technology and innovation.');
INSERT INTO PODCAST VALUES(podcast_index.nextval, 12, 'True Crime Stories', 'Dive into real-life crime cases and investigations.');
INSERT INTO PODCAST VALUES(podcast_index.nextval, 13, 'Health and Wellness', 'Tips and discussions on leading a healthy and balanced lifestyle.');
INSERT INTO PODCAST VALUES(podcast_index.nextval, 14, 'Business Insights', 'Insights and strategies for entrepreneurs and business professionals.');
INSERT INTO PODCAST VALUES(podcast_index.nextval, 15, 'Science Explorations', 'Discoveries and breakthroughs in the world of science.');
INSERT INTO PODCAST VALUES(podcast_index.nextval, 16, 'Personal Growth', 'Inspiring stories and practical advice for personal development.');
INSERT INTO PODCAST VALUES(podcast_index.nextval, 17, 'Comedy Hour', 'Laugh out loud with hilarious sketches and stand-up comedy.');
INSERT INTO PODCAST VALUES(podcast_index.nextval, 18, 'History Uncovered', 'Unraveling the mysteries of the past with historical analysis.');
INSERT INTO PODCAST VALUES(podcast_index.nextval, 19, 'Art and Culture', 'Exploring different forms of art, music, and cultural traditions.');
INSERT INTO PODCAST VALUES(podcast_index.nextval, 20, 'Sports Talk', 'Discussions on the latest sports news, events, and analysis.');


------INSERT INTO EPISODE----------
INSERT INTO EPISODE VALUES(episode_index.nextval, 1, 'Episode 1: The Future of Artificial Intelligence', 'John Doe', 'Jane Smith', 45, 0, 0);
INSERT INTO EPISODE VALUES(episode_index.nextval, 1, 'Episode 2: Space Exploration and Colonization','Michael Brown', 'Alice Johnson', 60, 0, 0);
INSERT INTO EPISODE VALUES(episode_index.nextval, 1, 'Episode 3: Quantum Computing Breakthroughs', 'Michael Brown', 'Emily Davis', 50, 0, 0);
INSERT INTO EPISODE VALUES(episode_index.nextval, 1, 'Episode 4: Augmented Reality and Virtual Reality Applications','Michael Brown', 'Samantha White', 55, 0, 0);
INSERT INTO EPISODE VALUES(episode_index.nextval, 1, 'Episode 5: Biotechnology and Genetic Engineering', 'David Wilson', 'Jessica Lee', 40, 0, 0);

INSERT INTO EPISODE VALUES (episode_index.nextval, 2,'The Case of the Missing Heirloom: A Forensic Analysis', 'Detective John Smith', 'Forensic Expert Sarah Johnson', 45, 0,0 );
INSERT INTO EPISODE VALUES (episode_index.nextval, 2,'Uncovering Corruption: Investigating Financial Crimes', 'Journalist Emma Thompson','Barak Obama', 60, 0,0 );

INSERT INTO EPISODE VALUES (episode_index.nextval, 3, 'Tips and discussions on leading a healthy and balanced lifestyle', 'Dr. Sarah Johnson', 'Wellness Coach Emily Smith', 40, 0, 0);

INSERT INTO EPISODE VALUES (episode_index.nextval, 4, 'Ep1: Key Principles for Startup Success', 'Entrepreneurial John Smith', 'Business Consultant Emily Bow', 60, 0, 0);
INSERT INTO EPISODE VALUES (episode_index.nextval, 4, 'Ep2: Navigating Market Trends and Competition', 'Business Analyst Michael John', 'Marketing Specialist Sarah Das', 45, 0 ,0);


INSERT INTO EPISODE VALUES (episode_index.nextval, 5, 'Recent Advances in Quantum Computing', 'Dr. Michael Adams', 'Dr. Rachel Chang', 50, 0, 0);
INSERT INTO EPISODE VALUES (episode_index.nextval, 5, 'Breakthroughs in Genetic Engineering', 'Professor James Carter', 'Dr. Lisa Rodriguez', 55, 0, 0);
INSERT INTO EPISODE VALUES (episode_index.nextval, 5, 'Exploring Space Exploration Technologies', 'Dr. Emily White', NULL, 45, 0, 0);

INSERT INTO EPISODE VALUES (episode_index.nextval, 6, 'Overcoming Adversity: Stories of Resilience', 'Rachel Adams', 'Life Coach David Roberts', 50, 0 ,0);
INSERT INTO EPISODE VALUES (episode_index.nextval, 6, 'Goal Setting and Self-Improvement Strategies', 'Lisa Miller', 'Psychologist Mark Wilson', 55, 0 ,0);

INSERT INTO EPISODE VALUES (episode_index.nextval, 7, 'Stand-Up Comedy Showcase: Comedy Gold', 'Comedian Mike Johnson', NULL, 45, 0, 0);
INSERT INTO EPISODE VALUES (episode_index.nextval, 7, 'Sketch Comedy Special: Laugh-a-minute Fun', 'Comedy Writer Sarah Thompson', NULL, 50, 0, 0);
INSERT INTO EPISODE VALUES (episode_index.nextval, 7, 'Comedy Roast: Hilarious Celebrity Impressions', 'Comedian John Smith', 'Impressionist Emily White', 55, 0, 0);

INSERT INTO EPISODE VALUES (episode_index.nextval, 8, 'The Secrets of Ancient Civilizations', 'Historian Dr. John Smith', NULL, 50, 0,0 );

INSERT INTO EPISODE VALUES (episode_index.nextval, 9, 'Episode1: Exploring Artistic Diversity', 'Art Historian Emma Johnson', 'Musicologist David Lee', 60, 0, 0);

INSERT INTO EPISODE VALUES (episode_index.nextval, 10, 'Football Fever: Analysis of the Latest Matches', 'Sports Analyst Michael Johnson', 'Football Expert Sarah Brown', 55, 0, 0);
INSERT INTO EPISODE VALUES (episode_index.nextval, 10, 'Basketball Buzz: NBA Playoffs Preview', 'Sports Commentator David Wilon', 'Basketball Analyst Emily White', 50, 0, 0);
INSERT INTO EPISODE VALUES (episode_index.nextval, 10, 'Sports Roundup: Highlights and Hot Topics', 'Sports Journalist Jessica Lee', NULL, 60, 0, 0);

-----INSERTS FOR SONG---------

INSERT INTO SONG VALUES (song_index.nextval, 1, 'Moonlight Sonata', 337, 50000);
INSERT INTO SONG VALUES (song_index.nextval, 1, 'Nocturne in E-Flat Major', 255, 45000);
INSERT INTO SONG VALUES (song_index.nextval, 1, 'Prelude in D-Flat Major', 230, 40000);
INSERT INTO SONG VALUES (song_index.nextval, 1, 'Adagio for Strings', 450, 60000);
INSERT INTO SONG VALUES (song_index.nextval, 1, 'Rhapsody in Blue', 560, 70000);

INSERT INTO SONG VALUES (song_index.nextval, 2, 'Electric Dreams', 282, 52000);
INSERT INTO SONG VALUES (song_index.nextval, 2, 'Dreamscape', 315, 51000);
INSERT INTO SONG VALUES (song_index.nextval, 2, 'Synthwave Symphony', 240, 48000);
INSERT INTO SONG VALUES (song_index.nextval, 2, 'Neon Nights', 278, 50000);
INSERT INTO SONG VALUES (song_index.nextval, 2, 'Digital Frontier', 295, 53000);

INSERT INTO SONG VALUES (song_index.nextval, 3, 'Eternal Flames', 324, 54000);
INSERT INTO SONG VALUES (song_index.nextval, 3, 'Inferno Symphony', 378, 58000);
INSERT INTO SONG VALUES (song_index.nextval, 3, 'Firestorm Rhapsody', 295, 53000);
INSERT INTO SONG VALUES (song_index.nextval, 3, 'Phoenix Melody', 310, 52000);
INSERT INTO SONG VALUES (song_index.nextval, 3, 'Flame Dance', 280, 51000);

INSERT INTO SONG VALUES (song_index.nextval, 4, 'Crystal Rain', 320, 52000);
INSERT INTO SONG VALUES (song_index.nextval, 4, 'Diamond Drops', 310, 51000);


INSERT INTO SONG VALUES (song_index.nextval, 5, 'Neon Lights', 315, 50000);
INSERT INTO SONG VALUES (song_index.nextval, 5, 'City Skylines', 290, 49000);
INSERT INTO SONG VALUES (song_index.nextval, 5, 'Midnight Drive', 280, 48000);
INSERT INTO SONG VALUES (song_index.nextval, 5, 'Electric Avenue', 305, 51000);
INSERT INTO SONG VALUES (song_index.nextval, 5, 'Synthwave Serenade', 320, 52000);
INSERT INTO SONG VALUES (song_index.nextval, 5, 'Neon Dreams', 330, 53000);
INSERT INTO SONG VALUES (song_index.nextval, 5, 'Digital Dystopia', 290, 47000);
INSERT INTO SONG VALUES (song_index.nextval, 5, 'City of Neon', 300, 49000);
INSERT INTO SONG VALUES (song_index.nextval, 5, 'Neon Skyline', 310, 50000);
INSERT INTO SONG VALUES (song_index.nextval, 5, 'Future Funk', 280, 46000);
INSERT INTO SONG VALUES (song_index.nextval, 5, 'Vaporwave Vibes', 315, 51000);
INSERT INTO SONG VALUES (song_index.nextval, 5, 'Synthpop Sensation', 325, 52000);

INSERT INTO SONG VALUES (song_index.nextval, 6, 'Starry Night', 280, 48000);
INSERT INTO SONG VALUES (song_index.nextval, 6, 'Celestial Symphony', 310, 50000);
INSERT INTO SONG VALUES (song_index.nextval, 6, 'Galactic Serenade', 290, 49000);
INSERT INTO SONG VALUES (song_index.nextval, 6, 'Nebula Waltz', 320, 52000);
INSERT INTO SONG VALUES (song_index.nextval, 6, 'Meteor Shower Melody', 300, 50000);
INSERT INTO SONG VALUES (song_index.nextval, 6, 'Cosmic Lullaby', 330, 53000);

INSERT INTO SONG VALUES (song_index.nextval, 7, 'Moonlit Sonata', 360, 54000);
INSERT INTO SONG VALUES (song_index.nextval, 7, 'Nocturnal Ballad', 340, 52000);
INSERT INTO SONG VALUES (song_index.nextval, 7, 'Midnight Melody', 370, 55000);
INSERT INTO SONG VALUES (song_index.nextval, 7, 'Lunar Serenade', 320, 50000);

INSERT INTO SONG VALUES (song_index.nextval, 8, 'Echoes of Dawn', 360, 54000);
INSERT INTO SONG VALUES (song_index.nextval, 8, 'Morning Mist', 340, 52000);
INSERT INTO SONG VALUES (song_index.nextval, 8, 'Sunrise Serenade', 330, 51000);
INSERT INTO SONG VALUES (song_index.nextval, 8, 'Dawn Chorus', 350, 53000);
INSERT INTO SONG VALUES (song_index.nextval, 8, 'First Light', 310, 50000);
INSERT INTO SONG VALUES (song_index.nextval, 8, 'Golden Dawn', 370, 55000);


INSERT INTO SONG VALUES (song_index.nextval, 9, 'Whispering Winds', 340, 53000);
INSERT INTO SONG VALUES (song_index.nextval, 9, 'Gentle Breeze', 320, 51000);
INSERT INTO SONG VALUES (song_index.nextval, 9, 'Soothing Zephyr', 330, 52000);
INSERT INTO SONG VALUES (song_index.nextval, 9, 'Murmuring Stream', 310, 50000);
INSERT INTO SONG VALUES (song_index.nextval, 9, 'Serenade of Silence', 350, 54000);
INSERT INTO SONG VALUES (song_index.nextval, 9, 'Tranquil Twilight', 360, 55000);
INSERT INTO SONG VALUES (song_index.nextval, 9, 'Echoes of Nature', 300, 49000);
INSERT INTO SONG VALUES (song_index.nextval, 9, 'Calm Waters', 310, 50000);

INSERT INTO SONG VALUES (song_index.nextval, 10, 'Rainbow Melodies', 330, 52000);
INSERT INTO SONG VALUES (song_index.nextval, 10, 'Colorful Harmony', 320, 51000);
INSERT INTO SONG VALUES (song_index.nextval, 10, 'Vibrant Serenade', 340, 53000);
INSERT INTO SONG VALUES (song_index.nextval, 10, 'Bright Symphony', 310, 50000);
INSERT INTO SONG VALUES (song_index.nextval, 10, 'Radiant Rhapsody', 350, 54000);
INSERT INTO SONG VALUES (song_index.nextval, 10, 'Prismatic Prelude', 320, 51000);


------INSERTS FOR LISTENER_LIKES_SONG------------
INSERT INTO LISTENER_LIKES_SONG VALUES(1, 28);
INSERT INTO LISTENER_LIKES_SONG VALUES(1, 37);
INSERT INTO LISTENER_LIKES_SONG VALUES(5, 53);
INSERT INTO LISTENER_LIKES_SONG VALUES(1, 40);
INSERT INTO LISTENER_LIKES_SONG VALUES(9, 2);
INSERT INTO LISTENER_LIKES_SONG VALUES(4, 21);
INSERT INTO LISTENER_LIKES_SONG VALUES(1, 6);
INSERT INTO LISTENER_LIKES_SONG VALUES(3, 37);
INSERT INTO LISTENER_LIKES_SONG VALUES(3, 40);
INSERT INTO LISTENER_LIKES_SONG VALUES(3, 49);
INSERT INTO LISTENER_LIKES_SONG VALUES(4, 42);
INSERT INTO LISTENER_LIKES_SONG VALUES(2, 20);
INSERT INTO LISTENER_LIKES_SONG VALUES(7, 19);
INSERT INTO LISTENER_LIKES_SONG VALUES(5, 58);
INSERT INTO LISTENER_LIKES_SONG VALUES(3, 58);
INSERT INTO LISTENER_LIKES_SONG VALUES(7, 28);
INSERT INTO LISTENER_LIKES_SONG VALUES(7, 31);
INSERT INTO LISTENER_LIKES_SONG VALUES(6, 38);
INSERT INTO LISTENER_LIKES_SONG VALUES(5, 18);
INSERT INTO LISTENER_LIKES_SONG VALUES(4, 26);
INSERT INTO LISTENER_LIKES_SONG VALUES(9, 13);
INSERT INTO LISTENER_LIKES_SONG VALUES(2, 1);
INSERT INTO LISTENER_LIKES_SONG VALUES(2, 7);
INSERT INTO LISTENER_LIKES_SONG VALUES(5, 30);
INSERT INTO LISTENER_LIKES_SONG VALUES(1, 5);
INSERT INTO LISTENER_LIKES_SONG VALUES(7, 52);
INSERT INTO LISTENER_LIKES_SONG VALUES(8, 20);
INSERT INTO LISTENER_LIKES_SONG VALUES(6, 59);
INSERT INTO LISTENER_LIKES_SONG VALUES(6, 4);
INSERT INTO LISTENER_LIKES_SONG VALUES(4, 47);
INSERT INTO LISTENER_LIKES_SONG VALUES(2, 19);
INSERT INTO LISTENER_LIKES_SONG VALUES(7, 9);
INSERT INTO LISTENER_LIKES_SONG VALUES(1, 32);
INSERT INTO LISTENER_LIKES_SONG VALUES(2, 31);
INSERT INTO LISTENER_LIKES_SONG VALUES(8, 47);
INSERT INTO LISTENER_LIKES_SONG VALUES(3, 2);
INSERT INTO LISTENER_LIKES_SONG VALUES(6, 25);
INSERT INTO LISTENER_LIKES_SONG VALUES(2, 34);
INSERT INTO LISTENER_LIKES_SONG VALUES(1, 38);
INSERT INTO LISTENER_LIKES_SONG VALUES(8, 59);
INSERT INTO LISTENER_LIKES_SONG VALUES(7, 33);
INSERT INTO LISTENER_LIKES_SONG VALUES(9, 3);
INSERT INTO LISTENER_LIKES_SONG VALUES(9, 9);
INSERT INTO LISTENER_LIKES_SONG VALUES(4, 22);
INSERT INTO LISTENER_LIKES_SONG VALUES(7, 45);
INSERT INTO LISTENER_LIKES_SONG VALUES(1, 59);
INSERT INTO LISTENER_LIKES_SONG VALUES(4, 28);
INSERT INTO LISTENER_LIKES_SONG VALUES(7, 48);
INSERT INTO LISTENER_LIKES_SONG VALUES(9, 15);


