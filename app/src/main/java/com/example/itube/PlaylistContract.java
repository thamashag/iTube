package com.example.itube;

import android.provider.BaseColumns;

public class PlaylistContract {
    public static class PlaylistEntry implements BaseColumns {
        public static final String TABLE_NAME = "playlist";
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_VIDEO_TITLE = "video_title";
        public static final String COLUMN_VIDEO_URL = "video_url";
    }
}

