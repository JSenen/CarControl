package com.juansenen.carcontrol.domain;

import android.accounts.OnAccountsUpdateListener;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Update;

@Entity(tableName = "cars")
public class Cars {

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



    public Cars() {}

    public Cars(String register, String trademark, String model, String year, int km) {
        this.register = register;
        this.trademark = trademark;
        this.model = model;
        this.year = year;
        this.km = km;
    }

    public Cars(String trademark, String model, String year, int km) {
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
}
