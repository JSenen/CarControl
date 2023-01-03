package com.juansenen.carcontrol.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.juansenen.carcontrol.domain.Park;

import java.util.List;

@Dao
public interface ParkDAO {
    //Obtner por id
    @Query("SELECT * FROM parking WHERE idCarPark = :matricula")
    List<Park> getParkByCar(String matricula);
    //Obtener todos
    @Query("SELECT * FROM parking")
    List<Park> getAll();
    //Obtener por matricula el ultimo aparcamiento grabado
    @Query("SELECT * FROM parking WHERE idCarPark = :matricula ORDER BY id DESC LIMIT 1 ;")
    Park getLastPark(String matricula);
    //Añadir
    @Insert
    void insert(Park park);
    //Borrar
    @Delete
    void delete(Park park);
    //Actualizar
    @Update
    void update(Park park);
}
