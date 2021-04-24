package es.ucm.fdi.gescom.sqlite;

import android.provider.BaseColumns;

public final class CommunitiesDatabase {
    private CommunitiesDatabase() {}

    public static class User implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_COMMUNITY = "community";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_DNI = "dni";
        public static final String COLUMN_NAME_LOCALIZER = "localizador";
    }

    public static class Communities implements BaseColumns {
        public static final String TABLE_NAME = "communities";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_ID_ADMIN = "id_admin";
        public static final String COLUMN_NAME_KEY = "clave";
    }

    public static class Incidences implements BaseColumns{
        public static final String TABLE_NAME = "incidences";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_BODY = "body";
        public static final String COLUMN_NAME_COMMUNITY_ID = "id_community";
        public static final String COLUMN_NAME_USER = "id_user";
    }
}
