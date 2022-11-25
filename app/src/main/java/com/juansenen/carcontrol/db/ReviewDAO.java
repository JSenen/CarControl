package com.juansenen.carcontrol.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.juansenen.carcontrol.domain.Fuel;
import com.juansenen.carcontrol.domain.Reviews;

import java.util.List;

@Dao
public interface ReviewDAO {

    @Query("SELECT * FROM revisiones WHERE idReviewCar = :matricula")
    List<Reviews> getReviewByCar(String matricula);

    @Query("SELECT * FROM revisiones")
    List<Reviews> getAll();

    @Insert
    void insert(Reviews review);

    @Delete
    void delete(Reviews review);

    @Update
    void update(Reviews review);

}
