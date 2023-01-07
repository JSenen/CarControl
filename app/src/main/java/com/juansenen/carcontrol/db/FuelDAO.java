package com.juansenen.carcontrol.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.juansenen.carcontrol.domain.Fuel;

import java.util.List;

@Dao
public interface FuelDAO {

    //Obtener todos los repostajes por id
    @Query("SELECT * FROM repostajes WHERE idFuelCar = :matricula")
    List<Fuel>  getFuelByCar(String matricula);

    //Obtener todos
    @Query("SELECT * FROM repostajes")
    List<Fuel> getAll();
    //Añadir
    @Insert
    void insert(Fuel fuel);
    //Borrar
    @Delete
    void delete(Fuel fuel);
    //Actualizar
    @Update
    void update(Fuel fuel);

}
