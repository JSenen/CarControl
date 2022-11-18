package com.juansenen.carcontrol.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cars {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long car_id;
    @ColumnInfo
    public String register;
    @ColumnInfo
    public String trademark;
    @ColumnInfo
    public String model;
    @ColumnInfo
    public int year;
    @ColumnInfo
    public int km;

    @Embedded
    public Revision revision;

    public Cars() {}

    public Cars(String register, String trademark, String model, int year, int km) {
        this.register = register;
        this.trademark = trademark;
        this.model = model;
        this.year = year;
        this.km = km;
    }

    public long getCar_id() {
        return car_id;
    }

    public void setCar_id(long car_id) {
        this.car_id = car_id;
    }

    public Revision getRevision() {
        return revision;
    }

    public void setRevision(Revision revision) {
        this.revision = revision;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }
}
