package com.example.mobdevtask.task1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.mobdevtask.R;
import com.example.mobdevtask.task1.EncrytionServices;
import com.example.mobdevtask.task1.model.User;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private List<User> users = new ArrayList<>();


    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View userView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UserHolder(userView);
    }


    @Override
    public void onBindViewHolder(@NonNull final UserHolder holder, final int position) {
        final String userName = users.get(position).getName();
        final String userPass = users.get(position).getPassword();
        holder.textViewName.setText(userName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String decryptedPass = EncrytionServices.decrypt(userName, userPass);
                    holder.textViewPass.setText(decryptedPass);

                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    class UserHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewPass;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewPass = itemView.findViewById(R.id.text_view_password);


        }
    }

}
