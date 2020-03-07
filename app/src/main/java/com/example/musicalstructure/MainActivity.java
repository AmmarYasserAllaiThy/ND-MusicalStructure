package com.example.musicalstructure;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    static final ArrayList<Song> songs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSongs();
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new MyAdapter(this, songs));
    }

    private void initSongs() {
        songs.add(new Song(0, "Someone Like You", "Adele", "21", new Date()));
        songs.add(new Song(1, "Rolling in the Deep", "Adele", "21", new Date()));
        songs.add(new Song(2, "Hello", "Adele", "21", new Date()));
        songs.add(new Song(3, "Freak Like Me", "Caroline Rose", "Superstar", new Date()));
        songs.add(new Song(4, "Do You Think We'll Last Forever?", "Caroline Rose", "Superstar", new Date()));
        songs.add(new Song(5, "Darling", "Real Estate", "In Mind", new Date()));
        songs.add(new Song(6, "Only For You", "Heartless Bastards", "Arrow", new Date()));
        songs.add(new Song(7, "The Woods", "Hollow Coves", "Wanderlust", new Date()));
        songs.add(new Song(8, "Keeping Me Alive (Acoustic)", "Jonathan Roy", "Keeping Me Alive (Acoustic)", new Date()));
        songs.add(new Song(9, "Carry You", "Ruelle", "featuring Fleurie", new Date()));
    }

    static class MyAdapter extends ArrayAdapter<Song> {
        Toast toast;

        public MyAdapter(@NonNull Context context, ArrayList<Song> songs) {
            super(context, 0, songs);
            toast = null;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View songItemView = convertView;
            if (convertView == null)
                songItemView = LayoutInflater.from(getContext()).inflate(R.layout.song_item, parent, false);

            final TextView songNameTV = songItemView.findViewById(R.id.song_name_tv);
            final TextView albumNameTV = songItemView.findViewById(R.id.album_name_tv);
            final ImageButton detailsIB = songItemView.findViewById(R.id.details_ib);
            final Song song = getItem(position);

            if (song != null) {
                songNameTV.setText(song.getName());
                albumNameTV.setText(song.getArtistName() + ", " + song.getAlbumName());
            }

            detailsIB.setOnClickListener(v ->
                    getContext().startActivity(new Intent(getContext(), DetailsActivity.class)
                            .putExtra("songId", song.getId())));

            songItemView.setOnClickListener(v ->
                    getContext().startActivity(new Intent(getContext(), PlayerActivity.class)
                            .putExtra("songId", song.getId())));

            return songItemView;
        }
    }
}