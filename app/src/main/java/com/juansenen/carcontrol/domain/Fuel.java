package com.juansenen.carcontrol.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

//Damos nombre a la tabla y creamos la relación de 1:M entre coches y esta
@Entity(tableName = "repostajes",
        foreignKeys = @ForeignKey(entity = Cars.class,
                parentColumns = "register",
                childColumns = "idFuelCar"))

public class Fuel {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;
    //Este atributo es el que mantendra la relación entre las tablas
    @ColumnInfo
    private String idFuelCar;
    @ColumnInfo
    private float price;
    @ColumnInfo
    private float litres;
    @ColumnInfo
    private int Km;
    @ColumnInfo
    private float total;
    @ColumnInfo
    private String date;


    public Fuel(@NonNull long id,String idFuelCar, float price, float litres, int km, String date, float total) {
        this.id = id;
        this.idFuelCar = idFuelCar;
        this.price = price;
        this.litres = litres;
        this.Km = km;
        this.date = date;
        this.total = total;
    }

    public Fuel(String idFuelCar, float price, float litres, int km,String date, float total) {
        this.idFuelCar = idFuelCar;
        this.price = price;
        this.litres = litres;
        Km = km;
        this.total = total;
    }
    public Fuel(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public int getKm() {
        return Km;
    }

    public void setKm(int km) {
        Km = km;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
