package es.ucm.fdi.gescom.features.avisos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.datacache.Aviso;

public class AvisosAdapter extends RecyclerView.Adapter<AvisosAdapter.AvisoViewHolder> {
    private final ArrayList<Aviso> mAvisos;
    private final LayoutInflater mInflater;
    private final AvisosView mView;

    public AvisosAdapter(Context context, ArrayList<Aviso> avisos, AvisosView view) {
        mInflater = LayoutInflater.from(context);
        mAvisos = avisos;
        mView = view;
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
        holder.date.setText(String.valueOf( mAvisos.get(holder.getAbsoluteAdapterPosition()).getDate()));
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modifyAviso(position, false, true);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modifyAviso(position, true, false);
            }
        });
    }

    private void modifyAviso(int position, boolean delete, boolean edit){
        mView.modifyAviso(position, delete, edit);
    }


    @Override
    public void onViewRecycled(@NonNull AvisosAdapter.AvisoViewHolder holder) {

    }

    @Override
    public int getItemCount() {
        return mAvisos.size();
    }


    public class AvisoViewHolder extends RecyclerView.ViewHolder {
        private final TextView titulo, descripcion, date;
        final AvisosAdapter mAdapter;
        private ImageView edit, delete;

        public AvisoViewHolder(View itemView, AvisosAdapter avisosAdapter) {
            super(itemView);
            mAdapter = avisosAdapter;
            titulo = itemView.findViewById(R.id.component_aviso_principal_title);
            descripcion = itemView.findViewById(R.id.component_aviso_principal_body);
            date = itemView.findViewById(R.id.component_aviso_principal_date);
            edit = itemView.findViewById(R.id.aviso_edit);
            delete = itemView.findViewById(R.id.aviso_delete);
        }
    }
}

