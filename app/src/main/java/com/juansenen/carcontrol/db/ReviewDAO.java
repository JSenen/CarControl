package com.juansenen.carcontrol.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.juansenen.carcontrol.domain.Reviews;

import java.util.List;

@Dao
public interface ReviewDAO {

    //Obtener por id
    @Query("SELECT * FROM revisiones WHERE idReviewCar = :matricula")
    List<Reviews> getReviewByCar(String matricula);
    //Obtener todos
    @Query("SELECT * FROM revisiones")
    List<Reviews> getAll();
    //Añadir
    @Insert
    void insert(Reviews review);
    //Borrar
    @Delete
    void delete(Reviews review);
    //Actualizar
    @Update
    void update(Reviews review);

}
