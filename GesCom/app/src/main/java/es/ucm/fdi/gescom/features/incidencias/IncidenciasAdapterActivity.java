package es.ucm.fdi.gescom.features.incidencias;

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
import es.ucm.fdi.gescom.features.principal.IncidencesAdapter;

public class IncidenciasAdapterActivity  extends RecyclerView.Adapter<IncidenciasAdapterActivity.IncidenceViewHolder> {
    private final ArrayList<Incidencia> mIncidences;
    private final LayoutInflater mInflater;

    public IncidenciasAdapterActivity(Context context, ArrayList<Incidencia> users) {
        mInflater = LayoutInflater.from(context);
        mIncidences = users;
    }

    @Override
    public IncidenciasAdapterActivity.IncidenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View userView = mInflater.inflate(R.layout.component_incidencia_activity, parent, false);
        return new IncidenciasAdapterActivity.IncidenceViewHolder(userView, this);
    }

    @Override
    public void onBindViewHolder(IncidenciasAdapterActivity.IncidenceViewHolder holder, int position) {
        holder.titulo.setText(String.valueOf( mIncidences.get(holder.getAbsoluteAdapterPosition()).getAsunto()));
        holder.descripcion.setText(String.valueOf( mIncidences.get(holder.getAbsoluteAdapterPosition()).getDescripcion()));
        holder.date.setText(String.valueOf( mIncidences.get(holder.getAbsoluteAdapterPosition()).getDate()));
        holder.user.setText(String.valueOf( mIncidences.get(holder.getAbsoluteAdapterPosition()).getmUsuario()));
    }


    @Override
    public void onViewRecycled(@NonNull IncidenciasAdapterActivity.IncidenceViewHolder holder) {

    }

    @Override
    public int getItemCount() {
        return mIncidences.size();
    }


    public class IncidenceViewHolder extends RecyclerView.ViewHolder {
        public final TextView titulo, descripcion, date, user;
        final IncidenciasAdapterActivity mAdapter;

        public IncidenceViewHolder(View itemView, IncidenciasAdapterActivity usersAdapter) {
            super(itemView);
            mAdapter = usersAdapter;
            titulo = itemView.findViewById(R.id.component_incidence_principal_title);
            descripcion = itemView.findViewById(R.id.component_incidence_principal_body);
            date = itemView.findViewById(R.id.component_incidence_principal_date);
            user = itemView.findViewById(R.id.component_incidence_principal_user);
        }
    }
}

