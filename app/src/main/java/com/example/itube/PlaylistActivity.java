package com.example.itube;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class PlaylistActivity extends AppCompatActivity {
    private ListView listViewPlaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        // Initialize UI elements
        listViewPlaylist = findViewById(R.id.listViewPlaylist);

        // Retrieve the list of URLs from the intent
        List<String> playlistUrls = getIntent().getStringArrayListExtra("PLAYLIST_URLS");

        // Create an ArrayAdapter to display the list of URLs in the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, playlistUrls);
        listViewPlaylist.setAdapter(adapter);
    }
}

