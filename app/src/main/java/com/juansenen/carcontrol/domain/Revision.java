package com.juansenen.carcontrol.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity
public class Revision {

    @PrimaryKey(autoGenerate = true)
    public double id;
    @ColumnInfo
    public LocalDateTime fecha;
    @ColumnInfo
    public boolean done;
    @ColumnInfo
    public int kmrevision;
    @ColumnInfo
    public boolean aceite;
    @ColumnInfo
    public boolean filtros;
    @ColumnInfo
    public boolean ruedas;
    @ColumnInfo
    public boolean discosfreno;
    @ColumnInfo
    public boolean anticongelante;
    @ColumnInfo
    public boolean limpiaparabrisas;

    public Revision(LocalDateTime fecha, boolean done, int kmrevision, boolean aceite, boolean filtros, boolean ruedas, boolean discosfreno, boolean anticongelante, boolean limpiaparabrisas) {
        this.fecha = fecha;
        this.done = done;
        this.kmrevision = kmrevision;
        this.aceite = aceite;
        this.filtros = filtros;
        this.ruedas = ruedas;
        this.discosfreno = discosfreno;
        this.anticongelante = anticongelante;
        this.limpiaparabrisas = limpiaparabrisas;
    }
}
