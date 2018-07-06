package com.fitty.njora.nicollet.fitty_94056.Adapters;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fitty.njora.nicollet.fitty_94056.Model.Trainer;
import com.fitty.njora.nicollet.fitty_94056.R;

import java.util.List;

public class TrainerAdapter extends RecyclerView.Adapter<TrainerAdapter.MyViewHolder> {

    private Context mContext;
    private List<Trainer> trainerList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, contacts;
        public ImageView photo, overflow;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.title);
            contacts = (TextView) view.findViewById(R.id.count);
            photo = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }


    public TrainerAdapter(Context mContext, List<Trainer> TrainerList) {
        this.mContext = mContext;
        this.trainerList = TrainerList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trainer_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Trainer trainer = trainerList.get(position);
        holder.name.setText(trainer.getName());
        holder.contacts.setText((String.valueOf(trainer.getContacts())));
        String photo_url = trainer.getPhoto();

        // loading album cover using Glide library
        Glide.with(mContext)
                .load(photo_url)
                .into(holder.photo);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_trainer, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_contact:
                    Toast.makeText(mContext, "Contact", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_rate:
                    Toast.makeText(mContext, "Rate", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return trainerList.size();
    }
}