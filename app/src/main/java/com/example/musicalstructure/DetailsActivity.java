package com.example.musicalstructure;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int songId = extras.getInt("songId", -1);
            Song song = null;

            for (Song so : MainActivity.songs)
                if (so.getId() == songId) {
                    song = so;
                    break;
                }

            if (song != null) {
                TextView songNameTV = findViewById(R.id.song_name_tv);
                TextView albumNameTV = findViewById(R.id.album_name_tv);
                TextView artistNameTV = findViewById(R.id.artist_name_tv);
                TextView releaseDateTV = findViewById(R.id.release_date_tv);

                songNameTV.setText(song.getName());
                albumNameTV.setText(song.getAlbumName());
                artistNameTV.setText(song.getArtistName());
                releaseDateTV.setText(song.getReleaseDate().toString());
            }
        }
    }
}