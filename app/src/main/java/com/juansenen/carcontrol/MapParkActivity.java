package com.juansenen.carcontrol;

import static com.juansenen.carcontrol.db.Constans.DATABASE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.juansenen.carcontrol.db.AppDatabase;
import com.juansenen.carcontrol.domain.Park;
import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.MapboxMap;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.mapbox.maps.plugin.gestures.OnMapClickListener;

import java.util.ArrayList;
import java.util.List;


public class MapParkActivity extends AppCompatActivity implements Style.OnStyleLoaded {

    private MapView mapView;
    private double longitude;
    private double latitude;
    private Point point;
    private PointAnnotationManager pointAnnotationManager;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private String matricula;
    private List<Park> parkList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_park);

        //Recuperamos los elementos del layout
        mapView = findViewById(R.id.mapViewPark);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);

        //Creamos la instancia al cliente proveedor de posicion en la Activity
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        //Recuperamos el campo enviado por el recyclerview
        Intent intent = getIntent();
        matricula = intent.getStringExtra("register");
        if (matricula == null)
            return;

        //Recuperamos BD
        final AppDatabase db = Room.databaseBuilder(this,AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();


        //Metodo para controlar el mapa y añadir marcador al pulsar sobre una posicion
        GesturesPlugin gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(point -> {
            delAllMakers();
            this.point = point;
           addMarketPoint(point);
            return true;
        });

        //Método añade posición al pulsar botón flotante
        FloatingActionButton fab = findViewById(R.id.but_float_addpark);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Insertamos en los campos de la base de datos la posicion
                Park park = new Park(matricula,latitude, longitude);
                db.parkDAO().insert(park);

                //Snackbar emergente que nos indica posicion guardada
                //Debemos asociarla a un elemento del activity, en este caso en mapview
                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.mapViewPark),
                        R.string.save_position, Snackbar.LENGTH_SHORT);
                mySnackbar.show();
            }
        });

        //Comprobamos permisos
        checkLocationPermission();
        initializePointManager();

    }

    private void initializePointManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
        Log.i("GPS AddPArk","Initialize Point Annotation");

    }

    @Override
    public void onStyleLoaded(@NonNull Style style) {
        gps();
    }
    @SuppressLint("MissingPermission")
    private void gps() {
        fusedLocationProviderClient.getLastLocation()
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
                            //Colocamos indicador en la posición del GPS
                            addMarketGPS(latitude, longitude);
                        }
                    }
                });

    }
    //Metodo para añadir marcador al mapa en la posición del GPS
    private void addMarketGPS(double latitude, double longitude) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(Point.fromLngLat(longitude,latitude))
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_parking_foreground));

        pointAnnotationManager.create(pointAnnotationOptions);
    }
    //Metodo establece configutaciones de visualizacion de la camara
    private void setCameraPosition(double latitude, double longitude){
        CameraOptions cameraPosition = new CameraOptions.Builder()
                .center(Point.fromLngLat(longitude,latitude))
                .pitch(45.0)
                .zoom(9.1)
                .bearing(-17.6)
                .build();
        mapView.getMapboxMap().setCamera(cameraPosition);

    }
    private void checkLocationPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
            }
        }

    }
    //Metodo para añadir marcador
    private void addMarketPoint(Point point) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(point)
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_parking_foreground));
        pointAnnotationManager.create(pointAnnotationOptions);
        Park park = new Park(matricula,point.latitude(), point.longitude());

        final AppDatabase db = Room.databaseBuilder(this,AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        //Insertamos en los campos de la base de datos la posicion
        db.parkDAO().insert(park);

        //Snackbar emergente que nos indica posicion guardada
        //Debemos asociarla a un elemento del activity, en este caso en mapview
        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.mapViewPark),
                R.string.save_position, Snackbar.LENGTH_SHORT);
        mySnackbar.show();

    }
    //Borrar todos los marcadores
    private void delAllMakers() {
        pointAnnotationManager.deleteAll();
    }

    //Menu en el Action BAr
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Al pulsar en opcion del action bar regresa a pantalla principal
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}