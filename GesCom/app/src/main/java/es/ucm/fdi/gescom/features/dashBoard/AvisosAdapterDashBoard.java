package es.ucm.fdi.gescom.features.dashBoard;

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

public class AvisosAdapterDashBoard  extends RecyclerView.Adapter<AvisosAdapterDashBoard.AvisoViewHolder> {
    private final ArrayList<Aviso> mAvisos;
    private final LayoutInflater mInflater;

    public AvisosAdapterDashBoard(Context context, ArrayList<Aviso> avisos) {
        mInflater = LayoutInflater.from(context);
        mAvisos = avisos;
    }

    @Override
    public AvisoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View userView = mInflater.inflate(R.layout.component_aviso_dashboard, parent, false);
        return new AvisoViewHolder(userView, this);
    }

    @Override
    public void onBindViewHolder(AvisoViewHolder holder, int position) {
        holder.titulo.setText(String.valueOf( mAvisos.get(holder.getAbsoluteAdapterPosition()).getAsunto()));
        holder.descripcion.setText(String.valueOf( mAvisos.get(holder.getAbsoluteAdapterPosition()).getDescripcion()));
        holder.date.setText(String.valueOf( mAvisos.get(holder.getAbsoluteAdapterPosition()).getDate()));
    }


    @Override
    public void onViewRecycled(@NonNull AvisoViewHolder holder) {

    }

    @Override
    public int getItemCount() {
        return mAvisos.size();
    }

    public class AvisoViewHolder extends RecyclerView.ViewHolder {
        public final TextView titulo, descripcion, date;
        final AvisosAdapterDashBoard mAdapter;

        public AvisoViewHolder(View itemView, AvisosAdapterDashBoard avisosAdapter) {
            super(itemView);
            mAdapter = avisosAdapter;
            titulo = itemView.findViewById(R.id.component_aviso_principal_title);
            descripcion = itemView.findViewById(R.id.component_aviso_principal_body);
            date = itemView.findViewById(R.id.component_aviso_principal_date);
        }
    }
}

