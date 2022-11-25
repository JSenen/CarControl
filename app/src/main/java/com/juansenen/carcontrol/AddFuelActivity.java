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
import android.widget.TextView;
import android.widget.Toast;

import com.juansenen.carcontrol.db.AppDatabase;
import com.juansenen.carcontrol.domain.Fuel;

public class AddFuelActivity extends AppCompatActivity {
    private String matricula;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fuel);
        Intent intent = getIntent();
        matricula = intent.getStringExtra("register");
        if (matricula == null)
            return;
        TextView txtregister = findViewById(R.id.txt_addfuel_id);
        txtregister.setText(matricula);
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

        TextView txtregister = findViewById(R.id.txt_addfuel_id);
        txtregister.setText(matricula);

        EditText edtlitre = findViewById(R.id.edtxt_addfuel_litres);
        EditText edtprice = findViewById(R.id.edtxt_addfuel_price);
        EditText editkm = findViewById(R.id.edtxt_addfuel_km);

        float litre = Float.parseFloat(edtlitre.getText().toString());
        float price = Float.parseFloat(edtprice.getText().toString());
        int km = Integer.parseInt(editkm.getText().toString());
        float total = litre * price;

        Fuel fuel = new Fuel(matricula, price, litre, km, total);
        final AppDatabase db = Room.databaseBuilder(this,AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        db.fuelDAO().insert(fuel);

        Toast.makeText(this,R.string.add,Toast.LENGTH_SHORT).show();

        edtlitre.setText("");
        edtprice.setText("");
        editkm.setText("");
        edtlitre.requestFocus();
    }
}