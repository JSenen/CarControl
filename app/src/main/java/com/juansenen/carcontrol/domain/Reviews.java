package com.juansenen.carcontrol.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "revisiones",
        foreignKeys = @ForeignKey(entity = Cars.class,
                parentColumns = "register",
                childColumns = "idReviewCar"))
public class Reviews {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long id;
    @ColumnInfo
    public String idReviewCar;
    @ColumnInfo
    public String fecha;
    @ColumnInfo
    public int kmreview;
    @ColumnInfo
    public float price;
    @ColumnInfo
    public boolean oil;
    @ColumnInfo
    public boolean brakes;
    @ColumnInfo
    public boolean freeze;
    @ColumnInfo
    public boolean brakeLiquid;
    @ColumnInfo
    public boolean wheels;
    @ColumnInfo
    public boolean wipers;


    public Reviews(@NonNull long id, String idReviewCar, String fecha, int kmreview, float price,
                   boolean oil, boolean brakes, boolean freeze, boolean brakeLiquid,
                   boolean wheels, boolean wipers) {
        this.id = id;
        this.idReviewCar = idReviewCar;
        this.fecha = fecha;
        this.kmreview = kmreview;
        this.price = price;
        this.oil = oil;
        this.brakes = brakes;
        this.freeze = freeze;
        this.brakeLiquid = brakeLiquid;
        this.wheels = wheels;
        this.wipers = wipers;
    }
    public Reviews(String idReviewCar, String fecha, int kmreview, float price, boolean oil,
                   boolean brakes, boolean freeze, boolean brakeLiquid, boolean wheels,
                   boolean wipers) {
        this.idReviewCar = idReviewCar;
        this.fecha = fecha;
        this.kmreview = kmreview;
        this.price = price;
        this.oil = oil;
        this.brakes = brakes;
        this.freeze = freeze;
        this.brakeLiquid = brakeLiquid;
        this.wheels = wheels;
        this.wipers = wipers;
    }
    public Reviews(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdReviewCar() {
        return idReviewCar;
    }

    public void setIdReviewCar(String idReviewCar) {
        this.idReviewCar = idReviewCar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getKmreview() {
        return kmreview;
    }

    public void setKmreview(int kmreview) {
        this.kmreview = kmreview;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isOil() {
        return oil;
    }

    public void setOil(boolean oil) {
        this.oil = oil;
    }

    public boolean isBrakes() {
        return brakes;
    }

    public void setBrakes(boolean brakes) {
        this.brakes = brakes;
    }

    public boolean isFreeze() {
        return freeze;
    }

    public void setFreeze(boolean freeze) {
        this.freeze = freeze;
    }

    public boolean isBrakeLiquid() {
        return brakeLiquid;
    }

    public void setBrakeLiquid(boolean brakeLiquid) {
        this.brakeLiquid = brakeLiquid;
    }

    public boolean isWheels() {
        return wheels;
    }

    public void setWheels(boolean wheels) {
        this.wheels = wheels;
    }

    public boolean isWipers() {
        return wipers;
    }

    public void setWipers(boolean wipers) {
        this.wipers = wipers;
    }
}
