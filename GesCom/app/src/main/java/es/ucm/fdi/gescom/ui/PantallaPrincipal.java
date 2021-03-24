package es.ucm.fdi.gescom.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

import es.ucm.fdi.gescom.R;


//NO SE PORQUE NO FUNCIONA LO DEL TOOLBAR
public class PantallaPrincipal extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView mMenuNavigation;
    private ImageView mMenuIcon;
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

        //TODO que el menu no ocupe la mitad de la pantalla
        //TODO cambiar el tamaño de los items del menu
    }

    @Override
    public void onBackPressed() {
        //TODO when the user clicks back the menu must disappear if inflated, if not close application
        super.onBackPressed();
    }

    public void inflateMenu(View view) {

        if(mMenuNavigation.getVisibility() == View.GONE){
            mMenuNavigation.setVisibility(View.VISIBLE);
            mMenuIcon.setImageResource(R.drawable.ic_menu_opened);
        }
        else{
            mMenuNavigation.setVisibility(View.GONE);
            mMenuIcon.setImageResource(R.drawable.ic_menu_closed);
        }
        //mMenuIcon.setImageResource(R.drawable.ic_menu_opened);
    }

    public void launchUser(View view) {
        Intent intent = new Intent(this, UserDisplay.class);
        startActivity(intent);
    }




}