package com.juansenen.carcontrol;

import static com.juansenen.carcontrol.db.Constans.DATABASE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.juansenen.carcontrol.db.AppDatabase;
import com.juansenen.carcontrol.domain.Park;
import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;

import java.util.ArrayList;
import java.util.List;

public class GPSActivity extends AppCompatActivity implements Style.OnStyleLoaded {


    // Inicializamos otros elementos
    // del layouy
    private double longitude ;
    private double latitude ;
    private MapView mapview;
    private PointAnnotationManager pointAnnotationManager;
    private FusedLocationProviderClient fusedLocationClient;
    private List<Park> places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Creamos la instancia al cliente proveedor de posicion en la Activity
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        setContentView(R.layout.activity_gpsactivity);

        //recuperamos elemntos del layout
        mapview = findViewById(R.id.mapviewgps);
        mapview.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);

        //Recogemos datos de la BD
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        places = db.parkDAO().getAll();

        //Comprobamos permisos
        checkLocationPermission();
        // Llamamos a los metodos para pbener la posicion
        initializePointAnnotationManager();

        //Llamada al metodo para colocar marcador
        addMarker(latitude, longitude);

    }

    private void initializePointAnnotationManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapview);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
        Log.i("GPS ACtivity","Initialize Point Annotation");
    }



    @Override
    public void onStyleLoaded(@NonNull Style style) {
        addMarker(latitude,longitude);
        gps();
    }

    @SuppressLint("MissingPermission")
    private void gps() {
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            longitude = location.getLongitude();
                            latitude = location.getLatitude();

                            //Colocamos camara en ultima posicion GPS
                            setCameraPosition(latitude, longitude);
                            addMarker(latitude, longitude);

                        }
                    }
                });

    }
    private void checkLocationPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
            }
        }

    }

    //Metodo establece configutaciones de visualizacion de la camara
    private void setCameraPosition(double latitude, double longitude){
        CameraOptions cameraPosition = new CameraOptions.Builder()
                .center(Point.fromLngLat(longitude,latitude))
                .pitch(45.0)
                .zoom(9.1)
                .bearing(-17.6)
                .build();
        mapview.getMapboxMap().setCamera(cameraPosition);

    }
    //Metodo para añadir marcador al mapa
    private void addMarker(double latitude, double longitude) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(Point.fromLngLat(longitude,latitude))
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_gps_marker_foreground));

        pointAnnotationManager.create(pointAnnotationOptions);
    }
    //Metodo menus en la action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Al seleccionar en la action bar, regresa a la pantalla anterior
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return true;
    }



}