package com.juansenen.carcontrol.domain;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CarsWithFuel {

    @Embedded
    public Cars cars;

    @Relation(entity = Fuel.class, parentColumn = "register", entityColumn = "idFuelCar", projection = {"litres"})

    public List<Float> litresByCar;
}
