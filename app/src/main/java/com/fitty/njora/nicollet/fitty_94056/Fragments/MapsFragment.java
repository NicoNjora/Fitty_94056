package com.fitty.njora.nicollet.fitty_94056.Fragments;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fitty.njora.nicollet.fitty_94056.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import static android.content.Context.LOCATION_SERVICE;
import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapsFragment extends Fragment implements OnMapReadyCallback, LocationListener {


    private GoogleMap googleMap;
    SupportMapFragment mapFragment;

    GoogleApiClient googleApiClient;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    Context context;

    public double latitude;
    public double longitude;
    public LocationManager locationManager;
    public Criteria criteria;
    public String bestProvider;

    private static final int LOCATION_REQUEST_CODE = 101;

    Double x, y;
    String loc_name;
    JsonArrayRequest request;
    private RequestQueue requestQueue;
     String JSON_URL = "https://fittyapi.herokuapp.com/api/gym";


    public MapsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
//        mapFragment = (MapView) view.findViewById(R.id.map);

        mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);




        mapFragment.getMapAsync(this);


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        googleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .build();


    }

    public void onStart() {
        super.onStart();
        googleApiClient.connect();
    }



    public void onStop() {
        super.onStop();
        googleApiClient.disconnect();

    }


    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;

    public boolean checkLocationPermission() {
        // In Android 6.0 and higher you need to request permissions at runtime, and the user has
        // the ability to grant or deny each permission. Users can also revoke a previously-granted
        // permission at any time, so your app must always check that it has access to each
        // permission, before trying to perform actions that require that permission. Here, we’re using
        // ContextCompat.checkSelfPermission to check whether this app currently has the
        // ACCESS_COARSE_LOCATION permission

        if (ContextCompat.checkSelfPermission(getActivity(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                // If your app does have access to COARSE_LOCATION, then this method will return
                // PackageManager.PERMISSION_GRANTED//
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    android.Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // If your app doesn’t have this permission, then you’ll need to request it by calling
                // the ActivityCompat.requestPermissions method//
                requestPermissions(new String[]{
                                android.Manifest.permission.ACCESS_COARSE_LOCATION
                        },
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                // Request the permission by launching Android’s standard permissions dialog.
                // If you want to provide any additional information, such as why your app requires this
                // particular permission, then you’ll need to add this information before calling
                // requestPermission //
                requestPermissions(new String[]{
                                android.Manifest.permission.ACCESS_COARSE_LOCATION
                        },
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }



    @Override
    public void onMapReady(final GoogleMap googleMap) {

        this.googleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //Checking if the user has granted the permission
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Requesting the Location permission
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
            return;
        }

        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        criteria = new Criteria();
        bestProvider = locationManager.getBestProvider(criteria, true);


         markerRequest();

         checkLocationandAddToMap();


//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(trm));


    }

     private void markerRequest() {
        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        x = jsonObject.getDouble("latitude");
                        y = jsonObject.getDouble("longitude");
                        loc_name = jsonObject.getString("name");
                        String open_hrs = jsonObject.getString("opening_hrs");
                        String close_hrs =jsonObject.getString("closing_hrs");

                        LatLng gymloc = new LatLng(y, x);
                        googleMap.addMarker(new MarkerOptions().
                                position(gymloc).
                                title(loc_name).
                                icon(BitmapDescriptorFactory.fromResource(R.drawable.icons8)));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(gymloc));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                    checkLocationandAddToMap();
                } else {
                    Toast.makeText(getActivity(), "Location Permission Denied", Toast.LENGTH_SHORT).show();

                }
                break;

        }

    }

    private void checkLocationandAddToMap() {
        //Checking if the user has granted the permission
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Requesting the Location permission
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
            return;
        }

        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        criteria = new Criteria();
        bestProvider = locationManager.getBestProvider(criteria, true);


        //Fetching the last known location using the Fus
        Location location = locationManager.getLastKnownLocation(bestProvider);


        if (location != null) {

            onLocationChanged(location);

            //MarkerOptions are used to create a new Marker.You can specify location, title etc with MarkerOptions
//            MarkerOptions markerOptions = new MarkerOptions().position(new
//                    LatLng(location.getLatitude(), location.getLongitude())).title("You are Here");

            //Adding the created the marker on the map
//            googleMap.addMarker(markerOptions);

            Log.e("TAG", "GPS is on");
        } else {
            //This is what you need:
            locationManager.requestLocationUpdates(bestProvider, 1000, 0, (LocationListener) getActivity());
        }


    }



    @Override
    public void onLocationChanged(Location location) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        googleMap.setMyLocationEnabled(true);

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);

        googleMap.addMarker(new MarkerOptions().position(latLng).title("You are Here"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        // Instantiates a new CircleOptions object and defines the center and radius
        CircleOptions circleOptions = new CircleOptions()
                .center(new LatLng(latitude,longitude))
                .radius(5000)// In meters
                .strokeColor(Color.BLUE);

    // Get back the mutable Circle
//        Circle circle =
                googleMap.addCircle(circleOptions);

//        circle = googleMap.addCircle(new CircleOptions()
//                .center(new LatLng(37.4, -122.1))
//                .radius(1000)
//                .strokeWidth(10)
//                .strokeColor(Color.GREEN)
//                .fillColor(Color.argb(128, 255, 0, 0))
//                );


    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


}