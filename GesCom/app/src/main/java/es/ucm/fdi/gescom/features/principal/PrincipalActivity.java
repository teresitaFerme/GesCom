package es.ucm.fdi.gescom.features.principal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.features.ajustes.AjustesActivity;
import es.ucm.fdi.gescom.features.avisos.AvisosActivity;
import es.ucm.fdi.gescom.features.incidencias.IncidenciasActivity;
import es.ucm.fdi.gescom.features.register_comunidad.initialize_users.InitializableUser;
import es.ucm.fdi.gescom.features.userdisplay.UserDisplayActivity;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.datacache.Incidencia;

public class PrincipalActivity extends BaseActivity implements PrincipalView{
    private Toolbar toolbar;
    private NavigationView mMenuNavigation;
    private ImageView mMenuIcon;
    private Menu mMenu;
    private RecyclerView mRecyclerIncidences;
    private ArrayList<Incidencia> mIncidencias = new ArrayList<>();
    private PrincipalPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pantalla_principal);
        getIntent();
        toolbar = this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mPresenter = new PrincipalPresenter(this);

        mMenuNavigation = findViewById(R.id.navigation_view);
        mMenuIcon = findViewById(R.id.imagen_menu);
        mMenuNavigation.setVisibility(View.GONE);
        mMenu = mMenuNavigation.getMenu();

        mRecyclerIncidences = findViewById(R.id.principal_incidences_recyclerView);
        mIncidencias = mPresenter.getIncidencias();
        if(mIncidencias.size() != 0){
            IncidencesAdapter incidencesAdapter = new IncidencesAdapter(this, mIncidencias);
            mRecyclerIncidences.setAdapter(incidencesAdapter);

            mRecyclerIncidences.setLayoutManager(new LinearLayoutManager(this));
        }


        //TODO que el menu no ocupe la mitad de la pantalla
        //TODO cambiar el tamaño de los items del menu

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
                //nextClass = ReservasActivity.class;
                break;
            case R.id.votaciones:
                //nextClass = VotacionesActivity.class;
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
        //esto está vacío para que si el usuario da hacia atrás, no se vaya a la pantalla de login
    }

    public void inflateMenu(View view) {
        if (mMenuNavigation.getVisibility() == View.GONE) {
            mMenuNavigation.setVisibility(View.VISIBLE);
            mMenuIcon.setImageResource(R.drawable.ic_menu_opened);
        } else {
            mMenuNavigation.setVisibility(View.GONE);
            mMenuIcon.setImageResource(R.drawable.ic_menu_closed);
        }
    }

    public void launchUser(View view) {
        Intent intent = new Intent(this, UserDisplayActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}