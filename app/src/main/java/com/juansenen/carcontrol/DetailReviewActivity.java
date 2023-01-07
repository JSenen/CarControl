package com.juansenen.carcontrol;

import static com.juansenen.carcontrol.db.Constans.DATABASE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.juansenen.carcontrol.adapter.ReviewAdapter;
import com.juansenen.carcontrol.db.AppDatabase;
import com.juansenen.carcontrol.domain.Reviews;

import java.util.ArrayList;
import java.util.List;

public class DetailReviewActivity extends AppCompatActivity {

    private List<Reviews> reviewsList;
    private ReviewAdapter reviewAdapter;
    private String matricula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_review);

        //Recuperamos el vehiculo por el campo register
        Intent intent = getIntent();
        matricula = intent.getStringExtra("register");
        if (matricula == null)
            return;
        reviewsList = new ArrayList<>();

        TextView idCar = findViewById(R.id.reviews_idcar);
        //Pintamos la matricula en el campo
        idCar.setText(matricula);

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        //Obtenemos los datos del vehiculo seleccionado
        reviewsList = db.reviewDAO().getReviewByCar(matricula);

        //Recuperamos el RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rcview_review_main);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //Creamos el adapter para el el RecyclerView
        reviewAdapter = new ReviewAdapter(this,reviewsList);
        recyclerView.setAdapter(reviewAdapter);

        registerForContextMenu(recyclerView);
    }

    //Recuperamos datos al volver a la pantalla
    @Override
    protected void onResume() {
        super.onResume();
        final AppDatabase db = Room.databaseBuilder(this,AppDatabase.class,DATABASE_NAME)
                .allowMainThreadQueries().build();

        //Vaciamos los datos que hubiese
        reviewsList.clear();
        //Añadimos los nuevos datos de la BD
        reviewsList.addAll(db.reviewDAO().getReviewByCar(matricula));
        //Notificamos el cambio
        reviewAdapter.notifyDataSetChanged();
    }

    //Menu en la action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Volvemos a pantalla anterior al pulsar
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return true;
    }
}