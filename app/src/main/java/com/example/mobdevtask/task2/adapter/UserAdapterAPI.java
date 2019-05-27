package com.example.mobdevtask.task2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mobdevtask.R;
import com.example.mobdevtask.task2.apimodels.Result;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class UserAdapterAPI extends RecyclerView.Adapter<UserAdapterAPI.UserHolder> {

    private List<Result> users = new ArrayList<>();

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View userView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_card, parent, false);
        return new UserHolder(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        Result randomUser = users.get(position);
        holder.txtTitle.setText(randomUser.getName().getTitle().toUpperCase());
        holder.txtName.setText(toUpperCaseFirstLetter(randomUser.getName().getFirst()));
        holder.txtLastName.setText(toUpperCaseFirstLetter(randomUser.getName().getLast()));
        holder.txtEmail.setText(randomUser.getEmail());
        holder.txtNat.setText(randomUser.getNat());
        Picasso.get().load(randomUser.getPicture().getMedium()).fit().centerCrop().into(holder.imgPhoto);

    }

    @Override
    public int getItemCount() {
        return users.size();

    }

    public void setUsers(List<Result> users) {
        this.users = users;
        notifyDataSetChanged();
    }



    class UserHolder extends RecyclerView.ViewHolder {

        private TextView txtTitle, txtName, txtLastName, txtEmail, txtNat;
        private ImageView imgPhoto;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.text_view_title);
            txtName = itemView.findViewById(R.id.text_view_name);
            txtLastName = itemView.findViewById(R.id.text_view_lastname);
            txtEmail = itemView.findViewById(R.id.text_view_email);
            txtNat = itemView.findViewById(R.id.text_view_nation);
            imgPhoto = itemView.findViewById(R.id.image_view_photo);
        }
    }

    public String toUpperCaseFirstLetter(String text) {
        String transformedString = text.substring(0,1).toUpperCase() + text.substring(1);
        return transformedString;
    }

}
