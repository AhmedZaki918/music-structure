package com.example.android.musicapp;


import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;


/**
 * {@link LibraryActivity} shows a list of the songs.
 * For each song, display the name of the song, name of the artist, photo of the artist, resource audio ID and etc.
 */
public class LibraryActivity extends AppCompatActivity {

    //Initialize the variables
    private MediaPlayer mMediaPlayer;
    private ImageView mPause_Play;
    private SeekBar seekbar;
    private Runnable runnable;
    private Handler handler;

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            //Make their visibility to gone after media player is completed.
            mPause_Play.setVisibility(View.GONE);
            seekbar.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        mPause_Play = findViewById(R.id.Pause_ImageButton);
        seekbar = findViewById(R.id.SeekBar);
        handler = new Handler();

        // Create an ArrayList of Data objects
        final ArrayList<Data> dataSource = new ArrayList<Data>();
        dataSource.add(new Data("Ana Magnoona", "Elissa", "2014", R.raw.ana_magnoona, R.drawable.elissa, R.drawable.baseline_library_music_black_24));
        dataSource.add(new Data("Ana Mosh Anani", "Amr Diab", "2014", R.raw.ana_mosh_anani, R.drawable.amr_diab, R.drawable.baseline_library_music_black_24));
        dataSource.add(new Data("Kalam Kalam", "Karmin Solayman", "2014", R.raw.kalam_kalam, R.drawable.carmen_soilman, R.drawable.baseline_library_music_black_24));
        dataSource.add(new Data("Mawa2ef Mo2lema", "Assala", "2015", R.raw.mawa2ef_mo2lema, R.drawable.assala, R.drawable.baseline_library_music_black_24));
        dataSource.add(new Data("Nerga3 Tani", "Tamer Hosny", "2014", R.raw.nerga3_tani, R.drawable.tamer_hosny, R.drawable.baseline_library_music_black_24));
        dataSource.add(new Data("Rahnt 3alik", "Nansy Ajram", "2014", R.raw.rahnt3alik, R.drawable.nancy, R.drawable.baseline_library_music_black_24));
        dataSource.add(new Data("Tell me", "Inna", "2015", R.raw.tell_me, R.drawable.inna, R.drawable.baseline_library_music_black_24));
        dataSource.add(new Data("Inta mny", "Yara", "2008", R.raw.inta_mny, R.drawable.anta_mny, R.drawable.baseline_library_music_black_24 ));


        // Create an {@link MusicAdaptor}, whose data source is a list of
        // {@link Data}s. The adapter knows how to create list item views for each item
        // in the list.
        MusicAdapter adapter = new MusicAdapter(this, dataSource);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = findViewById(R.id.ListView);
        listView.setAdapter(adapter);

        //Set on click listener on each Item in the ListView instead of create multi click listener for each item.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Get the {@link Data} object at the given position the user clicked on
                Data d = dataSource.get(position);


                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current song.
                mMediaPlayer = MediaPlayer.create(LibraryActivity.this, d.getAudioResourceId());

                //Get the duration of MediaPlayer and pass it to seekbar
                seekbar.setMax(mMediaPlayer.getDuration());
                // Start the audio file
                mMediaPlayer.start();

                //Convert the pause icon to play icon once the user clicked the icon
                mPause_Play.setBackgroundResource(R.drawable.baseline_pause_black_24);
                //Displays the pause icon once the song is clicked
                mPause_Play.setVisibility(View.VISIBLE);
                //Displays the seekbar once the song is clicked
                seekbar.setVisibility(View.VISIBLE);

                //Change the progress of seekbar once the song is clicked
                changeSeekBar();

                //Setup a listener on the media player
                mMediaPlayer.setOnCompletionListener(mCompletionListener);

            }
        });

        //Start the media player, stop it and switch the icons
        mPause_Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check the status of media player is running or not before the user click on icon
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                    mPause_Play.setBackgroundResource(R.drawable.baseline_play_arrow_black_24);

                } else {
                    mMediaPlayer.start();
                    mPause_Play.setBackgroundResource(R.drawable.baseline_pause_black_24);
                    //Change the progress of seekbar once the song is clicked
                    changeSeekBar();
                }
            }
        });

        //Change the progress of seekbar when the user click on it
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                if (b) {
                    mMediaPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    //This method change the seekbar
    private void changeSeekBar() {
        seekbar.setProgress(mMediaPlayer.getCurrentPosition());

        if (mMediaPlayer.isPlaying()) {

            runnable = new Runnable() {
                @Override
                public void run() {
                    changeSeekBar();
                }
            };

            handler.postDelayed(runnable, 1000);
        }
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

        }
    }
}