package com.juansenen.carcontrol;

import static com.juansenen.carcontrol.db.Constans.DATABASE_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.juansenen.carcontrol.db.AppDatabase;
import com.juansenen.carcontrol.domain.Cars;

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

        // Cargar los detalles del vehiculo
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


    }

}
