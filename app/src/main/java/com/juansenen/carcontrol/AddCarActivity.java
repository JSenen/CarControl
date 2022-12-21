package com.juansenen.carcontrol;

import static com.juansenen.carcontrol.db.Constans.DATABASE_NAME;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.juansenen.carcontrol.db.AppDatabase;
import com.juansenen.carcontrol.domain.Cars;
import com.juansenen.carcontrol.util.DatePickerFragment;

import java.io.File;

public class AddCarActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTFecha;
    private String rutaImagen;
    public ImageView imgCarga;
    public Button btnLoadImg;
    public Uri pathImagen;
    public File savedImagen;
    public String pathReal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        btnLoadImg = findViewById(R.id.btnCargarImg);
        imgCarga = findViewById(R.id.imagemId);


        //Creamos un listener del Boton cargar imagen
        btnLoadImg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    //Verifica permisos para Android 6.0+
                    checkExternalStoragePermission();
                }
                camaraLauncher.launch(new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI));

            }
        });

        editTFecha = findViewById(R.id.edtxt_year);
        editTFecha.setOnClickListener(this);

    }

    //Metodo para recibir la ruta de la imagen al pulsar el botón
    ActivityResultLauncher<Intent> camaraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==RESULT_OK){
                        pathImagen = result.getData().getData();
                        imgCarga.setImageURI(pathImagen);
                        pathReal = getRealPathFromURI(pathImagen);
                    }
                }
            });

    //Comprobar permisos para leer escribir
    private void checkExternalStoragePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 225);
        } else {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back, menu);
        getMenuInflater().inflate(R.menu.menu_takephoto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.actbar_photo) {
            Intent intent = new Intent(this, TakePhotoActivity.class);
            startActivity(intent);
            return true;
        }else if (item.getItemId() == R.id.actbar_back) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return false;
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
        //Si no se introducen km, se asigna 0
        if (km <= 0){
            km = 0;
        }

        Cars car = new Cars(register, trdmark, model, year, km, pathReal);
        car.setImgPath(pathReal);

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Uri path = data.getData();
            imgCarga.setImageURI(path);
            rutaImagen = getRealPathFromURI(path);
            savedImagen = new File(rutaImagen);
        }
    }

    private String getRealPathFromURI(Uri path) {
        String result;
        Cursor cursor = getContentResolver().query(path, null, null,null,null );
        if(cursor == null ){
            result = path.getPath();
        }else{
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
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