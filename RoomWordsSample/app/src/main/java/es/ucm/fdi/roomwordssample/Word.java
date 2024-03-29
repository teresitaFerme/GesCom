package es.ucm.fdi.roomwordssample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")

    private String mWord;
    public Word(@NonNull String word) {this.mWord = word;}//the parameter can never be null
    public String getWord(){return this.mWord;}
}
