package com.example.iceauror.lab10;

import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import static com.google.maps.android.SphericalUtil.computeArea;
import static com.google.maps.android.SphericalUtil.computeDistanceBetween;
import static com.google.maps.android.SphericalUtil.computeHeading;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private Marker home;
    private Marker node2;
    private Marker node3;
    private Marker node4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // TODO Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // TODO Add a marker in your hometown
        // TODO Move the camera to the Marker Location
        // TODO Change the initial states of the Map.
        // TODO Add the styling to the Map

        LatLng sydney = new LatLng(42.026275, -93.6473925);
        LatLng marker1 = new LatLng(42.073092, -93.625037);
        LatLng marker2 = new LatLng(42.072893, -93.624299);
        LatLng marker3 = new LatLng(42.028229, -93.650864);

        home= mMap.addMarker(new MarkerOptions()
                .position(sydney));

        node2 = mMap.addMarker(new MarkerOptions()
                .position(marker1)
        );

        node3 = mMap.addMarker(new MarkerOptions()
                .position(marker2)
        );

        node4 = mMap.addMarker(new MarkerOptions()
                .position(marker3)
        );
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {

            @Override
            public boolean onMarkerClick(Marker arg0) {
                String Text;
                //LatLng arr[]={home.getPosition(), node2.getPosition(), node3.getPosition(), node4.getPosition()};
                List<LatLng> arr= new ArrayList<LatLng>();
                arr.add(home.getPosition());
                arr.add(node2.getPosition());
                arr.add(node3.getPosition());
                arr.add(node4.getPosition());
                        //, node2.getPosition(), node3.getPosition(), node4.getPosition()};

                Text="heading: "+Double.toString(   computeHeading(home.getPosition(),arg0.getPosition())    )
                +"\n Distance from home: "+Double.toString(   computeDistanceBetween(home.getPosition(),arg0.getPosition()) )
                +"\n Area: "+Double.toString(   computeArea(arr) )
                ;
                //Text=(   computeHeading(home.getPosition(),node2.getPosition())    ).toString();
                Toast.makeText(MapsActivity.this, Text, Toast.LENGTH_SHORT).show();// display toast
                return true;
            }

        });


        mMap.moveCamera(CameraUpdateFactory.zoomTo(15)); // 8 8 for random curr place
        //17for real inputs
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker3));

        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(
                this, R.raw.mapstyle_night));



    }
/*
    public boolean onMarkerClick(Marker marker)
    {
        int position = (int)(marker.getTag());
        //return text
        //int position2= ;
        Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
        return false;
    }
    */
}
