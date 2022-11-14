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

    @Query("SELECT * FROM Cars")
    List<Cars> getAll();

    @Insert
    void insert(Cars cars);

    @Delete
    void delete(Cars cars);

    @Update
    void update(Cars cars);
}
