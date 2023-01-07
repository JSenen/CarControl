package com.juansenen.carcontrol.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Damos nombre a la tabla
@Entity(tableName = "cars")
public class Cars {

    //Establecemos llave primaria
    @PrimaryKey
    @NonNull
    public String register;
    @ColumnInfo
    public String trademark;
    @ColumnInfo
    public String model;
    @ColumnInfo
    public String year;
    @ColumnInfo
    public int km;
    @ColumnInfo
    //Atributo path de la imagen le damos por defecto valor 0
    public String imgPath = "";


    public Cars() {}

    public Cars(String register, String trademark, String model, String year, int km, String imgPath) {
        this.register = register;
        this.trademark = trademark;
        this.model = model;
        this.year = year;
        this.km = km;

    }

    public Cars(String trademark, String model, String year, int km, String imgPath) {
        this.trademark = trademark;
        this.model = model;
        this.year = year;
        this.km = km;

    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
