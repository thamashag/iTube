package com.example.itube;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

public class VideoPlayerActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        webView = findViewById(R.id.webView);

        // Enable JavaScript for the WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Load HTML content with YouTube IFrame Player API code
        String htmlContent = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "  <body>\n" +
                "    <!-- The <iframe> (and video player) will replace this <div> tag. -->\n" +
                "    <div id=\"player\"></div>\n" +
                "\n" +
                "    <script>\n" +
                "      var player;\n" +
                "      function onYouTubeIframeAPIReady() {\n" +
                "        player = new YT.Player('player', {\n" +
                "          height: '100%',\n" +
                "          width: '100%',\n" +
                "          videoId: '" + extractVideoId(getIntent().getStringExtra("VIDEO_URL")) + "',\n" +
                "          playerVars: {\n" +
                "            'playsinline': 1\n" +
                "          },\n" +
                "          events: {\n" +
                "            'onReady': onPlayerReady,\n" +
                "            'onStateChange': onPlayerStateChange\n" +
                "          }\n" +
                "        });\n" +
                "      }\n" +
                "\n" +
                "      function onPlayerReady(event) {\n" +
                "        event.target.playVideo();\n" +
                "      }\n" +
                "\n" +
                "      function onPlayerStateChange(event) {\n" +
                "        // Handle player state change events\n" +
                "      }\n" +
                "    </script>\n" +
                "\n" +
                "    <script src=\"https://www.youtube.com/iframe_api\"></script>\n" +
                "  </body>\n" +
                "</html>";

        webView.loadData(htmlContent, "text/html", "UTF-8");
    }

    // Method to extract video ID from YouTube URL
    private String extractVideoId(String videoUrl) {
        String videoId = "";

        // Extract video ID from YouTube URL
        if (videoUrl != null && videoUrl.trim().length() > 0) {
            String[] split = videoUrl.split("v=");
            if (split.length > 1) {
                videoId = split[1];
                int endIndex = videoId.indexOf("&");
                if (endIndex != -1) {
                    videoId = videoId.substring(0, endIndex);
                }
            }
        }

        return videoId;
    }
}





