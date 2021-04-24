package es.ucm.fdi.gescom.features.principal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.datacache.Incidencia;

public class IncidencesAdapter extends RecyclerView.Adapter<IncidencesAdapter.IncidenceViewHolder> {
    private final ArrayList<Incidencia> mIncidences;
    private final LayoutInflater mInflater;

    public IncidencesAdapter(Context context, ArrayList<Incidencia> users) {
        mInflater = LayoutInflater.from(context);
        mIncidences = users;
    }

    @Override
    public IncidencesAdapter.IncidenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View userView = mInflater.inflate(R.layout.component_incidence_principal, parent, false);
        return new IncidenceViewHolder(userView, this);
    }

    @Override
    public void onBindViewHolder(IncidenceViewHolder holder, int position) {
        holder.titulo.setText(String.valueOf( mIncidences.get(holder.getAbsoluteAdapterPosition()).getAsunto()));
        holder.descripcion.setText(String.valueOf( mIncidences.get(holder.getAbsoluteAdapterPosition()).getDescripcion()));
    }


    @Override
    public void onViewRecycled(@NonNull IncidenceViewHolder holder) {

    }

    @Override
    public int getItemCount() {
        return mIncidences.size();
    }


    public class IncidenceViewHolder extends RecyclerView.ViewHolder {
        public final TextView titulo, descripcion;
        final IncidencesAdapter mAdapter;

        public IncidenceViewHolder(View itemView, IncidencesAdapter usersAdapter) {
            super(itemView);
            mAdapter = usersAdapter;
            titulo = itemView.findViewById(R.id.component_incidence_principal_title);
            descripcion = itemView.findViewById(R.id.component_incidence_principal_body);
        }
    }
}
