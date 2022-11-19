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

    @Query("SELECT * FROM fuel")
    List<Fuel> getAllFuel();

    @Insert
    void insert(Fuel fuel);

    @Delete
    void delete(Fuel fuel);

    @Update
    void update(Fuel fuel);

}
