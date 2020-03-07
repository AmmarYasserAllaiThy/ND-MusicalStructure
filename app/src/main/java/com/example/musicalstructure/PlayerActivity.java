package com.example.musicalstructure;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerActivity extends AppCompatActivity {

    private static int songId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            songId = extras.getInt("songId", -1);

            if (songId != -1) {
                TextView songNameTV = findViewById(R.id.song_name_tv);
                ImageButton loopIB = findViewById(R.id.loop_ib);
                ImageButton prevIB = findViewById(R.id.prev_ib);
                ImageButton pauseIB = findViewById(R.id.pause_ib);
                ImageButton nextIB = findViewById(R.id.next_ib);
                ImageButton likeIB = findViewById(R.id.like_ib);

                Song song = getSong(songId);
                final boolean[] isPaused = {false};
                final boolean[] isLiked = {false};
                final Toast[] toast = new Toast[1];

                if (song != null) songNameTV.setText(song.getName());

                loopIB.setOnClickListener(v -> {
                    if (toast[0] != null) toast[0].cancel();
                    toast[0] = Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT);
                    toast[0].show();
                });

                prevIB.setOnClickListener(v -> {
                    Song prevSong = getPrevSong();
                    if (prevSong != null) songNameTV.setText(prevSong.getName());
                    else {
                        if (toast[0] != null) toast[0].cancel();
                        toast[0] = Toast.makeText(getApplicationContext(), "No Previous Songs", Toast.LENGTH_SHORT);
                        toast[0].show();
                    }
                });

                pauseIB.setOnClickListener(v -> pauseIB.setImageResource((isPaused[0] = !isPaused[0]) ?
                        R.drawable.ic_play_circle_filled_48dp : R.drawable.ic_pause_circle_filled_48dp));

                nextIB.setOnClickListener(v -> {
                    Song nextSong = getNextSong();
                    if (nextSong != null) songNameTV.setText(nextSong.getName());
                    else {
                        if (toast[0] != null) toast[0].cancel();
                        toast[0] = Toast.makeText(getApplicationContext(), "No Next Songs", Toast.LENGTH_SHORT);
                        toast[0].show();
                    }
                });

                likeIB.setOnClickListener(v -> likeIB.setImageResource((isLiked[0] = !isLiked[0]) ?
                        R.drawable.ic_favorite_48dp : R.drawable.ic_favorite_border_48dp));
            }
        }
    }

    private Song getSong(int id) {
        for (Song song : MainActivity.songs) if (song.getId() == id) return song;
        return null;
    }

    private Song getNextSong() {
        if (++songId < MainActivity.songs.size()) return getSong(songId);
        songId--;
        return null;
    }

    private Song getPrevSong() {
        if (--songId > -1) return getSong(songId);
        songId++;
        return null;
    }
}