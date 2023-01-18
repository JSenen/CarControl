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

public class LastParkActivity extends AppCompatActivity {

    private String matricula;
    private Park park;
    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_park);

        //recuperamos los elementos del Layout
        mapView = findViewById(R.id.mapLastPark);

        //Recuperamos los datos del vehiculo seleccionado
        Intent intent = getIntent();
        matricula = intent.getStringExtra("register");
        if (matricula == null)
            return;

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        //Obentemos los datos de la BD por medio del campo relacionado matricula
        park = db.parkDAO().getLastPark(matricula);

        double latitude = park.getLatitude();
        double longitude = park.getLongitude();

        //Metodos para colocar el marcador en el mapa segun los campos
        initializePointManager();
        setCameraPosition(latitude,longitude);
        addMarker(latitude,longitude);


    }
    private void initializePointManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);

    }
    //Añadimos marcador en la ultima posicion
    private void addMarker(double latitude, double longitude) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(Point.fromLngLat(longitude, latitude))
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_parking_foreground));

        pointAnnotationManager.create(pointAnnotationOptions);
    }
    //Configuramos la camara y como se visualiza
    private void setCameraPosition(double latitude, double longitude){
        CameraOptions cameraPosition = new CameraOptions.Builder()
                .center(Point.fromLngLat(longitude, latitude))
                .pitch(60.0)
                .zoom(10.5)
                .bearing(-17.6)
                .build();
        mapView.getMapboxMap().setCamera(cameraPosition);

    }
    //Menu en la action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Al pulsar en menu action bar regresamos a la pantalla principal
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return true;
    }
}