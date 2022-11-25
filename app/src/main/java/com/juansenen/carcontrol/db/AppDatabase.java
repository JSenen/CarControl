package com.juansenen.carcontrol.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.juansenen.carcontrol.domain.Cars;
import com.juansenen.carcontrol.domain.Fuel;
import com.juansenen.carcontrol.domain.Reviews;

@Database(entities = {Cars.class, Fuel.class, Reviews.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CarsDAO carsDAO();
    public abstract FuelDAO fuelDAO();
    public abstract ReviewDAO reviewDAO();
}
