package com.fitty.njora.nicollet.fitty_94056;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fitty.njora.nicollet.fitty_94056.Fragments.DatePickerFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class AddSessionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //An ArrayList for Spinner Items
    private ArrayList<String> gymDetails;

    //JSON Array
    private JSONArray result;

    private TextView date_view;
    String dateMessage;
    EditText trainer_picked, sets_no, reps_no;
    Spinner spinner;



    private static final String TAG = MainActivity.class.getSimpleName();
    private String mSpinnerLabel = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);

        //Initializing the ArrayList
        gymDetails= new ArrayList<String>();

        date_view = (TextView) findViewById(R.id.textView_pickedDate);
        trainer_picked = (EditText) findViewById(R.id.picked_Trainer);
        sets_no = (EditText) findViewById(R.id.EtSets_no);
        reps_no = (EditText) findViewById(R.id.EtReps_no);

        // Create the spinner.
        spinner = (Spinner) findViewById(R.id.gym_spinner);

        //This method will fetch the data from the URL
        getData();

//        if (spinner != null) {
//            spinner.setOnItemSelectedListener(this);
//        }
//        // Create ArrayAdapter using the string array and default spinner layout.
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.trainer_array, android.R.layout.simple_spinner_item);
//
//        // Specify the layout to use when the list of choices appears.
//        adapter.setDropDownViewResource
//                (android.R.layout.simple_spinner_dropdown_item);
//
//        // Apply the adapter to the spinner.
//        if (spinner != null) {
//            spinner.setAdapter(adapter);
//        }

    }

    public void showDatePickerDialog(View v) {
        android.support.v4.app.DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),
                getString(R.string.date_picker));
    }


    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);

        dateMessage = (month_string + "/" +
                day_string + "/" + year_string);
        date_view.setText(dateMessage);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mSpinnerLabel = adapterView.getItemAtPosition(i).toString();

        trainer_picked.setText(mSpinnerLabel);

    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }

    private void getData(){
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Details.DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray(Details.JSON_ARRAY);

                            //Calling method getStudents to get the students from the JSON Array
                            getGymDetails(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getGymDetails(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                gymDetails.add(json.getString(Details.TAG_GYM));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        spinner.setAdapter(new ArrayAdapter<String>(AddSessionActivity.this, android.R.layout.simple_spinner_dropdown_item, gymDetails));
    }


    public void postData(View view){
        try{



            RequestQueue requestQueue = Volley.newRequestQueue(this);

            String URL = "https://fittyapi.herokuapp.com/api/session/add";
            JSONObject jsonBody = new JSONObject();

            jsonBody.put("date", dateMessage);

            jsonBody.put("gym_id", 1);
            jsonBody.put("exercise_type", "Chest");


            jsonBody.put("reps_no", reps_no.getText());
            jsonBody.put("sets_no", sets_no.getText());
            jsonBody.put("user_id", 1);
            jsonBody.put("trainer_id", 1);


            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        } catch(
        JSONException e)

        {
            e.printStackTrace();
        }
    }


}
