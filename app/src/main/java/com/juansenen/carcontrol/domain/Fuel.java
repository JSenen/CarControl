package com.juansenen.carcontrol.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Fuel {
    @PrimaryKey(autoGenerate = true)
    private long idFuel;
    @ColumnInfo
    private String idFuelCar;
    @ColumnInfo
    private float price;
    @ColumnInfo
    private float litres;

    public Fuel(){}

    public Fuel(String idFuelCar, float price, float litres) {
        this.idFuelCar = idFuelCar;
        this.price = price;
        this.litres = litres;
    }

    public long getIdFuel() {
        return idFuel;
    }

    public void setIdFuel(long idFuel) {
        this.idFuel = idFuel;
    }

    public String getIdFuelCar() {
        return idFuelCar;
    }

    public void setIdFuelCar(String idFuelCar) {
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
