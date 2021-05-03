package es.ucm.fdi.gescom.features.votaciones;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.datacache.Votacion;


public class VotacionesAdapter extends RecyclerView.Adapter<VotacionesAdapter.VotacionViewHolder> {
    private final ArrayList<Votacion> mVotaciones;
    private final LayoutInflater mInflater;

    public VotacionesAdapter(Context context, ArrayList<Votacion> votaciones) {
        mInflater = LayoutInflater.from(context);
        mVotaciones = votaciones;
    }

    @Override
    public VotacionesAdapter.VotacionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View votacionView = mInflater.inflate(R.layout.component_votacion, parent, false);
        return new VotacionesAdapter.VotacionViewHolder(votacionView, this);
    }

    @Override
    public void onBindViewHolder(VotacionesAdapter.VotacionViewHolder holder, int position) {
        holder.titulo.setText(String.valueOf( mVotaciones.get(holder.getAbsoluteAdapterPosition()).getTitle()));
        holder.description.setText(String.valueOf( mVotaciones.get(holder.getAbsoluteAdapterPosition()).getDescription()));
        holder.mVotosContra.setText(String.valueOf( mVotaciones.get(holder.getAbsoluteAdapterPosition()).getVotosContra()));
        holder.mVotosFavor.setText(String.valueOf( mVotaciones.get(holder.getAbsoluteAdapterPosition()).getVotosFavor()));

        if(GesComApp.getUser().getLocalizer().equals("Administrador")){
            holder.mCerrarVotacion.setVisibility(View.VISIBLE);
            holder.mEnviarVoto.setVisibility(View.GONE);
        }else{
            holder.mCerrarVotacion.setVisibility(View.GONE);
            holder.mEnviarVoto.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onViewRecycled(@NonNull VotacionesAdapter.VotacionViewHolder holder) {

    }

    @Override
    public int getItemCount() {
        return mVotaciones.size();
    }


    public class VotacionViewHolder extends RecyclerView.ViewHolder {
        final VotacionesAdapter mAdapter;
        public final Button mEnviarVoto, mCerrarVotacion;
        public final ImageButton mFavor, mContra;
        public final TextView mVotosFavor, mVotosContra, titulo, description;

        public VotacionViewHolder(View itemView, VotacionesAdapter adapter) {
            super(itemView);
            mAdapter = adapter;
            mEnviarVoto = itemView.findViewById(R.id.votacion_button);
            mFavor = itemView.findViewById(R.id.votacion_button_favor);
            mContra = itemView.findViewById(R.id.votacion_button_contra);
            mVotosFavor = itemView.findViewById(R.id.votacion_votos_a_favor);
            mVotosContra = itemView.findViewById(R.id.votacion_votos_en_contra);
            titulo = itemView.findViewById(R.id.votacion_title);
            description = itemView.findViewById(R.id.votacion_description);
            mCerrarVotacion = itemView.findViewById(R.id.votacion_cerrar_admin_button);
        }
    }
}

