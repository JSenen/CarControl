package com.juansenen.carcontrol;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission_group.CAMERA;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.ToString;

public class TakePhotoActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION_CAMERA = 100;
    private final String RUTA_IMAGEN = "Pictures"; //Carpeta Raiz Galeria de Imagenes
    private Button btnTakePhoto;
    private ImageView imageView;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);

        //Recuperamos los elementos del layout
        btnTakePhoto = findViewById(R.id.btnTakePhoto);
        imageView = findViewById(R.id.imgView_takephoto);

        //Creamos un listener del Boton cargar imagen
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Validación permisos camara
                validatePermisos();
                //Intent para delegar la función a la cámara
                takeCamara.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
                takePhoto();
            }
        });

    }
    //Intent recibe la fotografía
    ActivityResultLauncher<Intent> takeCamara = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode()==RESULT_OK){
                        MediaScannerConnection.scanFile(TakePhotoActivity.this, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(String s, Uri uri) {

                            }
                        });

                    }
                    //Pintamos la imagen el el imagenView de la Activity
                    Bundle extras = result.getData().getExtras();
                    Bitmap imgBitmap = (Bitmap) extras.get("data");
                    imageView.setImageBitmap(imgBitmap);

                    try {
                        saveImage(imgBitmap); //Guardamos imagen en galeria
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //SanckBar emergente nos indica que la foto se guardo en la galeria
                    Snackbar.make(imageView, R.string.img_added_to_galery,Snackbar.LENGTH_LONG).show();
                }
            });


    //Comprobar permisos para leer escribir
    private boolean validatePermisos() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            //Verifica permisos para Android 6.0+
            return true;
        }
        if ((checkSelfPermission(CAMERA)==PackageManager.PERMISSION_GRANTED)&&(checkSelfPermission(WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)){
            return true;
        }
        if ((shouldShowRequestPermissionRationale(CAMERA)) || (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE))){
            cargarDialogoRecomendacion();

        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);
        }
        return false;
    }
    //Metodo para gaurdar la imagen tomada
    private void saveImage(Bitmap bitmap) throws IOException {
        //Nombre del directorio
        String folderName = "Cars";
        //Nombre de la imagen por fecha y hora
        String imageName = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        //Formato de la imagen
        String mimeType = "image/jpeg";

        //Creamos elemento OutputStream (buffer) para pasar la imagen a bytes
        OutputStream fos;
        //Depende de la version de Android del dispositivo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //Para versiones de Android 9+
            ContentResolver resolver = getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, imageName);
            contentValues.put(MediaStore.Images.Media.MIME_TYPE, mimeType);
            contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/" + folderName);
            Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            fos = resolver.openOutputStream(imageUri);
        } else {
            String imagesDir = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                    .toString() + File.separator + folderName;

            //Crear el archivo de imagen
            File file = new File(imagesDir);

            if (!file.exists()) {
                if (!file.mkdirs()) {

                }
            }

            //Creamos archivo imagen con nombre y extension jpeg.
            File imageFile = new File(imagesDir, imageName + ".jpeg");
            //Creamos el archivo imagen de bytes
            fos = new FileOutputStream(imageFile);
        }
        //Comprimimos la iamgen a formato JPEG y calidad 100%
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        //vacía el flujo de salida y fuerza la escritura de los bytes de salida almacenados en el buffer
        fos.flush();
        //Cerramos el buffer
        fos.close();
    }

    //Metodo de recepcion de solicitud de permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==100){
            if (grantResults.length==2 && grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                btnTakePhoto.setEnabled(true);
            }
        }
    }
    //Dialogos para solicitar permisos
    private void cargarDialogoRecomendacion() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(TakePhotoActivity.this);
        dialogo.setTitle(R.string.permissions_uncheked);
        dialogo.setMessage(R.string.Check_permissions_for_use_app);
        dialogo.setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);
            }
        });
        dialogo.show();
    }
    //Metodo toma la imagen
    private void takePhoto(){
        File fileImagen = new File(Environment.getExternalStorageDirectory(), RUTA_IMAGEN);
        boolean isCreada = fileImagen.exists();
        String nombreImagen = "";
        if (isCreada == false) {
            isCreada = fileImagen.mkdirs();
        }
        if (isCreada == true) {
            nombreImagen = (System.currentTimeMillis()/100)+".jpeg";
        }
        path = Environment.getExternalStorageDirectory()+File.separator+RUTA_IMAGEN+File.separator+nombreImagen;
        File imagen = new File(path);

    }
    //Menus en la Action Bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back, menu);
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.actbar_back){
            //Al pulsar en action bar vuelve a pantalla añadir vehiculo
            Intent intent = new Intent(this, AddCarActivity.class);
            startActivity(intent);
            return true;
        }else if(item.getItemId() == R.id.item_home){
            //Al pulsar vuelve a la pantalla principal
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

}