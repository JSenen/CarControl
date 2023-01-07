package com.juansenen.carcontrol.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.juansenen.carcontrol.domain.Cars;
import com.juansenen.carcontrol.domain.Fuel;
import com.juansenen.carcontrol.domain.Park;
import com.juansenen.carcontrol.domain.Reviews;

//Indicamos las clases para la base de datos y la version de la base.
//Aumentamos la version segun modifiquemos aspectos de la base

@Database(entities = {Cars.class, Fuel.class, Reviews.class, Park.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    //Un DAO por cada clase
    public abstract CarsDAO carsDAO();
    public abstract FuelDAO fuelDAO();
    public abstract ReviewDAO reviewDAO();
    public abstract ParkDAO parkDAO();
}
