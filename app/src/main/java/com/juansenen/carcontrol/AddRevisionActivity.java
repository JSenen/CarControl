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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.juansenen.carcontrol.db.AppDatabase;
import com.juansenen.carcontrol.domain.Revision;


public class AddRevisionActivity extends AppCompatActivity {

    public String idcar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_revision);

        Intent intent = getIntent();
        String register = intent.getStringExtra("register");
        if (register == null){
            return;
        }

        idcar = register;

    }

    public void addRev(View view){

        EditText edtxtkm = findViewById(R.id.edtxt_addrev_km);
        EditText edtdate = findViewById(R.id.edtxt_addrev_date);
        CheckBox chkoil = findViewById(R.id.chckbox_addrev_oil);
        CheckBox chkfilter = findViewById(R.id.chck_addrev_filter);
        CheckBox chkwheel = findViewById(R.id.chckbox_addrev_wheels);
        CheckBox chkbrake = findViewById(R.id.chck_addrev_brakedisc);
        CheckBox chkwindow = findViewById(R.id.chck_addrev_windowclean);
        CheckBox chkfreeze = findViewById(R.id.chkc_addrev_anticongelante);

        //TODO Arreglar fechas. Modelo de datos

        int kmrev = Integer.parseInt(edtxtkm.getText().toString());
        String daterev = edtdate.getText().toString();
        Boolean oilrev = chkoil.isChecked();
        Boolean filterrev = chkfilter.isChecked();
        Boolean wheelrev = chkwheel.isChecked();
        Boolean brakerev = chkbrake.isChecked();
        Boolean windowrev = chkwindow.isChecked();
        Boolean frezzzerev = chkfreeze.isChecked();
        Boolean done = true;

        Revision revision = new Revision(idcar,daterev,done,kmrev,oilrev,filterrev,wheelrev,brakerev,frezzzerev,windowrev);

        final AppDatabase db = Room.databaseBuilder(this,AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        db.revisionDAO().insert(revision);

        Toast.makeText(this,"AÑADIDA",Toast.LENGTH_SHORT).show();

        edtxtkm.setText("");
        edtdate.setText("");
        chkoil.setChecked(false);
        chkfilter.setChecked(false);
        chkwheel.setChecked(false);
        chkbrake.setChecked(false);
        chkwindow.setChecked(false);
        chkfreeze.setChecked(false);

        edtxtkm.requestFocus();
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


}