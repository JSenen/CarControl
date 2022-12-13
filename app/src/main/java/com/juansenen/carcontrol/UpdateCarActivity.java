package com.juansenen.carcontrol;

import static com.juansenen.carcontrol.db.Constans.DATABASE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
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
import com.juansenen.carcontrol.util.DatePickerFragment;

public class UpdateCarActivity extends AppCompatActivity implements View.OnClickListener{

    private String matricula;
    private TextView txtRegister;
    private EditText editTrade;
    private EditText editModel;
    private EditText editDate;
    private EditText editKm;
    private Cars car;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_car);

        Intent intent = getIntent();
        matricula = intent.getStringExtra("register");
        if (matricula == null)
            return;

        final AppDatabase db = Room.databaseBuilder(this,AppDatabase.class,DATABASE_NAME)
                .allowMainThreadQueries().build();
        car = db.carsDAO().getByRegister(matricula);

        txtRegister = findViewById(R.id.update_register);
        editTrade = findViewById(R.id.update_trademark);
        editModel = findViewById(R.id.update_model);
        editDate = findViewById(R.id.update_datebuy);
        editKm = findViewById(R.id.update_kms);

        txtRegister.setText(matricula);
        editTrade.setText(car.getTrademark());
        editModel.setText(car.getModel());
        editKm.setText(String.valueOf(car.getKm()));

        editDate.setText(car.getYear());
        editDate.setOnClickListener(this);

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

    public void buttonUpdateCar(View view){
        car.setTrademark(editTrade.getText().toString());
        car.setModel(editModel.getText().toString());
        car.setKm(Integer.parseInt(editKm.getText().toString()));
        car.setYear(editDate.getText().toString());

        //Dialogo asegurar modiicación
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.Modify_car_question)
                .setTitle(R.string.update_car_dialog_yes)
                .setPositiveButton(R.string.do_it, (dialog, id) -> {
                    final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                            .allowMainThreadQueries().build();
                    db.carsDAO().update(car);
                    Toast.makeText(this, R.string.Updated,Toast.LENGTH_LONG);
                    Intent intent= new Intent (this, MainActivity.class);
                    startActivity(intent);

                })
                .setNegativeButton((R.string.cancel), (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //Metodo onclick EditText fecha
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.update_datebuy:
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
                editDate.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }





}