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
    private void saveImage(Bitmap bitmap) throws IOException {
        String folderName = "Cars";
        String imageName = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        String mimeType = "image/jpeg";

        OutputStream fos;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentResolver resolver = getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, imageName);
            contentValues.put(MediaStore.Images.Media.MIME_TYPE, mimeType);
            contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "DCIM/" + folderName);
            Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            fos = resolver.openOutputStream(imageUri);
        } else {
            String imagesDir = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                    .toString() + File.separator + folderName;

            File file = new File(imagesDir);

            if (!file.exists()) {
                if (!file.mkdirs()) {

                }
            }

            File imageFile = new File(imagesDir, imageName + ".jpeg");
            fos = new FileOutputStream(imageFile);
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        fos.flush();
        fos.close();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==100){
            if (grantResults.length==2 && grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                btnTakePhoto.setEnabled(true);
            }
        }
    }

    private void cargarDialogoRecomendacion() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(TakePhotoActivity.this);
        dialogo.setTitle("Permisos desactivados");
        dialogo.setMessage("Debe aceptar permisos para el correcto uso app");
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);
            }
        });
        dialogo.show();
    }

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent = new Intent(this, AddCarActivity.class);
        startActivity(intent);
        return false;
    }

}