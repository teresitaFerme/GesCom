package es.ucm.fdi.gescom.features.avisos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.datacache.Aviso;

public class AvisosAdapter extends RecyclerView.Adapter<AvisosAdapter.AvisoViewHolder> {
    private final ArrayList<Aviso> mAvisos;
    private final LayoutInflater mInflater;

    public AvisosAdapter(Context context, ArrayList<Aviso> avisos) {
        mInflater = LayoutInflater.from(context);
        mAvisos = avisos;
    }

    @Override
    public AvisosAdapter.AvisoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View userView = mInflater.inflate(R.layout.component_aviso_activity, parent, false);
        return new AvisosAdapter.AvisoViewHolder(userView, this);
    }

    @Override
    public void onBindViewHolder(AvisosAdapter.AvisoViewHolder holder, int position) {
        holder.titulo.setText(String.valueOf( mAvisos.get(holder.getAbsoluteAdapterPosition()).getAsunto()));
        holder.descripcion.setText(String.valueOf( mAvisos.get(holder.getAbsoluteAdapterPosition()).getDescripcion()));
    }


    @Override
    public void onViewRecycled(@NonNull AvisosAdapter.AvisoViewHolder holder) {

    }

    @Override
    public int getItemCount() {
        return mAvisos.size();
    }


    public class AvisoViewHolder extends RecyclerView.ViewHolder {
        public final TextView titulo, descripcion;
        final AvisosAdapter mAdapter;

        public AvisoViewHolder(View itemView, AvisosAdapter avisosAdapter) {
            super(itemView);
            mAdapter = avisosAdapter;
            titulo = itemView.findViewById(R.id.component_incidence_principal_title);
            descripcion = itemView.findViewById(R.id.component_incidence_principal_body);
        }
    }
}

