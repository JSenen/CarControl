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

        RecyclerView recyclerView = findViewById(R.id.rcview_main);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CarsAdapter(this,carsList);
        recyclerView.setAdapter(adapter);

        registerForContextMenu(recyclerView);


    }


    @Override
    protected void onResume() {
        super.onResume();

        final AppDatabase db = Room.databaseBuilder(this,AppDatabase.class,DATABASE_NAME)
                .allowMainThreadQueries().build();

        carsList.clear();
        carsList.addAll(db.carsDAO().getAll());
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_addcar,menu);
        getMenuInflater().inflate(R.menu.menu_takephoto, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.actbar_add) {
            Intent intent = new Intent(this, AddCarActivity.class);
            startActivity(intent);
            return true;
        }else if (item.getItemId() == R.id.actbar_map) {
            Intent intent = new Intent(this, GPSActivity.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.actbar_photo){
            Intent intent = new Intent(this, TakePhotoActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

}