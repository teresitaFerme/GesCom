package es.ucm.fdi.gescom.features.register_comunidad.initialize_users;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.gescom.R;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {
    private final ArrayList<InitializableUser> mUsers;
    private final LayoutInflater mInflater;

    public UsersAdapter(Context context, ArrayList<InitializableUser> users) {
        mInflater = LayoutInflater.from(context);
        mUsers = users;
    }

    @Override
    public UsersAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View userView = mInflater.inflate(R.layout.component_user_registration, parent, false);
        return new UserViewHolder(userView, this);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull UserViewHolder holder) {
        mUsers.get(holder.getAbsoluteAdapterPosition()).setUsername(String.valueOf(holder.userDni.getText()), String.valueOf(holder.userDni.getText()));
        mUsers.get(holder.getAbsoluteAdapterPosition()).setLocalizer(String.valueOf(holder.userLocalizer.getText()));
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.numberTextView.setText(String.valueOf( mUsers.get(holder.getAbsoluteAdapterPosition()).getPosition()));
        holder.userDni.setText(String.valueOf( mUsers.get(holder.getAbsoluteAdapterPosition()).getUsername()));
        holder.userLocalizer.setText(String.valueOf( mUsers.get(holder.getAbsoluteAdapterPosition()).getLocalizer()));
    }


    @Override
    public void onViewRecycled(@NonNull UserViewHolder holder) {
        holder.userDni.setText("");
        holder.userLocalizer.setText("");
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {
        public final TextView numberTextView;
        public final EditText userDni, userLocalizer;
        final UsersAdapter mAdapter;

        public UserViewHolder(View itemView, UsersAdapter usersAdapter) {
            super(itemView);
            numberTextView = itemView.findViewById(R.id.user_number);
            mAdapter = usersAdapter;
            userDni = itemView.findViewById(R.id.initialize_dni);
            userLocalizer = itemView.findViewById(R.id.initialize_localizer);

        }
    }
}
