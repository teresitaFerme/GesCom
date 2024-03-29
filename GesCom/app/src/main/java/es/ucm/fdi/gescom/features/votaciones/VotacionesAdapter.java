package es.ucm.fdi.gescom.features.votaciones;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.datacache.Votacion;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabase;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabaseHelper;


public class VotacionesAdapter extends RecyclerView.Adapter<VotacionesAdapter.VotacionViewHolder> {
    private final ArrayList<Votacion> mVotaciones;
    private final LayoutInflater mInflater;
    private final VotacionesView mVotacionesView;

    public VotacionesAdapter(Context context, ArrayList<Votacion> votaciones, VotacionesView votacionesView) {
        mInflater = LayoutInflater.from(context);
        mVotaciones = votaciones;
        mVotacionesView = votacionesView;
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
            holder.mEnviarVoto.setVisibility(View.GONE);
            if(mVotaciones.get(holder.getAbsoluteAdapterPosition()).getOpened()){
                holder.mCerrarVotacion.setVisibility(View.VISIBLE);
                holder.mCerrarVotacion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                        builder1.setMessage("¿Desea cerrar la votación " + holder.titulo.getText() + "?");
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "Cerrar votación",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        CommunitiesDatabaseHelper mCommunitiesDBHelper  = new CommunitiesDatabaseHelper(holder.mCerrarVotacion.getContext());
                                        SQLiteDatabase db = mCommunitiesDBHelper.getWritableDatabase();

                                        ContentValues values = new ContentValues();
                                        values.put(CommunitiesDatabase.Votes.COLUMN_NAME_OPENED, 0);

                                        String selection = CommunitiesDatabase.Votes._ID + " = ?";
                                        String[] selectionArgs = {mVotaciones.get(holder.getAbsoluteAdapterPosition()).getId()};

                                        db.update(
                                                CommunitiesDatabase.Votes.TABLE_NAME,
                                                values,
                                                selection,
                                                selectionArgs
                                        );
                                        //TODO hacer que se actualice en la vista que la votacion esta cerrada
                                        dialog.cancel();
                                        mVotacionesView.populateRecyclers();
                                    }
                                });

                        builder1.setNegativeButton(
                                "Cancelar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }
                });
            } else{
                holder.mCerrarVotacion.setVisibility(View.GONE);
            }

        }else{
            holder.mCerrarVotacion.setVisibility(View.GONE);
            if(mVotaciones.get(position).getOpened()){
                holder.mEnviarVoto.setVisibility(View.VISIBLE);
                CommunitiesDatabaseHelper mCommunitiesDBHelper  = new CommunitiesDatabaseHelper(holder.mEnviarVoto.getContext());
                SQLiteDatabase db = mCommunitiesDBHelper.getWritableDatabase();

                String[] projection = {
                        BaseColumns._ID,
                        CommunitiesDatabase.VotesRegister.COLUMN_NAME_USER
                };

                String selection = CommunitiesDatabase.VotesRegister.COLUMN_NAME_VOTE + " = ?";
                String[] selectionArgs = {mVotaciones.get(position).getId()};
                Cursor cursor = db.query(
                        CommunitiesDatabase.VotesRegister.TABLE_NAME,   // The table to query
                        projection,             // The array of columns to return (pass null to get all)
                        selection,              // The columns for the WHERE clause
                        selectionArgs,          // The values for the WHERE clause
                        null,                   // don't group the rows
                        null,                   // don't filter by row groups
                        null               // The sort order
                );

                boolean hasVoted = false;
                for (cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()) {
                    if(cursor.getString(1).equals(String.valueOf(GesComApp.getUser().getId()))){
                        hasVoted = true;
                    }
                }

                if(!hasVoted){
                    holder.mEnviarVoto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                            builder1.setMessage("¿Cuál será su voto para esta votación?");
                            builder1.setCancelable(true);

                            builder1.setPositiveButton(
                                    "A favor",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {


                                            ContentValues values = new ContentValues();
                                            values.put(CommunitiesDatabase.Votes.COLUMN_NAME_VOTOS_FAVOR, String.valueOf(mVotaciones.get(holder.getAbsoluteAdapterPosition()).getVotosFavor() + 1));

                                            String selection = CommunitiesDatabase.Votes._ID + " = ?";
                                            String[] selectionArgs = {mVotaciones.get(holder.getAbsoluteAdapterPosition()).getId()};

                                            db.update(
                                                    CommunitiesDatabase.Votes.TABLE_NAME,
                                                    values,
                                                    selection,
                                                    selectionArgs
                                            );
                                            //TODO hacer que se actualice en la vista que la votacion esta cerrada
                                            dialog.cancel();
                                            mVotacionesView.populateRecyclers();

                                            values = new ContentValues();

                                            values.put(CommunitiesDatabase.VotesRegister.COLUMN_NAME_USER, GesComApp.getUser().getId());
                                            values.put(CommunitiesDatabase.VotesRegister.COLUMN_NAME_VOTE, mVotaciones.get(position).getId());

                                            db.insert(CommunitiesDatabase.VotesRegister.TABLE_NAME, null, values);

                                        }
                                    });

                            builder1.setNegativeButton(
                                    "En contra",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            CommunitiesDatabaseHelper mCommunitiesDBHelper  = new CommunitiesDatabaseHelper(holder.mEnviarVoto.getContext());
                                            SQLiteDatabase db = mCommunitiesDBHelper.getWritableDatabase();

                                            ContentValues values = new ContentValues();
                                            values.put(CommunitiesDatabase.Votes.COLUMN_NAME_VOTOS_CONTRA, String.valueOf(mVotaciones.get(holder.getAbsoluteAdapterPosition()).getVotosContra() + 1));

                                            String selection = CommunitiesDatabase.Votes._ID + " = ?";
                                            String[] selectionArgs = {mVotaciones.get(holder.getAbsoluteAdapterPosition()).getId()};

                                            db.update(
                                                    CommunitiesDatabase.Votes.TABLE_NAME,
                                                    values,
                                                    selection,
                                                    selectionArgs
                                            );
                                            //TODO hacer que se actualice en la vista que la votacion esta cerrada
                                            //TODO falta registrar la votacion para esconder el boton cuando ya hayan votado
                                            dialog.cancel();
                                            mVotacionesView.populateRecyclers();

                                            values = new ContentValues();

                                            values.put(CommunitiesDatabase.VotesRegister.COLUMN_NAME_USER, GesComApp.getUser().getId());
                                            values.put(CommunitiesDatabase.VotesRegister.COLUMN_NAME_VOTE, mVotaciones.get(position).getId());

                                            db.insert(CommunitiesDatabase.VotesRegister.TABLE_NAME, null, values);

                                        }
                                    });

                            AlertDialog alert11 = builder1.create();
                            alert11.show();
                        }
                    });
                } else holder.mEnviarVoto.setVisibility(View.GONE); ;

            } else{
                holder.mEnviarVoto.setVisibility(View.GONE);
            }
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
        public final ImageView mFavor, mContra;
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

