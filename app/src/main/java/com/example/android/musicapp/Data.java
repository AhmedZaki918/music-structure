package com.example.android.musicapp;

/**
 * {@link Data} represents a attributes of a song (e.g. Song name, Artist name, ID for the song).
 * Each object has 6 properties: song name, artist name, album date, audio resource ID, image resource ID and music icon.
 */

public class Data {

    //Song name
    private String mSongName;
    //Artist name
    private String mArtistName;
    //the release version date
    private String mAlbumDate;
    //Audio resource ID for the song
    private int mAudioResourceId;
    //Image resource ID for the song
    private int mImageResourceId;
    //Music icon for each row in the ListView
    private int mMusicIcon;

    /*
     * Create a new Data object.
     *
     * @param songName is the name of the song
     * @param artistName is the name of the artist
     * @param albumDate is the release version date
     * @param auidoResourceId is the song
     * @param imageResourceId is image of the artist
     * @param musicIcon is the music icon for each song
     */
    public Data(String songName, String artistName, String albumDate, int audioResourceId, int imageResourceId, int musicIcon) {
        mSongName = songName;
        mArtistName = artistName;
        mAlbumDate = albumDate;
        mAudioResourceId = audioResourceId;
        mImageResourceId = imageResourceId;
        mMusicIcon = musicIcon;
    }

    //Get the name of the song
    public String getSongName() {
        return mSongName;
    }

    //Get the name of the artist
    public String getArtistName() {
        return mArtistName;
    }

    //Get the release version date
    public String getAlbumDate() {
        return mAlbumDate;
    }

    //Get the audio resource id
    public int getAudioResourceId() {
        return mAudioResourceId;
    }

    //Get image resource id
    public int getImageResourceId() {
        return mImageResourceId;
    }

    //Get music icon resource id
    public int getMusicIcon() {
        return mMusicIcon;
    }
}