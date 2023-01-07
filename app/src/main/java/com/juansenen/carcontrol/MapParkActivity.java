package com.juansenen.carcontrol;

import static com.juansenen.carcontrol.db.Constans.DATABASE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.location.LocationServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.juansenen.carcontrol.db.AppDatabase;
import com.juansenen.carcontrol.domain.Park;
import com.mapbox.geojson.Point;
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


public class MapParkActivity extends AppCompatActivity {

    private MapView mapView;
    private Point point;
    private PointAnnotationManager pointAnnotationManager;
    private String matricula;
    private List<Park> parkList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_park);

        //Recuperamos los elementos del layout
        mapView = findViewById(R.id.mapViewPark);

        //Recuperamos el campo enviado por el recyclerview
        Intent intent = getIntent();
        matricula = intent.getStringExtra("register");
        if (matricula == null)
            return;

        //Metodo para controlar el mapa y añadir marcador al pulsar sobre una posicion
        GesturesPlugin gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(point -> {
            delAllMakers();
            this.point = point;
           addMarketPoint(point);
            return true;
        });
        initializePointManager();

    }

    private void initializePointManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);

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

}