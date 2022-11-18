package com.juansenen.carcontrol;

import static com.juansenen.carcontrol.db.Constans.DATABASE_NAME;
import static com.juansenen.carcontrol.db.Constans.DATABASE_REVISION;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.juansenen.carcontrol.db.AppDatabase;
import com.juansenen.carcontrol.db.RevisionDAO;
import com.juansenen.carcontrol.domain.Cars;
import com.juansenen.carcontrol.domain.Revision;

public class DetailCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_car);

        Intent intent = getIntent();
        String register = intent.getStringExtra("register");
        if (register == null){
            return;
        }

        // TODO Revisiones en los detalles del vehiculo
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        Cars car = db.carsDAO().getByRegister(register);
        fillData(car);



    }

    private void fillData(Cars car){
        TextView txtdetregister = findViewById(R.id.txtdetail_register);
        TextView txttrdmark = findViewById(R.id.txtdetail_trdmark);
        TextView txtmodel = findViewById(R.id.txtdetail_model);
        TextView txtyear = findViewById(R.id.txtdetail_year);
        TextView txtkm = findViewById(R.id.txtdetail_km);

        txtdetregister.setText(car.getRegister());
        txttrdmark.setText(car.getTrademark());
        txtmodel.setText(car.getModel());
        txtyear.setText(String.valueOf(car.getYear()));
        txtkm.setText(String.valueOf(car.getKm()));


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

    public void addRevision(View view){

    }

}
