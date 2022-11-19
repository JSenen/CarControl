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

import com.juansenen.carcontrol.R;
import com.juansenen.carcontrol.adapter.CarsAdapter;
import com.juansenen.carcontrol.adapter.FuelAdapter;
import com.juansenen.carcontrol.db.AppDatabase;
import com.juansenen.carcontrol.domain.Cars;
import com.juansenen.carcontrol.domain.Fuel;

import java.util.ArrayList;
import java.util.List;

public class DetailFuelActivity extends AppCompatActivity {

    private List<Fuel> fuelList;
    private FuelAdapter fuelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fuel);

        fuelList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rcview_fuel_main);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        fuelAdapter = new FuelAdapter(this,fuelList);
        recyclerView.setAdapter(fuelAdapter);

        registerForContextMenu(recyclerView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return true;
    }
}