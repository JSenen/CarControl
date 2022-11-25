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

public class DetalReviewActivity extends AppCompatActivity {

    private List<Reviews> reviewsList;
    private ReviewAdapter reviewAdapter;
    private String matricula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_review);

        Intent intent = getIntent();
        matricula = intent.getStringExtra("register");
        if (matricula == null)
            return;
        reviewsList = new ArrayList<>();
        TextView textView = findViewById(R.id.rcview_review_main_register);
        textView.setText(matricula);

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        reviewsList = db.reviewDAO().getReviewByCar(matricula);

        RecyclerView recyclerView = findViewById(R.id.rcview_review_main);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        reviewAdapter = new ReviewAdapter(this,reviewsList);
        recyclerView.setAdapter(reviewAdapter);

        registerForContextMenu(recyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final AppDatabase db = Room.databaseBuilder(this,AppDatabase.class,DATABASE_NAME)
                .allowMainThreadQueries().build();

        reviewsList.clear();
        reviewsList.addAll(db.reviewDAO().getReviewByCar(matricula));
        reviewAdapter.notifyDataSetChanged();
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