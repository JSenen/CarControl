package com.juansenen.carcontrol;

import static com.juansenen.carcontrol.db.Constans.DATABASE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.juansenen.carcontrol.db.AppDatabase;
import com.juansenen.carcontrol.domain.Fuel;

public class AddFuelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fuel);
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
    public void butaddfuel(View view){
        EditText edtid = findViewById(R.id.edtxt_addfuel_id);
        EditText edtlitre = findViewById(R.id.edtxt_addfuel_litres);
        EditText edtprice = findViewById(R.id.edtxt_addfuel_price);

        String register = edtid.getText().toString();
        float litre = Float.parseFloat(edtlitre.getText().toString());
        float price = Float.parseFloat(edtprice.getText().toString());

        Fuel fuel = new Fuel(register, price, litre);
        final AppDatabase db = Room.databaseBuilder(this,AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        db.fuelDAO().insert(fuel);

        Toast.makeText(this,"AÑADIDO",Toast.LENGTH_SHORT).show();

        edtid.setText("");
        edtlitre.setText("");
        edtprice.setText("");
        edtid.requestFocus();
    }
}