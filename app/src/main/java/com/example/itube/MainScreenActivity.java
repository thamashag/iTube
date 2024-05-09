package com.example.itube;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainScreenActivity extends AppCompatActivity {
    private EditText editTextYouTubeUrl;
    private Button buttonPlay, buttonAddToPlaylist, buttonMyPlaylist;
    private PlaylistDbHelper dbHelper; // Database helper instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        // Initialize UI elements
        editTextYouTubeUrl = findViewById(R.id.editTextYouTubeUrl);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonAddToPlaylist = findViewById(R.id.buttonAddToPlaylist);
        buttonMyPlaylist = findViewById(R.id.buttonMyPlaylist);

        // Initialize database helper
        dbHelper = new PlaylistDbHelper(this);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoUrl = editTextYouTubeUrl.getText().toString().trim();
                // Pass video URL to VideoPlayerActivity
                Intent intent = new Intent(MainScreenActivity.this, VideoPlayerActivity.class);
                intent.putExtra("VIDEO_URL", videoUrl);
                startActivity(intent);
            }
        });

        buttonAddToPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add the YouTube URL to the user's playlist
                String videoUrl = editTextYouTubeUrl.getText().toString().trim();
                // Check if the URL is not empty
                if (!videoUrl.isEmpty()) {
                    // Add the URL to the playlist
                    dbHelper.addToPlaylist(1, videoUrl); // Assuming user ID is 1
                } else {
                    // Show a message indicating that the URL is empty
                    // You can display this message in a Toast or Snackbar
                    // For example:
                    // Toast.makeText(MainScreenActivity.this, "Enter a YouTube URL", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonMyPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the list of URLs from the database
                List<String> playlistUrls = dbHelper.getPlaylist(); // Assuming user ID is 1

                // Pass the list of URLs to the activity/dialog to display
                Intent intent = new Intent(MainScreenActivity.this, PlaylistActivity.class);
                intent.putStringArrayListExtra("PLAYLIST_URLS", (ArrayList<String>) playlistUrls);
                startActivity(intent);
            }
        });
    }
}


