package com.example.musicalstructure;

import java.util.Date;

public class Song {

    private int id;
    private String name;
    private String artistName;
    private String albumName;
    private Date releaseDate;

    public Song(int id, String name, String artistName, String albumName, Date releaseDate) {
        this.id = id;
        this.name = name;
        this.artistName = artistName;
        this.albumName = albumName;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artistName='" + artistName + '\'' +
                ", albumName='" + albumName + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}