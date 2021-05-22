package es.ucm.fdi.gescom.features.user_management;

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
import es.ucm.fdi.gescom.datacache.User;

public class UsersAdapter  extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {
    private final ArrayList<User> mUsers;
    private final LayoutInflater mInflater;
    private UserManagementView mView;

    public UsersAdapter(Context context, ArrayList<User> users, UserManagementView view) {
        mInflater = LayoutInflater.from(context);
        mUsers = users;
        this.mView = view;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View userView = mInflater.inflate(R.layout.component_user_management, parent, false);
        return new UserViewHolder(userView, this);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        String username = mUsers.get(position).getUserName();
        holder.username.setText(username);

        String dni = "DNI: " + mUsers.get(position).getmDNI();
        holder.dni.setText(dni);

        String localizer = "Localizador: " + mUsers.get(position).getLocalizer();
        holder.localizer.setText(localizer);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchView(position, true, false);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchView(position, false, true);
            }
        });

    }

    private void launchView(int position, boolean edit, boolean delete){
        mView.onClick(position, edit, delete);
    }

    @Override
    public void onViewRecycled(@NonNull UserViewHolder holder) {

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {
        final UsersAdapter mAdapter;
        private TextView username, dni, localizer;
        private ImageView edit, delete;

        public UserViewHolder(View itemView, UsersAdapter usersAdapter) {
            super(itemView);
            mAdapter = usersAdapter;
            username = itemView.findViewById(R.id.component_user_name);
            dni = itemView.findViewById(R.id.component_user_dni);
            localizer = itemView.findViewById(R.id.component_user_localizer);
            edit = itemView.findViewById(R.id.component_user_edit);
            delete = itemView.findViewById(R.id.component_user_delete);
        }
    }
}

