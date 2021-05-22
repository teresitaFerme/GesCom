package es.ucm.fdi.gescom.features.user_management.new_user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import es.ucm.fdi.gescom.base.BasePresenter;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabase;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabaseHelper;

public class AddUserPresenter extends BasePresenter {
    private AddUserView mView;
    private final CommunitiesDatabaseHelper mCommunitiesDBHelper;


    AddUserPresenter(AddUserView view){
        mView = view;
        mCommunitiesDBHelper = new CommunitiesDatabaseHelper((Context) view);
    }

    public void validate(String dni, String localizador) {
        if (dni.length() != 0 && localizador.length() != 0) {
            if(esDni(dni)){
                SQLiteDatabase db = mCommunitiesDBHelper.getReadableDatabase();

                String[] projection = {
                        BaseColumns._ID,
                        CommunitiesDatabase.User.COLUMN_NAME_USERNAME
                };

                String selection = CommunitiesDatabase.User.COLUMN_NAME_DNI + " = ?";
                String[] selectionArgs = {dni};

                Cursor cursor = db.query(
                        CommunitiesDatabase.User.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null
                );

                if (!cursor.moveToFirst()) {
                    ContentValues values = new ContentValues();
                    values.put(CommunitiesDatabase.User.COLUMN_NAME_COMMUNITY, GesComApp.getComunidad().getName());
                    values.put(CommunitiesDatabase.User.COLUMN_NAME_USERNAME, dni);
                    values.put(CommunitiesDatabase.User.COLUMN_NAME_PASSWORD, GesComApp.getComunidad().getKey());
                    values.put(CommunitiesDatabase.User.COLUMN_NAME_DNI, dni);
                    values.put(CommunitiesDatabase.User.COLUMN_NAME_LOCALIZER, localizador);

                    db.insert(CommunitiesDatabase.User.TABLE_NAME, null, values);
                    mView.initSuccessful();
                } else mView.initFailure("Ese dni ya está registrado.");//TODO borrar aqui el campo usuario
            }else mView.initFailure("Introduce un DNI válido.");
        }else mView.initFailure("Por favor rellene todos los campos");
    }

    public static boolean contieneSoloLetras(String cadena) {
        for (int x = 0; x < cadena.length(); x++) {
            char c = cadena.charAt(x);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_' || (c >= '0' && c <='9'))) {
                return false;
            }
        }
        return true;
    }

    private static boolean esDni(String cadena){
        if(cadena.length() != 9){
            return false;
        }else{
            for (int x = 0; x < cadena.length() - 1; x++) {
                char c = cadena.charAt(x);
                if (x < 8 && !(c >= '0' && c <='9')) {
                    return false;
                }
            }
            char c = cadena.charAt(8);
            if(!(c >= 'A' && c <= 'Z')){
                return false;
            }
            return true;
        }
    }


}

