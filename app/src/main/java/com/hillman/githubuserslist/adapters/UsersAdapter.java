package com.hillman.githubuserslist.adapters;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hillman.githubuserslist.R;
import com.hillman.githubuserslist.model.User;
import com.hillman.githubuserslist.ui.CircularTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hllman
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ItemViewHolder> {
    private List<User> users;
    private Context context;

    public UsersAdapter(List<User> users, Context context) {
        this.users = users;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        protected AppCompatImageView userPhoto;
        protected TextView name,id;
        protected FloatingActionButton add;

        public ItemViewHolder(View itemView) {
            super(itemView);
            userPhoto = itemView.findViewById(R.id.photo);
            name = itemView.findViewById(R.id.name);
            id =  itemView.findViewById(R.id.id);
        }
    }

    @Override
    public UsersAdapter.ItemViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item,parent,false);
        final ItemViewHolder viewHolder=new ItemViewHolder(v);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final UsersAdapter.ItemViewHolder holder, final int position) {
        User user = users.get(position);
        Picasso.with(context)
                .load(user.getAvatarUrl()).error(R.mipmap.ic_launcher)
                .transform(new CircularTransformation())
                .into(holder.userPhoto);
        holder.name.setText(user.getLogin());
        holder.id.setText("ID "+ user.getId());
    }
    @Override
    public int getItemCount() {
        return users.size();
    }
}
