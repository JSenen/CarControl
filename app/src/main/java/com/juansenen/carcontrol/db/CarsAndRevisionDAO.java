package com.juansenen.carcontrol.db;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.juansenen.carcontrol.domain.CarsAndRevision;

import java.util.List;

@Dao
public interface CarsAndRevisionDAO {

    @Transaction
    @Query("SELECT * FROM cars")
    List<CarsAndRevision> getCarAndRevision();


}
