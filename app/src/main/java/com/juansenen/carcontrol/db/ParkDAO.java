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
    @Query("SELECT * FROM parking WHERE idCarPark = :matricula")
    List<Park> getParkByCar(String matricula);

    @Query("SELECT * FROM parking")
    List<Park> getAll();

    @Query("SELECT * FROM parking WHERE idCarPark = :matricula ORDER BY id DESC LIMIT 1 ;")
    Park getLastPark(String matricula);

    @Insert
    void insert(Park park);

    @Delete
    void delete(Park park);

    @Update
    void update(Park park);
}
