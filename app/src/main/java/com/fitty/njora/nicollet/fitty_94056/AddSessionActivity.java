package com.fitty.njora.nicollet.fitty_94056;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AddSessionActivity extends AppCompatActivity {

    private TextView date_view, time_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);

        date_view = (TextView) findViewById(R.id.textView_pickedDate);
        time_view = (TextView) findViewById(R.id.textView_pickedTime);
    }

    public void showDatePickerDialog(View v) {
        android.support.v4.app.DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),
                getString(R.string.date_picker));
    }
    public void showTimePickerDialog(View view) {
        android.support.v4.app.DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(),
                getString(R.string.time_picker));
    }
    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);

        String dateMessage = (month_string + "/" +
                day_string + "/" + year_string);
        date_view.setText(dateMessage);

    }
    public void processTimePickerResult(int hourOfDay, int minute) {
    // Convert time elements into strings.
        String hour_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);
    // Assign the concatenated strings to timeMessage.
        String timeMessage = (hour_string + minute_string);

         time_view.setText(timeMessage);

    }
}
