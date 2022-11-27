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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.juansenen.carcontrol.db.AppDatabase;
import com.juansenen.carcontrol.domain.Fuel;
import com.juansenen.carcontrol.domain.Reviews;
import com.juansenen.carcontrol.util.DatePickerFragment;

public class AddReviewActivity extends AppCompatActivity implements View.OnClickListener {

    private String matricula;
    private EditText editTFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        Intent intent = getIntent();
        matricula = intent.getStringExtra("register");
        if (matricula == null)
            return;
        TextView txtregister = findViewById(R.id.txt_review_register);
        txtregister.setText(matricula);

        editTFecha = findViewById(R.id.edt_addreview_date);
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

    public void butAddReview(View view) {
        TextView txtRegister = findViewById(R.id.txt_review_register);
        EditText edtReviewKm = findViewById(R.id.edt_addreview_km);
        EditText edtReviewPrice = findViewById(R.id.edt_addreview_price);
        CheckBox chkReviewOil = findViewById(R.id.chk_addreview_oil);
        CheckBox chkReviewBrakes = findViewById(R.id.chk_addreview_brakes);
        CheckBox chkReviewFreeze = findViewById(R.id.chk_addreview_freeze);
        CheckBox chkReviewLiqBrakes = findViewById(R.id.chk_addreview_liquidbrakes);
        CheckBox chkReviewWheels = findViewById(R.id.chk_addreview_wheels);
        CheckBox chkReviewWipers = findViewById(R.id.chk_addreview_wipers);

        String reviewRegister = txtRegister.getText().toString();
        String reviewDate = editTFecha.getText().toString();
        int reviewKm = Integer.parseInt(edtReviewKm.getText().toString());
        float reviewPrice = Float.parseFloat(edtReviewPrice.getText().toString());
        boolean reviewOil = chkReviewOil.isChecked();
        boolean reviewBrakes = chkReviewBrakes.isChecked();
        boolean reviewFreeze = chkReviewFreeze.isChecked();
        boolean reviewLiqBrakes = chkReviewLiqBrakes.isChecked();
        boolean reviewWheels = chkReviewWheels.isChecked();
        boolean reviewWipers = chkReviewWipers.isChecked();

        Reviews review = new Reviews(reviewRegister, reviewDate, reviewKm, reviewPrice, reviewOil,
                reviewBrakes, reviewFreeze, reviewLiqBrakes, reviewWheels, reviewWipers);

        Reviews reviews = new Reviews(reviewRegister, reviewDate, reviewKm, reviewPrice, reviewOil
                , reviewBrakes, reviewFreeze, reviewLiqBrakes, reviewWheels, reviewWipers);
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        db.reviewDAO().insert(reviews);

        Toast.makeText(this, R.string.add, Toast.LENGTH_SHORT).show();

        editTFecha.setText("");
        edtReviewKm.setText("");
        edtReviewPrice.setText("");
        chkReviewOil.setChecked(false);
        chkReviewBrakes.setChecked(false);
        chkReviewFreeze.setChecked(false);
        chkReviewLiqBrakes.setChecked(false);
        chkReviewWheels.setChecked(false);
        chkReviewWipers.setChecked(false);

    }

    //Metodo onclick EditText fecha
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edt_addreview_date:
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