package com.juansenen.carcontrol.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.juansenen.carcontrol.domain.Cars;

import java.util.List;

@Dao
public interface CarsDAO {

    //Obtener todos
    @Query("SELECT * FROM cars")
    List<Cars> getAll();
    //Obtener por matricula
    @Query("SELECT * FROM cars WHERE register = :register")
    Cars getByRegister(String register);
    //Añadir
    @Insert
    void insert(Cars cars);
    //Borrar
    @Delete
    void delete(Cars cars);
    //Actualizar
    @Update
    void update(Cars cars);


}
