package com.juansenen.carcontrol;

import static com.juansenen.carcontrol.db.Constans.DATABASE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

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
    private String matricula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fuel);

        Intent intent = getIntent();
        matricula = intent.getStringExtra("register");
        if (matricula == null)
            return;
        fuelList = new ArrayList<>();
        TextView textView = findViewById(R.id.rcview_fuel_main_register);
        textView.setText(matricula);

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        fuelList = db.fuelDAO().getFuelByCar(matricula);

        RecyclerView recyclerView = findViewById(R.id.rcview_fuel_main);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        fuelAdapter = new FuelAdapter(this,fuelList);
        recyclerView.setAdapter(fuelAdapter);

        registerForContextMenu(recyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final AppDatabase db = Room.databaseBuilder(this,AppDatabase.class,DATABASE_NAME)
                .allowMainThreadQueries().build();

        fuelList.clear();
        fuelList.addAll(db.fuelDAO().getFuelByCar(matricula));
        fuelAdapter.notifyDataSetChanged();
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