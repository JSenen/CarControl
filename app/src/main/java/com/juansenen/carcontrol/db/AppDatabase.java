package com.juansenen.carcontrol.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.juansenen.carcontrol.domain.Cars;
import com.juansenen.carcontrol.domain.Fuel;

@Database(entities = {Cars.class, Fuel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CarsDAO carsDAO();
    public abstract FuelDAO fuelDAO();
}
