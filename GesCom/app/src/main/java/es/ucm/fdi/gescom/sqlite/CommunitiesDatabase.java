package es.ucm.fdi.gescom.sqlite;

import android.provider.BaseColumns;

public final class CommunitiesDatabase {
    private CommunitiesDatabase() {}

    public static class User implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_COMMUNITY = "community";
    }

    public static class Communities implements BaseColumns {
        public static final String TABLE_NAME = "communities";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_ID_ADMIN = "id_admin";
    }
}
