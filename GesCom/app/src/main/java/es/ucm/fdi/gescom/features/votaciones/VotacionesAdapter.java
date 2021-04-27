package es.ucm.fdi.gescom.features.votaciones;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.datacache.Votacion;


public class VotacionesAdapter extends RecyclerView.Adapter<VotacionesAdapter.VotacionViewHolder> {
    private final ArrayList<Votacion> mVotaciones;
    private final LayoutInflater mInflater;

    public VotacionesAdapter(Context context, ArrayList<Votacion> users) {
        mInflater = LayoutInflater.from(context);
        mVotaciones = users;
    }

    @Override
    public VotacionesAdapter.VotacionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View votacionView = mInflater.inflate(R.layout.component_votacion, parent, false);
        return new VotacionesAdapter.VotacionViewHolder(votacionView, this);
    }

    @Override
    public void onBindViewHolder(VotacionesAdapter.VotacionViewHolder holder, int position) {
        //holder.title.setText(String.valueOf( mIncidences.get(holder.getAbsoluteAdapterPosition()).getAsunto()));
        //holder.description.setText(String.valueOf( mIncidences.get(holder.getAbsoluteAdapterPosition()).getDescripcion()));
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
        public final Button mEnviarVoto;
        public final ImageButton mFavor, mContra;

        public VotacionViewHolder(View itemView, VotacionesAdapter adapter) {
            super(itemView);
            mAdapter = adapter;
            mEnviarVoto = itemView.findViewById(R.id.votacion_button);
            mFavor = itemView.findViewById(R.id.votacion_button_favor);
            mContra = itemView.findViewById(R.id.votacion_button_contra);
        }
    }
}

