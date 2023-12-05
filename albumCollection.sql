CREATE TABLE genre(
    genreId INTEGER PRIMARY KEY,
    genreName TEXT
);

CREATE TABLE artist(
    artistId INTEGER PRIMARY KEY,
    artistName TEXT,
    artistFounded INT,
    artistGenreId INT
);

INSERT INTO genre(genreName) VALUES ('Pop');

INSERT INTO artist(artistName, artistFounded, artistGenreId)
VALUES('ABBA', 1972, 1);

SELECT * FROM genre;
SELECT * FROM artist;

DROP TABLE genre;
DROP TABLE artist;
