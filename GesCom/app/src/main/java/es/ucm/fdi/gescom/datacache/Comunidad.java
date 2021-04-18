package es.ucm.fdi.gescom.datacache;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class Comunidad {

    private long id;
    private String name;
    private long id_admin;
    private String key;

    public Comunidad(long id,  String name, long id_admin, String key) {
        this.id = id;
        this.name = name;
        this.id_admin = id_admin;
        this.key = key;
    }//the parameter can never be null

    public Comunidad getComunidad(){return this;}

    public long getId(){return this.id;}

    public String getName(){return this.name;}

    public String getKey(){return this.key;}
}
