package com.juansenen.carcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.juansenen.carcontrol.domain.Revision;

public class AddRevisionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_revision);

        Intent intent = getIntent();
        String register = intent.getStringExtra("car_id");
        if (register == null){
            return;
        }
        //TODO Terminar Añadir revision



    }
    public void addRev(View view){
        EditText edtxtkm = findViewById(R.id.edtxt_addrev_km);
        EditText edtdate = findViewById(R.id.edtxt_addrev_date);
        CheckBox chkoil = findViewById(R.id.chckbox_addrev_oil);

        String kmrev = edtxtkm.getText().toString();
        String daterev = edtdate.getText().toString();
        Boolean oilrev = chkoil.isChecked();

    }

}