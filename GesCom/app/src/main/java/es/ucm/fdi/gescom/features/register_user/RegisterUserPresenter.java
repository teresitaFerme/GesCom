package es.ucm.fdi.gescom.features.register_user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import es.ucm.fdi.gescom.base.BasePresenter;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.datacache.User;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabase;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabaseHelper;

public class RegisterUserPresenter extends BasePresenter {
    private RegisterUserView mView;
    private final CommunitiesDatabaseHelper mCommunitiesDBHelper;


    RegisterUserPresenter(RegisterUserView view){
        mView = view;
        mCommunitiesDBHelper = new CommunitiesDatabaseHelper((Context) view);
    }

    public void validate(String dni, String clave) {
        if(dni.length() != 0 && clave.length() != 0){
            //TODO guardar todos los datos ne cache, incluida la comunidad entera

            SQLiteDatabase db = mCommunitiesDBHelper.getReadableDatabase();
            String[] projection = {
                    BaseColumns._ID,
                    CommunitiesDatabase.User.COLUMN_NAME_USERNAME,
                    CommunitiesDatabase.User.COLUMN_NAME_PASSWORD,
                    CommunitiesDatabase.User.COLUMN_NAME_LOCALIZER
            };
            String selection = CommunitiesDatabase.User.COLUMN_NAME_USERNAME + " = ?";
            String[] selectionArgs = {dni};
            Cursor cursor = db.query(
                    CommunitiesDatabase.User.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            );

            if(cursor.moveToFirst()){
                if(cursor.getString(cursor.getColumnIndex(CommunitiesDatabase.User.COLUMN_NAME_PASSWORD)).equals(clave)){
                    GesComApp.getApp().setUser(dni, clave, cursor.getString(cursor.getColumnIndex(CommunitiesDatabase.User.COLUMN_NAME_LOCALIZER)), cursor.getInt(0));
                }
                else  mView.wrongData("Dni o clave incorrectos");
            } else  mView.wrongData("Dni o clave incorrectos");
        }else{
            mView.wrongData("Por favor rellene todos los campos");
        }
    }

    public void validate(String username, String pass, String passRepeat, String dni) {
        //TODO meter validacion del dni en la funcion validate de arriba y en el initialize users
        if (username.length() != 0 && pass.length() != 0 && passRepeat.length() != 0) {
            if(esDni(dni)){
                if(contieneSoloLetras(username)){
                    if(pass.equals(passRepeat)){
                        if(pass.length() >= 8){

                            SQLiteDatabase db = mCommunitiesDBHelper.getReadableDatabase();

                            String[] projection = {
                                    BaseColumns._ID,
                                    CommunitiesDatabase.User.COLUMN_NAME_USERNAME
                            };

                            String selection = CommunitiesDatabase.User.COLUMN_NAME_USERNAME + " = ?";
                            String[] selectionArgs = {username};

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
                                values.put(CommunitiesDatabase.User.COLUMN_NAME_USERNAME, username);
                                values.put(CommunitiesDatabase.User.COLUMN_NAME_PASSWORD, pass);

                                selectionArgs = new String[]{dni};

                                db.update(
                                        CommunitiesDatabase.User.TABLE_NAME,
                                        values,
                                        selection,
                                        selectionArgs
                                );

                                projection = new String[]{
                                        BaseColumns._ID,
                                        CommunitiesDatabase.User.COLUMN_NAME_LOCALIZER
                                };
                                selectionArgs = new String[]{username};
                                GesComApp.setUser(new User(username, pass, db.query(CommunitiesDatabase.User.TABLE_NAME, projection, selection, selectionArgs, null, null, null).getString(1), db.query(CommunitiesDatabase.User.TABLE_NAME, projection, selection, selectionArgs, null, null, null).getInt(0)));

                                mView.initSuccessful();
                            } else mView.initFailure("Ese usuario ya está cogido. Introduzca otro usuario.");//TODO borrar aqui el campo usuario
                        }else  mView.initFailure("La contraseña debe tener 8 carácteres como mínimo.");
                    }else mView.initFailure("Las contraseñas no coinciden");//TODO hacer que se borren los campos de las contraseñas
                } else mView.initFailure("El usuario solo puede contener letras, números y barra baja.");
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
