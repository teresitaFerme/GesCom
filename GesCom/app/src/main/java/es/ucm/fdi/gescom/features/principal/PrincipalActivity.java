package es.ucm.fdi.gescom.features.principal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.features.ajustes.AjustesActivity;
import es.ucm.fdi.gescom.features.avisos.AvisosActivity;
import es.ucm.fdi.gescom.features.incidencias.IncidenciasActivity;
import es.ucm.fdi.gescom.features.userdisplay.UserDisplayActivity;
import es.ucm.fdi.gescom.roomdatabase.GesComApp;
import es.ucm.fdi.gescom.roomdatabase.Incidencia;
import es.ucm.fdi.gescom.roomdatabase.Usuario;


//NO SE PORQUE NO FUNCIONA LO DEL TOOLBAR
public class PrincipalActivity extends AppCompatActivity {
    private final String edServidor = "sql11.freemysqlhosting.net";
    private final String edPuerto = "3306";
    private final String edUsuario = "sql11401998";
    private final String edPassword = "Y5hMbgyCqi";
    private final String baseDatos = "sql11401998";


    private Toolbar toolbar;
    private NavigationView mMenuNavigation;
    private ImageView mMenuIcon;
    private Menu mMenu;
    private TextView mIncidenciaAsunto;
    private TextView mIncidenciaDescripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pantalla_principal);
        getIntent();
        toolbar = this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        mMenuNavigation = findViewById(R.id.navigation_view);
        mMenuIcon = findViewById(R.id.imagen_menu);
        mMenuNavigation.setVisibility(View.GONE);
        mMenu = mMenuNavigation.getMenu();

        mIncidenciaAsunto = findViewById(R.id.incidencias_asunto_principañ);
        mIncidenciaDescripcion = findViewById(R.id.incidencias_descripcion_pricipal);

        Incidencia inci = GesComApp.getIncidencia();
        if(inci != null){
            mIncidenciaAsunto.setText(inci.getAsunto());
            mIncidenciaDescripcion.setText(inci.getDescripcion());
        }

        //TODO que el menu no ocupe la mitad de la pantalla
        //TODO cambiar el tamaño de los items del menu

        conectarMySQL();
    }

    //TODO hay que hacer override de onResume o onRestart para que actualice las incidencias

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Class nextClass = null;
        switch (id){
            case R.id.avisos:
                nextClass = AvisosActivity.class;
                break;
            case R.id.incidencias:
                nextClass = IncidenciasActivity.class;
                break;
            case R.id.reservas:
                //nextClass = AvisosActivity.class;
                break;
            case R.id.votaciones:
                //nextClass = AvisosActivity.class;
                break;
            case R.id.ajustes:
                nextClass = AjustesActivity.class;
                break;
        }
        Intent intent = new Intent(this, nextClass);
        startActivity(intent);

        return true;
    }

    @Override
    public void onBackPressed() {
        //TODO when the user clicks back the menu must disappear if inflated, if not close application
        super.onBackPressed();
    }

    public void inflateMenu(View view) {
        if (mMenuNavigation.getVisibility() == View.GONE) {
            mMenuNavigation.setVisibility(View.VISIBLE);
            mMenuIcon.setImageResource(R.drawable.ic_menu_opened);
        } else {
            mMenuNavigation.setVisibility(View.GONE);
            mMenuIcon.setImageResource(R.drawable.ic_menu_closed);
        }
        //mMenuIcon.setImageResource(R.drawable.ic_menu_opened);
    }

    public void launchUser(View view) {
        Intent intent = new Intent(this, UserDisplayActivity.class);
        startActivity(intent);
    }

    public void conectarMySQL() {

      // new DataBaseConnection().execute();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {

        super.onSaveInstanceState(outState, outPersistentState);
    }
}