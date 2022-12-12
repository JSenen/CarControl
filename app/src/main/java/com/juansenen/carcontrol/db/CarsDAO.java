package com.juansenen.carcontrol.db;

import static androidx.room.OnConflictStrategy.REPLACE;

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

    @Query("UPDATE cars SET trademark= :trade, model= :mode, year= :datebuy, km= :kms WHERE register= :register")
    void update(String trade,String mode,String datebuy,int kms,String register);

    @Update
    void update(Cars cars);


}
