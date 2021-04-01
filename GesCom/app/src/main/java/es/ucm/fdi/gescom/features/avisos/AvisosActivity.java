package es.ucm.fdi.gescom.features.avisos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.ucm.fdi.gescom.R;

import static androidx.recyclerview.widget.RecyclerView.*;

public class AvisosActivity extends AppCompatActivity {
    private RecyclerView mRecyclerAvisos;
    private FloatingActionButton mNuevoAviso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avisos);

        mRecyclerAvisos = findViewById(R.id.recycler_avisos);
        mRecyclerAvisos.setAdapter(new Adapter() {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.aviso_card_view, parent, false);

                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 7;
            }
        });


        //TODO si es admin que pueda añadir nuevos avisos
        //TODO que los avisos puedan filtrarse por dias, semanas o meses
        //TODO poner bien el recycler view y que salgan todos los card views
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView avisosCardView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            avisosCardView = (CardView) view.findViewById(R.id.aviso_cardView);
        }

        public CardView getCardView() {
            return avisosCardView;
        }
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}