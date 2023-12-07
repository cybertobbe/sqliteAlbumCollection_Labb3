CREATE TABLE genre(
    genreId INTEGER PRIMARY KEY,
    genreName TEXT NOT NULL
);

CREATE TABLE artist(
    artistId INTEGER PRIMARY KEY,
    artistName TEXT NOT NULL,
    artistFounded INT NOT NULL,
    artistGenreId INT NOT NULL
);

INSERT INTO genre(genreName)
VALUES ('Pop'), ('Rock'), ('Jazz'), ('Blues');

INSERT INTO artist(artistName, artistFounded, artistGenreId)
VALUES('ABBA', 1972, 1), ('Kiss', 1973, 2), ('Louis Armstrong', 1919, 3), ('Ella Fitzgerald', 1934, 3), ('BB King', 1942, 4);



SELECT * FROM genre;
SELECT * FROM artist;

-- Inner join
SELECT artist.artistName, genre.genreName FROM artist INNER JOIN genre ON artist.artistGenreId = genre.genreId;

DROP TABLE genre;
DROP TABLE artist;