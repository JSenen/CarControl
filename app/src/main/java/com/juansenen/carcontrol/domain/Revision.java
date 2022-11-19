package com.juansenen.carcontrol.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Revision {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long revId;
    @ColumnInfo
    public String revCarId;
    @ColumnInfo
    public String fecha;
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

    public Revision(String revCarId, String fecha, boolean done, int kmrevision, boolean aceite,
                    boolean filtros, boolean ruedas, boolean discosfreno,
                    boolean anticongelante, boolean limpiaparabrisas) {
        this.revCarId = revCarId;
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

    public Revision() {

    }

    public long getRevId() {
        return revId;
    }

    public void setRevId(long revId) {
        this.revId = revId;
    }

    public String getRevCarId() {
        return revCarId;
    }

    public void setRevCarId(String revCarId) {
        this.revCarId = revCarId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getKmrevision() {
        return kmrevision;
    }

    public void setKmrevision(int kmrevision) {
        this.kmrevision = kmrevision;
    }

    public boolean isAceite() {
        return aceite;
    }

    public void setAceite(boolean aceite) {
        this.aceite = aceite;
    }

    public boolean isFiltros() {
        return filtros;
    }

    public void setFiltros(boolean filtros) {
        this.filtros = filtros;
    }

    public boolean isRuedas() {
        return ruedas;
    }

    public void setRuedas(boolean ruedas) {
        this.ruedas = ruedas;
    }

    public boolean isDiscosfreno() {
        return discosfreno;
    }

    public void setDiscosfreno(boolean discosfreno) {
        this.discosfreno = discosfreno;
    }

    public boolean isAnticongelante() {
        return anticongelante;
    }

    public void setAnticongelante(boolean anticongelante) {
        this.anticongelante = anticongelante;
    }

    public boolean isLimpiaparabrisas() {
        return limpiaparabrisas;
    }

    public void setLimpiaparabrisas(boolean limpiaparabrisas) {
        this.limpiaparabrisas = limpiaparabrisas;
    }
}
