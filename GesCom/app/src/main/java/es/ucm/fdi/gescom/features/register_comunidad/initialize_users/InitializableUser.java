package es.ucm.fdi.gescom.features.register_comunidad.initialize_users;

import java.util.ArrayList;

public class InitializableUser {
    private int position;
    private String username;
    private String localizer;

    InitializableUser(int pos){
        position = pos;
    }

    public int getPosition(){return position;}

    public static ArrayList<InitializableUser> createContactsList(int numContacts) {
        ArrayList<InitializableUser> users = new ArrayList<InitializableUser>();

        for (int i = 1; i <= numContacts; i++) {
            users.add(new InitializableUser(i));
        }

        return users;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLocalizer(String localizer) {
        this.localizer = localizer;
    }
}
