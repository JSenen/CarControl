package com.juansenen.carcontrol.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.juansenen.carcontrol.domain.Cars;
import com.juansenen.carcontrol.domain.Fuel;

import java.util.List;

@Dao
public interface FuelDAO {

    @Query("SELECT * FROM repostajes WHERE idFuelCar = :matricula")
    List<Fuel>  getFuelByCar(String matricula);

    @Query("SELECT * FROM repostajes")
    List<Fuel> getAll();

    @Insert
    void insert(Fuel fuel);

    @Delete
    void delete(Fuel fuel);

    @Update
    void update(Fuel fuel);

}
