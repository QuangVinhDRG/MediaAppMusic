package com.example.mediaappmusic;

public class Song {
    private String songName, artist;
    private int file, imageResource;

    public Song(String songName, String artist, int file, int imageResource) {
        this.songName = songName;
        this.artist = artist;
        this.file = file;
        this.imageResource = imageResource;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
