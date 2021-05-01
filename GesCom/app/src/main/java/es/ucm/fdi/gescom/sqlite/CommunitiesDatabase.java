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
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_SEEN = "seen";
    }

    public static class Votes implements BaseColumns{
        public static final String TABLE_NAME = "votes";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_COMMUNITY_ID = "id_community";
        public static final String COLUMN_NAME_VOTOS_FAVOR = "votos_favor";
        public static final String COLUMN_NAME_VOTOS_CONTRA = "votos_contra";
        public static final String COLUMN_NAME_OPENED = "opened";
    }

    public static class VotesRegister implements BaseColumns{
        public static final String TABLE_NAME = "votes_register";
        public static final String COLUMN_NAME_VOTE = "votacion";
        public static final String COLUMN_NAME_USER = "usuario";
    }

    public static class Avisos implements BaseColumns{
        public static final String TABLE_NAME = "avisos";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_BODY = "body";
        public static final String COLUMN_NAME_COMMUNITY_ID = "id_community";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_HOUR = "hour";
    }

}
