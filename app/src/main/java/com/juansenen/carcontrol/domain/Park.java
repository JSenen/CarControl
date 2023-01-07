package com.juansenen.carcontrol.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

//Damos nombre a la tabla y establecemos la relación 1:M entre coches y esta
@Entity(tableName = "parking",
        foreignKeys = @ForeignKey(entity = Cars.class,
                parentColumns = "register",
                childColumns = "idCarPark"))
public class Park {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long id;
    @ColumnInfo
    //Atributo mantiene la relacion entre las tablas
    public String idCarPark;
    @ColumnInfo
    public double latitude;
    @ColumnInfo
    public double longitude;


    public Park(){}

    public Park(@NonNull long id, String idCarPark, double latitude, double longitude) {
        this.id = id;
        this.idCarPark = idCarPark;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public Park(String idCarPark, double latitude, double longitude) {
        this.idCarPark = idCarPark;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdCarPark() {
        return idCarPark;
    }

    public void setIdCarPark(String idCarPark) {
        this.idCarPark = idCarPark;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
