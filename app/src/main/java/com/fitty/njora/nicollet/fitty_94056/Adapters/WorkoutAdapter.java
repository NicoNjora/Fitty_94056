package com.fitty.njora.nicollet.fitty_94056.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fitty.njora.nicollet.fitty_94056.Model.Workout;
import com.fitty.njora.nicollet.fitty_94056.R;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.MyViewHolder> {

        private Context context;
        private List<Workout> workoutList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView type, reps, sets;


            private MyViewHolder(View view) {
                super(view);
                type = view.findViewById(R.id.workout_type);
                reps= view.findViewById(R.id.workout_exercise);
                sets = view.findViewById(R.id.workout_sets);

            }
        }


        public WorkoutAdapter(Context context, List<Workout> workoutList) {
            this.context = context;
            this.workoutList = workoutList;
        }

        @Override
        public WorkoutAdapter.MyViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.workout_item, parent, false);

            return new WorkoutAdapter.MyViewHolder(itemView);
        }


        @Override
        public void onBindViewHolder(WorkoutAdapter.MyViewHolder holder, final int position) {
            final Workout workout= workoutList.get(position);
            holder.type.setText(workout.getType());
            holder.reps.setText(workout.getReps());
            holder.sets.setText(workout.getSets());


        }

        @Override
        public int getItemCount() {
            return workoutList.size();
        }
}

