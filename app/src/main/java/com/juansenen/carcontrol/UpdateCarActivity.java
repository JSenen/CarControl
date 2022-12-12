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
import android.widget.Toast;

import com.juansenen.carcontrol.db.AppDatabase;
import com.juansenen.carcontrol.domain.Cars;
import com.juansenen.carcontrol.util.DatePickerFragment;

public class UpdateCarActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_car);

        Intent intent = getIntent();
        String matricula = intent.getStringExtra("register");
        if (matricula == null)
            return;

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .build();
        Cars car = db.carsDAO().getByRegister(matricula);

        EditText editRegister = findViewById(R.id.edtxt_register_update);
        editRegister.setText(car.getRegister());
        EditText editTrade = findViewById(R.id.edtxt_trdmark_update);
        editTrade.setText(car.getTrademark());
        EditText editModel = findViewById(R.id.edtxt_model_update);
        editModel.setText(car.getModel());
        EditText editDate = findViewById(R.id.edtxt_year_update);
        editDate.setText(car.getYear());
        EditText editKms = findViewById(R.id.edtxt_kms_update);
        editKms.setText(String.valueOf(car.getKm()));

        editTFecha = findViewById(R.id.edtxt_year_update);
        editTFecha.setOnClickListener(this);

    }
    //Metodo onclick EditText fecha
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edtxt_year_update:
                showDatePickerDialog();
                break;
            default:

        }
    }
    //Mostrar Calendario al pulsar sobre edittext fecha
    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 Por que Enero es 0
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                editTFecha.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    //Menu Action Bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return true;
    }
    //Boton actualizar registro
    public void updateCar(){ // TODO (1) SOLUCIONAR UPDATE VEHICULOS
        EditText editRegister = findViewById(R.id.edtxt_register_update);
        String register = editRegister.getText().toString();
        EditText editTrade = findViewById(R.id.edtxt_trdmark_update);
        String trade = editTrade.getText().toString();
        EditText editModel = findViewById(R.id.edtxt_model_update);
        String model = editModel.getText().toString();
        EditText editDate = findViewById(R.id.edtxt_year_update);
        String dateBuy = editDate.getText().toString();
        EditText editKms = findViewById(R.id.edtxt_kms_update);
        int kms = Integer.parseInt(editKms.getText().toString());

        Cars car = new Cars(register, trade, model, dateBuy, kms);
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        db.carsDAO().update(car);


        Toast.makeText(this, R.string.Updated, Toast.LENGTH_SHORT).show();

    }


}