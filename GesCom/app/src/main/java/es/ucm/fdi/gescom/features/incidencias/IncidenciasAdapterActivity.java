package es.ucm.fdi.gescom.features.incidencias;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.datacache.Incidencia;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabase;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabaseHelper;

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
        holder.user.setText(String.valueOf( mIncidences.get(holder.getAbsoluteAdapterPosition()).getUsername()));
        if(GesComApp.getUser().getLocalizer().equals("Administrador")){
            if(mIncidences.get(holder.getAbsoluteAdapterPosition()).getSeen()){
                holder.mNotSeen.setVisibility(View.GONE);
                holder.mSeen.setVisibility(View.VISIBLE);
            }else{
                holder.mSeen.setVisibility(View.GONE);
                holder.mNotSeen.setVisibility(View.VISIBLE);
            }
        }else{
            holder.mSeen.setVisibility(View.GONE);
            holder.mNotSeen.setVisibility(View.GONE);
        }

        holder.mSeen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIncidences.get(holder.getAbsoluteAdapterPosition()).setSeen(false);
                holder.mSeen.setVisibility(View.GONE);
                holder.mNotSeen.setVisibility(View.VISIBLE);
                CommunitiesDatabaseHelper mCommunitiesDBHelper = new CommunitiesDatabaseHelper(v.getContext());

                SQLiteDatabase db = mCommunitiesDBHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(CommunitiesDatabase.Incidences.COLUMN_NAME_SEEN, "0");

                String selection = CommunitiesDatabase.Incidences._ID + " = ?";
                String[] selectionArgs = {String.valueOf(mIncidences.get(holder.getAbsoluteAdapterPosition()).get_id())};

                db.update(
                        CommunitiesDatabase.Incidences.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs
                );
            }
        });

        holder.mNotSeen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIncidences.get(holder.getAbsoluteAdapterPosition()).setSeen(true);
                holder.mNotSeen.setVisibility(View.GONE);
                holder.mSeen.setVisibility(View.VISIBLE);
                CommunitiesDatabaseHelper mCommunitiesDBHelper = new CommunitiesDatabaseHelper(v.getContext());

                SQLiteDatabase db = mCommunitiesDBHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(CommunitiesDatabase.Incidences.COLUMN_NAME_SEEN, "1");

                String selection = CommunitiesDatabase.Incidences._ID + " = ?";
                String[] selectionArgs = {String.valueOf(mIncidences.get(holder.getAbsoluteAdapterPosition()).get_id())};

                db.update(
                        CommunitiesDatabase.Incidences.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs
                );
            }
        });

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
        public final ImageButton mSeen, mNotSeen;

        public IncidenceViewHolder(View itemView, IncidenciasAdapterActivity usersAdapter) {
            super(itemView);
            mAdapter = usersAdapter;
            titulo = itemView.findViewById(R.id.component_incidence_principal_title);
            descripcion = itemView.findViewById(R.id.component_incidence_principal_body);
            date = itemView.findViewById(R.id.component_incidence_principal_date);
            user = itemView.findViewById(R.id.component_incidence_principal_user);
            mSeen = itemView.findViewById(R.id.incidencia_seen);
            mNotSeen = itemView.findViewById(R.id.incidencia_not_seen);


            mSeen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSeen.setVisibility(View.GONE);
                    mNotSeen.setVisibility(View.VISIBLE);
                }
            });

            mNotSeen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mNotSeen.setVisibility(View.GONE);
                    mSeen.setVisibility(View.VISIBLE);
                }
            });

        }
    }
}

