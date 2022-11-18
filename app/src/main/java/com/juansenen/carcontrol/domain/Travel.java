package com.juansenen.carcontrol.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Travel {


    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo
    public String origen;
    @ColumnInfo
    public double latorigen;
    @ColumnInfo
    public double longorigen;
    @ColumnInfo
    public String destino;
    @ColumnInfo
    public double latdestino;
    @ColumnInfo
    public double longdestino;
    @ColumnInfo
    public double distancia;

    public Travel(){

    }

    public Travel(String origen, double latorigen, double longorigen, String destino, double latdestino, double longdestino, double distancia) {

        this.origen = origen;
        this.latorigen = latorigen;
        this.longorigen = longorigen;
        this.destino = destino;
        this.latdestino = latdestino;
        this.longdestino = longdestino;
        this.distancia = distancia;
    }
}
