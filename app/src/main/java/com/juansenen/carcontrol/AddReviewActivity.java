package com.juansenen.carcontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.juansenen.carcontrol.domain.Reviews;

public class AddReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        //TODO Recibir matricula de la MainActivity
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

    public void butAddReview(View view){
        TextView txtRegister = findViewById(R.id.txt_review_register);
        EditText edtReviewDate = findViewById(R.id.edt_addreview_date);
        EditText edtReviewKm = findViewById(R.id.edt_addreview_km);
        EditText edtReviewPrice = findViewById(R.id.edt_addreview_price);
        CheckBox chkReviewOil = findViewById(R.id.chk_addreview_oil);
        CheckBox chkReviewBrakes = findViewById(R.id.chk_addreview_brakes);
        CheckBox chkReviewFreeze = findViewById(R.id.chk_addreview_freeze);
        CheckBox chkReviewLiqBrakes = findViewById(R.id.chk_addreview_liquidbrakes);
        CheckBox chkReviewWheels = findViewById(R.id.chk_addreview_wheels);
        CheckBox chkReviewWipers = findViewById(R.id.chk_addreview_wipers);

        String reviewRegister = txtRegister.getText().toString(); //TODO Añadir matricula vehiculo SQL
        String reviewDate = edtReviewDate.getText().toString();
        int reviewKm = Integer.parseInt(edtReviewKm.getText().toString());
        float reviewPrice = Float.parseFloat(edtReviewPrice.getText().toString());
        boolean reviewOil = chkReviewOil.isChecked();
        boolean reviewBrakes = chkReviewBrakes.isChecked();
        boolean reviewFreeze = chkReviewFreeze.isChecked();
        boolean reviewLiqBrakes = chkReviewLiqBrakes.isChecked();
        boolean reviewWheels = chkReviewWheels.isChecked();
        boolean reviewWipers = chkReviewWipers.isChecked();

        Reviews review = new Reviews(reviewRegister,reviewDate,reviewKm,reviewPrice,reviewOil,reviewBrakes,reviewFreeze,reviewLiqBrakes,reviewWheels,reviewWipers);

        //TODO Conectar a la BBDD

        Toast.makeText(this,"AÑADIDO",Toast.LENGTH_SHORT).show();

        //TODO Limpiar campos
    }
}