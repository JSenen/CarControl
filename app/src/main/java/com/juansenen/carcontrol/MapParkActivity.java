package com.juansenen.carcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mapbox.android.core.MapboxSdkInfoForUserAgentGenerator;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;


public class MapParkActivity extends AppCompatActivity {

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_park);

        mapView = findViewById(R.id.mapViewPark);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS);

    }
}