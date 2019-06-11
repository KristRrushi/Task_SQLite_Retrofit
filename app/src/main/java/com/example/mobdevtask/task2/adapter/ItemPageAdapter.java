package com.example.mobdevtask.task2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobdevtask.R;
import com.example.mobdevtask.task2.Task2Fragment;
import com.example.mobdevtask.task2.apimodels.Result;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ItemPageAdapter extends PagedListAdapter<Result, ItemPageAdapter.UserHolder> {

    private Context ctx;

    public ItemPageAdapter(Context ctx) {
        super(DIFF_CALLBACK);
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(ctx).inflate(R.layout.item_user_card, parent, false);

        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {

        Result result = getItem(position);
        holder.txtTitle.setText(result.getName().getTitle().toUpperCase());
        holder.txtName.setText(toUpperCaseFirstLetter(result.getName().getFirst()));
        holder.txtLastName.setText(toUpperCaseFirstLetter(result.getName().getLast()));
        holder.txtEmail.setText(result.getEmail());
        holder.txtNat.setText(result.getNat());
        Picasso.get().load(result.getPicture().getMedium()).fit().centerCrop().into(holder.imgPhoto);

    }


    private static DiffUtil.ItemCallback<Result> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Result>() {
                @Override
                public boolean areItemsTheSame(Result oldItem, Result newItem) {
                    return oldItem.getLogin().getUuid() == newItem.getLogin().getUuid();
                }

                @Override
                public boolean areContentsTheSame(Result oldItem, Result newItem) {
                    return oldItem.equals(newItem);
                }
            };



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
