package com.juansenen.carcontrol.domain;


import androidx.room.Embedded;
import androidx.room.Relation;


import java.util.List;

public class CarsAndRevision {
    @Embedded
    public Cars car;
    @Relation(
            parentColumn = "car_id",
            entityColumn = "revCarId"
    )
    public List<Revision> revisionList;


}
