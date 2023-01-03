package com.juansenen.carcontrol;

import static com.juansenen.carcontrol.db.Constans.DATABASE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.juansenen.carcontrol.adapter.CarsAdapter;
import com.juansenen.carcontrol.db.AppDatabase;
import com.juansenen.carcontrol.domain.Cars;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Cars> carsList;
    private CarsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carsList = new ArrayList<>();

        //Recuperamos el recyclerview del layout
        RecyclerView recyclerView = findViewById(R.id.rcview_main);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //Construimos el adapter del recyclerview
        adapter = new CarsAdapter(this,carsList);
        recyclerView.setAdapter(adapter);

        registerForContextMenu(recyclerView);


    }

    //Al volver a la Activity principal, recuperamos los datos de la BD
    @Override
    protected void onResume() {
        super.onResume();

        final AppDatabase db = Room.databaseBuilder(this,AppDatabase.class,DATABASE_NAME)
                .allowMainThreadQueries().build();

        //Limpiamos la lista de coches
        carsList.clear();
        //Añadimos los coches de la BD
        carsList.addAll(db.carsDAO().getAll());
        //Notificamos cambios al adapter
        adapter.notifyDataSetChanged();

    }
    //Opciones de menu en la action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_addcar,menu);
        getMenuInflater().inflate(R.menu.menu_takephoto, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.actbar_add) {
            // Intent para añadir nuevo coche
            Intent intent = new Intent(this, AddCarActivity.class);
            startActivity(intent);
            return true;
        }else if (item.getItemId() == R.id.actbar_map) {
            //Intent para ir a la Activity GPS (mapa)
            Intent intent = new Intent(this, GPSActivity.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.actbar_photo){
            //Intent para tomar fotografia
            Intent intent = new Intent(this, TakePhotoActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

}