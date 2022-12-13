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

public class AddCarActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        editTFecha = findViewById(R.id.edtxt_year);
        editTFecha.setOnClickListener(this);

    }

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

    public void butAdd(View view) {

        EditText ed_register = findViewById(R.id.edtxt_register);
        EditText ed_trdmark = findViewById(R.id.edtxt_trdmark);
        EditText ed_model = findViewById(R.id.edtxt_model);
        EditText ed_km = findViewById(R.id.edtxt_kms);


        String register = ed_register.getText().toString();
        String trdmark = ed_trdmark.getText().toString();
        String model = ed_model.getText().toString();
        String year = editTFecha.getText().toString();
        int km = Integer.parseInt(ed_km.getText().toString());


        Cars car = new Cars(register, trdmark, model, year, km);
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        db.carsDAO().insert(car);

        Toast.makeText(this, R.string.Added, Toast.LENGTH_SHORT).show();

        ed_register.setText("");
        ed_trdmark.setText("");
        ed_model.setText("");
        editTFecha.setText("");
        ed_km.setText("");
        ed_register.requestFocus();

    }

    //Metodo onclick EditText fecha
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edtxt_year:
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
}