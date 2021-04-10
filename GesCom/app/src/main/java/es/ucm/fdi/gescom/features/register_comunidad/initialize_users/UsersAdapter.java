package es.ucm.fdi.gescom.features.register_comunidad.initialize_users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.ucm.fdi.gescom.R;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    private List<InitializableUser> mUsers;

    public UsersAdapter(List<InitializableUser> users) {
        mUsers = users;
    }

    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View userView = inflater.inflate(R.layout.component_user_registration, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(userView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InitializableUser initUser = mUsers.get(position);

        TextView textView = holder.nameTextView;
        textView.setText(String.valueOf(initUser.getPosition()));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.holi);
            // messageButton = (Button) itemView.findViewById(R.id.message_button);
        }
    }
}
