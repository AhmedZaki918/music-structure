package com.example.android.musicapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/*
 * {@link MusicAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source, which is a list of {@link Data} objects.
 * */
public class MusicAdapter extends ArrayAdapter<Data> {


    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context    The current context. Used to inflate the layout file.
     * @param sourceData A List of Data objects to display in a list
     */
    public MusicAdapter(Activity context, ArrayList<Data> sourceData) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for multi views, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, sourceData);
    }


    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }


        // Get the {@link Data} object located at this position in the list
        Data currentSong = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID ArtistName
        TextView artistName = listItemView.findViewById(R.id.ArtistName);
        // Get the Artist Name from the current Data object and
        // set this text on the name TextView
        artistName.setText(currentSong.getArtistName());

        // Find the TextView in the list_item.xml layout with the ID SongName
        TextView songName = listItemView.findViewById(R.id.SongName);
        // Get the Song Name from the current Data object and
        // set this text on the name TextView
        songName.setText(currentSong.getSongName());

        // Find the TextView in the list_item.xml layout with the ID Album
        TextView albumDate = listItemView.findViewById(R.id.Album);
        // Get the Album from the current Data object and
        // set this text on the name TextView
        albumDate.setText(currentSong.getAlbumDate());

        // Find the ImageView in the list_item.xml layout with the ID ImageView
        ImageView image = listItemView.findViewById(R.id.ImageView);
        // Get the Image View from the current Data object and
        // set the image to iconView
        image.setImageResource(currentSong.getImageResourceId());

        // Find the ImageView in the list_item.xml layout with the ID MusicIcon_ImageView
        ImageView playIcon = listItemView.findViewById(R.id.MusicIcon_ImageView);
        // Get the MusicIcon ImageView from the current Data object and
        // set the image to iconView
        playIcon.setImageResource(currentSong.getMusicIcon());


        // Return the whole list item layout (containing 6 views)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
