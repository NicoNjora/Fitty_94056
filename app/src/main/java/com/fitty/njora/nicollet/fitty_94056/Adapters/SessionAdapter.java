package com.fitty.njora.nicollet.fitty_94056.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fitty.njora.nicollet.fitty_94056.Model.Session;
import com.fitty.njora.nicollet.fitty_94056.R;

import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.MyViewHolder> {
    public Context context;
    private List<Session> sessionList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView gym, date, trainer, exercise_type, sets_no, reps_no;


        public MyViewHolder(View view) {
            super(view);
            gym = view.findViewById(R.id.session_gym);
            date= view.findViewById(R.id.session_date);
            exercise_type= view.findViewById(R.id.session_exercise_type);
            trainer = view.findViewById(R.id.session_trainer);
            sets_no= view.findViewById(R.id.session_sets_no);
            reps_no = view.findViewById(R.id.session_reps_no);


        }
    }


    public SessionAdapter(Context context, List<Session> sessionList) {
        this.context = context;
        this.sessionList = sessionList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.session_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final Session session = sessionList.get(position);

        holder.gym.setText(session.getGym());
        holder.date.setText(session.getDate());
        holder.exercise_type.setText(session.getExercise_type());
        holder.trainer.setText(session.getTrainer());
        holder.sets_no.setText(String.valueOf(session.getSets()));
        holder.reps_no.setText(String.valueOf(session.getReps()));

    }

    @Override
    public int getItemCount() {
        return sessionList.size();
    }
}
