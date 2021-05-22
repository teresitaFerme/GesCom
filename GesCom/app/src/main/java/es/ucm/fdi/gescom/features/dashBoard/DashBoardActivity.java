package es.ucm.fdi.gescom.features.dashBoard;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.datacache.Aviso;
import es.ucm.fdi.gescom.datacache.Incidencia;
import es.ucm.fdi.gescom.datacache.Votacion;
import es.ucm.fdi.gescom.features.ajustes.AjustesActivity;
import es.ucm.fdi.gescom.features.avisos.AvisosActivity;
import es.ucm.fdi.gescom.features.incidencias.IncidenciasActivity;
import es.ucm.fdi.gescom.features.incidencias.reportar_incidencia.ReportarIncidenciaActivity;
import es.ucm.fdi.gescom.features.user_management.UserManagementActivity;
import es.ucm.fdi.gescom.features.userdisplay.UserDisplayActivity;
import es.ucm.fdi.gescom.features.votaciones.VotacionesActivity;

public class DashBoardActivity extends BaseActivity implements DashBoardView {
    private Toolbar toolbar;
    private NavigationView mMenuNavigation;
    private ImageView mMenuIcon;
    private Menu mMenu;
    private RecyclerView mRecyclerIncidences, mRecyclerVotaciones, mRecyclerAvisos, mRecyclerReservas;
    private ArrayList<Incidencia> mIncidencias = new ArrayList<>();
    private ArrayList<Aviso> mAvisos = new ArrayList<>();
    private ArrayList<Votacion> mVotes = new ArrayList<>();
    private DashBoardPresenter mPresenter;
    private TextView mNoIncidences, mNoVotes, mViewAllIncidences, mViewAllVotes, mViewAllAvisos;
    private CardView mCardViewIncidences, mVotaciones, mReservas, mAvisosCardview, mIncidenciasTitle, mAvisosTitle, mVotacionesTitle, mReservasTitle;
    private ImageButton mShowReservas, mShowIncidencias, mShowAvisos, mShowVotaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pantalla_principal);
        getIntent();
        bindViews();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mPresenter = new DashBoardPresenter(this);


        mMenuNavigation.setVisibility(View.GONE);
        mMenu = mMenuNavigation.getMenu();

        mViewAllIncidences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAllIncidences();
            }
        });
        mViewAllVotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAllVotes();
            }
        });

        mPresenter.checkAdmin();
        drawVotes();

        drawAvisos();

        mVotacionesTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mVotaciones.getVisibility() == View.GONE){
                    mVotaciones.setVisibility(View.VISIBLE);
                    mShowVotaciones.setBackground(getDrawable(R.drawable.ic_desplegado));
                }else{
                    mVotaciones.setVisibility(View.GONE);
                    mShowVotaciones.setBackground(getDrawable(R.drawable.ic_no_desplegado));
                }
            }
        });

        mIncidenciasTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCardViewIncidences.getVisibility() == View.GONE){
                    mCardViewIncidences.setVisibility(View.VISIBLE);
                    mShowIncidencias.setBackground(getDrawable(R.drawable.ic_desplegado));
                }else{
                    mCardViewIncidences.setVisibility(View.GONE);
                    mShowIncidencias.setBackground(getDrawable(R.drawable.ic_no_desplegado));
                }
            }
        });

        mReservasTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mReservas.getVisibility() == View.GONE){
                    mReservas.setVisibility(View.VISIBLE);
                    mShowReservas.setBackground(getDrawable(R.drawable.ic_desplegado));
                }else{
                    mReservas.setVisibility(View.GONE);
                    mShowReservas.setBackground(getDrawable(R.drawable.ic_no_desplegado));
                }
            }
        });


        //TODO que el menu no ocupe la mitad de la pantalla
        //TODO cambiar el tamaño de los items del menu
        //TODO que aquellos apartados que tengan cosas en la lista salgan desplegados
    }

    private void launchAllVotes() {
        Intent intent = new Intent(this, VotacionesActivity.class);
        startActivity(intent);
    }

    @Override
    public void bindViews() {
        toolbar = this.findViewById(R.id.toolbar);
        mMenuNavigation = findViewById(R.id.navigation_view);
        mMenuIcon = findViewById(R.id.imagen_menu);
        mRecyclerIncidences = findViewById(R.id.principal_incidences_recyclerView);
        mNoIncidences = findViewById(R.id.principal_incidences_none);
        mViewAllIncidences = findViewById(R.id.principal_view_all_incidences);
        mShowIncidencias = findViewById(R.id.principal_incidencias_no_desplegadas);
        mShowReservas = findViewById(R.id.principal_reservas_no_desplegadas);
        mShowVotaciones = findViewById(R.id.principal_votaciones_no_desplegadas);
        mCardViewIncidences = findViewById(R.id.cardView_incidencias);
        mReservas = findViewById(R.id.cardView_reservas);
        mAvisosCardview = findViewById(R.id.cardView_avisos);
        mRecyclerAvisos = findViewById(R.id.principal_avisos_recyclerView);
        mVotaciones = findViewById(R.id.cardView_votaciones);
        mIncidenciasTitle = findViewById(R.id.cardView_incidencias_title);
        mVotacionesTitle = findViewById(R.id.cardView_votaciones_title);
        mReservasTitle = findViewById(R.id.cardView_reservas_title);
        mAvisosTitle = findViewById(R.id.cardView_avisos_title);
        mViewAllVotes = findViewById(R.id.principal_view_all_votaciones);
        mNoVotes = findViewById(R.id.principal_votaciones_none);
    }

    //TODO hay que hacer override de onResume o onRestart para que actualice las incidencias

    public void launchAllIncidences(){
        Intent intent = new Intent(this, IncidenciasActivity.class);
        startActivity(intent);
    }

    public void launchAllAvisos(){
        Intent intent = new Intent(this, AvisosActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mMenuNavigation.getVisibility() == View.VISIBLE) {
            mMenuNavigation.setVisibility(View.GONE);
            mMenuIcon.setImageResource(R.drawable.ic_menu_closed);
        }
    }

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
                nextClass = VotacionesActivity.class;
                break;
            case R.id.ajustes:
                nextClass = AjustesActivity.class;
                break;
            case R.id.gestionar_usuarios:
                nextClass = UserManagementActivity.class;
                break;
        }
        Intent intent = new Intent(this, nextClass);
        startActivity(intent);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (mMenuNavigation.getVisibility() == View.VISIBLE) {
            mMenuNavigation.setVisibility(View.GONE);
            mMenuIcon.setImageResource(R.drawable.ic_menu_closed);
        }
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

    @Override
    public void drawIncidences() {
        mIncidencias = mPresenter.getIncidencias();
        if(mIncidencias.size() != 0){
            mCardViewIncidences.setVisibility(View.VISIBLE);
            mShowIncidencias.setBackground(getDrawable(R.drawable.ic_desplegado));
            mNoIncidences.setVisibility(View.GONE);
            IncidencesAdapter incidencesAdapter = new IncidencesAdapter(this, mIncidencias);
            mRecyclerIncidences.setAdapter(incidencesAdapter);

            mRecyclerIncidences.setLayoutManager(new LinearLayoutManager(this));
        }
        else mNoIncidences.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideIncidences() {
        findViewById(R.id.cardView_incidencias_title).setVisibility(View.GONE);
        findViewById(R.id.cardView_incidencias).setVisibility(View.GONE);
    }

    @Override
    public void drawVotes() {
        /*mVotes = mPresenter.getVotes();
        if(mVotes.size() != 0){
            mVotaciones.setVisibility(View.VISIBLE);
            mShowVotaciones.setBackground(getDrawable(R.drawable.ic_desplegado));
            mNoVotes.setVisibility(View.GONE);
            VotacionesAdapter votacionesAdapter = new VotacionesAdapter(this, mVotes);
            mRecyclerVotaciones.setAdapter(votacionesAdapter);

            mRecyclerVotaciones.setLayoutManager(new LinearLayoutManager(this));
        }
        else mNoVotes.setVisibility(View.VISIBLE);*/
    }

    @Override
    public void hideVotes() {

    }

    @Override
    public void drawAvisos() {
        mAvisos = mPresenter.getAvisos();
        if(mAvisos.size() != 0){
            findViewById(R.id.principal_avisos_none).setVisibility(View.GONE);
            AvisosAdapterDashBoard avisosAdapter = new AvisosAdapterDashBoard(this, mAvisos);
            mRecyclerAvisos.setAdapter(avisosAdapter);
            mRecyclerAvisos.setLayoutManager(new LinearLayoutManager(this));
            findViewById(R.id.principal_view_all_avisos).setVisibility(View.VISIBLE);
            findViewById(R.id.principal_view_all_avisos).setOnClickListener(view -> launchAllAvisos());
        }
        else {
            findViewById(R.id.principal_avisos_none).setVisibility(View.VISIBLE);
            findViewById(R.id.principal_view_all_avisos).setVisibility(View.GONE);
        }
    }

    @Override
    public void drawReservas() {

    }

    @Override
    public void hideReservas() {

    }

    @Override
    public void hideUserShortcuts() {
        findViewById(R.id.principal_user_shortcuts_container).setVisibility(View.GONE);
    }

    @Override
    public void bindUserShortcuts() {
        findViewById(R.id.principal_new_incidence_shortcut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.launchAddIncidence();
            }
        });

        //TODO añadir el listener al shortcut de hacer reserva
    }

    @Override
    public void launchAddIncidence() {
        Intent intent = new Intent(this, ReportarIncidenciaActivity.class);
        startActivity(intent);
    }
}