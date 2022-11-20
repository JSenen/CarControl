package com.juansenen.carcontrol.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "repostajes",
        foreignKeys = @ForeignKey(entity = Cars.class,
                parentColumns = "register",
                childColumns = "idFuelCar"))

public class Fuel {
    @PrimaryKey
    @NonNull
    private String idFuelCar;
    @ColumnInfo
    private float price;
    @ColumnInfo
    private float litres;


    public Fuel(@NonNull String idFuelCar, float price, float litres) {
        this.idFuelCar = idFuelCar;
        this.price = price;
        this.litres = litres;
    }


    @NonNull
    public String getIdFuelCar() {
        return idFuelCar;
    }

    public void setIdFuelCar(@NonNull String idFuelCar) {
        this.idFuelCar = idFuelCar;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getLitres() {
        return litres;
    }

    public void setLitres(float litres) {
        this.litres = litres;
    }
}
