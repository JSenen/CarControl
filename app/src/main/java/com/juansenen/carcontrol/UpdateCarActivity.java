package com.juansenen.carcontrol;

import static com.juansenen.carcontrol.db.Constans.DATABASE_NAME;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.Manifest;
import android.app.AlertDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
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
    private ImageView imageCar;
    private Button buttonCargarImg;
    private Cars car;
    public Uri pathImagen;
    public String pathReal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_car);

        //Recuperamos el vehículo por el atributo register
        Intent intent = getIntent();
        matricula = intent.getStringExtra("register");
        if (matricula == null)
            return;

        //Obtenemos vehículo de la BD
        final AppDatabase db = Room.databaseBuilder(this,AppDatabase.class,DATABASE_NAME)
                .allowMainThreadQueries().build();
        car = db.carsDAO().getByRegister(matricula);

        //Obtenemos los elementos del layout de la Activity
        txtRegister = findViewById(R.id.update_register);
        editTrade = findViewById(R.id.update_trademark);
        editModel = findViewById(R.id.update_model);
        editDate = findViewById(R.id.update_datebuy);
        editKm = findViewById(R.id.update_kms);
        imageCar = findViewById(R.id.imagemId_updatecar);
        //Boton para caragr imagen de la galeria del dispositivo
        buttonCargarImg = findViewById(R.id.btnCargarImg_updateCar);

        //Creamos un click listener para el boton cargar imagen
        buttonCargarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    //Verifica permisos para Android 6.0+
                    checkStoragePermission();
                }
                //Lanzamos un intent para coger la imagen de la galeria
                cameraLaunch.launch(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
            }
        });

        //Colocamos los datos del vehículo en los campos de texto
        txtRegister.setText(matricula);
        editTrade.setText(car.getTrademark());
        editModel.setText(car.getModel());
        editKm.setText(String.valueOf(car.getKm()));
        //El campo de la imagen lo rellenamos con la URI de la imagen
        imageCar.setImageURI(Uri.parse(car.getImgPath()));

        editDate.setText(car.getYear());
        //Listener en el campo fecha para abrir el fragment en caso de cambiar la fecha
        editDate.setOnClickListener(this);

    }
    //Metodo para recibir la ruta de la imagen al pulsar el botón
    ActivityResultLauncher<Intent> cameraLaunch = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==RESULT_OK){
                        //Obtenemos el path de la imagen en la galeria
                        pathImagen = result.getData().getData();
                        //Mostramos la imagen en el campo imagen
                        imageCar.setImageURI(pathImagen);
                        //Obtenemos el path de la imagen
                        pathReal = getRealPathFromURI(pathImagen);
                        //añadimos la ruta al campo de la BD
                        car.setImgPath(pathReal);
                    }
                }
            });
    //Comprobar permisos para leer escribir
    private void checkStoragePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 225);
        } else {

        }
    }
    //Obtener la path real  String de la imagen
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
    //Menus en la action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back,menu);
        return true;
    }

    //Seleccion de opciones en la action bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.actbar_back) {
            // Intent a la Activity principal
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
    //Metodo al pulsar boton actualizar coche
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
                    Snackbar.make(imageCar,R.string.Updated,Snackbar.LENGTH_LONG).show();
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