package com.juansenen.carcontrol.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.juansenen.carcontrol.domain.Cars;
import com.juansenen.carcontrol.domain.Revision;

import java.util.List;

public class CarsWithRevision {

    @Embedded
    public Cars cars;

    @Relation(
            parentColumn = "car_id",
            entityColumn = "revision_registerId"
    )
    public List<Revision> revisiones;

}
