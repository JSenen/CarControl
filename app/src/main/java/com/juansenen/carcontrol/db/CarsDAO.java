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


    @Query("SELECT * FROM cars")
    List<Cars> getAll();

    @Query("SELECT * FROM cars WHERE register = :register")
    Cars getByRegister(String register);

    @Insert
    void insert(Cars cars);

    @Delete
    void delete(Cars cars);

    @Update
    void update(Cars cars);


}
