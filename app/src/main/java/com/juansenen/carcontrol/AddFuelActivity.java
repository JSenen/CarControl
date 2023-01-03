package com.juansenen.carcontrol;

import static com.juansenen.carcontrol.db.Constans.DATABASE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.juansenen.carcontrol.db.AppDatabase;
import com.juansenen.carcontrol.domain.Cars;
import com.juansenen.carcontrol.domain.Fuel;
import com.juansenen.carcontrol.util.DatePickerFragment;

public class AddFuelActivity extends AppCompatActivity implements View.OnClickListener {

    private String matricula;
    private EditText editTextFecha;
    private Cars car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fuel);

        //Recuperamos de el vehiculo seleccionado en el adapter por su matricula
        Intent intent = getIntent();
        matricula = intent.getStringExtra("register");
        if (matricula == null)
            return;
        TextView txtregister = findViewById(R.id.txt_addfuel_id);
        txtregister.setText(matricula);

        //Lo obtenemos de la BD
        final AppDatabase db = Room.databaseBuilder(this,AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        car = db.carsDAO().getByRegister(matricula);
        //Colocar los kilomentros actuales del coche
        EditText editkm = findViewById(R.id.edtxt_addfuel_km);
        editkm.setHint(String.valueOf(car.getKm()));

        editTextFecha = findViewById(R.id.edtxt_addfuel_date);
        //Listener en el campo fecha para abrir DataPicker en caso de pulsarlo
        editTextFecha.setOnClickListener(this);
    }

    //Metodo al pulsar en campo fecha para mostrar DataPicker
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edtxt_addfuel_date:
                showDatePickerDialog();
                break;
            default:

        }
    }

    //Menu en la action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back,menu);
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.actbar_back){
            //Regresa a la pantalla anterior
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }else if(item.getItemId() == R.id.item_home){
            //Regresa a la pantalla principal
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
            }

        return false;
    }
    //MEtodo al pulsar en el boton de registrar repostaje
    public void butaddfuel(View view){

        //Obtenemos el vehiculo de la BD
        final AppDatabase db = Room.databaseBuilder(this,AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        car = db.carsDAO().getByRegister(matricula);

        //Recuperamos elementos del layout
        TextView txtregister = findViewById(R.id.txt_addfuel_id);
        //Pintamos la matricula del vehiculo en su campo
        txtregister.setText(matricula);
        EditText edtlitre = findViewById(R.id.edtxt_addfuel_litres);
        EditText edtprice = findViewById(R.id.edtxt_addfuel_price);
        EditText editkm = findViewById(R.id.edtxt_addfuel_km);
        editkm.setHint(String.valueOf(car.getKm()));

        float litre = Float.parseFloat(edtlitre.getText().toString());
        float price = Float.parseFloat(edtprice.getText().toString());
        int km = Integer.parseInt(editkm.getText().toString());
        float total = litre * price;
        String date = editTextFecha.getText().toString();

        //Creamos el obajeto
        Fuel fuel = new Fuel(matricula, price, litre, km, date, total);
        //Añadir repostaje
        db.fuelDAO().insert(fuel);
        //Actualizar km del vehiculo
        car.setKm(km);
        db.carsDAO().update(car);

        //Elemento emergente indica se ha grabado el repostaje
        Toast.makeText(this,R.string.add,Toast.LENGTH_SHORT).show();

        //Vaciamos los campos
        edtlitre.setText("");
        edtprice.setText("");
        editkm.setHint(String.valueOf(car.getKm()));
        editTextFecha.setText("");
        //Llevamos el foco al campo litros
        edtlitre.requestFocus();
    }
    //Mostrar Calendario al pulsar sobre edittext fecha
    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 Por que Enero es 0
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                editTextFecha.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}