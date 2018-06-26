package com.fitty.njora.nicollet.fitty_94056;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



/**
 * A simple {@link Fragment} subclass.
 */
public class MapsFragment extends Fragment implements OnMapReadyCallback {


    private GoogleMap googleMap;
    SupportMapFragment mapFragment;


    public MapsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
//        mapFragment = (MapView) view.findViewById(R.id.map);

        mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;
    }


//    // latitude and longitude
//    double latitude = 1.2108;
//    double longitude = 36.7950;
//
//    // create marker
//    MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Hello Maps ");
//    googleMap.addMarker(marker);


    @Override
    public void onMapReady(final GoogleMap googleMap) {


        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        //    latitude and longitude
        double latitude = 1.3089;
        double longitude = 36.8121;

        // create marker
        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Gym Location ");
        googleMap.addMarker(marker);

        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(latitude, longitude)).zoom(12).build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));



        // Add a marker in Sydney and move the camera
        LatLng trm = new LatLng(1.2108, 36.7950);
        googleMap.addMarker(new MarkerOptions().position(trm).title("Marker in Two Rivers"));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(trm));
    }



}
