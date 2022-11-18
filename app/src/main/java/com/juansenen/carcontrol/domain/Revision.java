package com.juansenen.carcontrol.domain;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity
public class Revision {

    @PrimaryKey(autoGenerate = true)
    public long revision_id;
    @ColumnInfo
    public long revision_registerId;
    @ColumnInfo
    public long fecha;
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


    public Revision(long fecha, boolean done, int kmrevision, boolean aceite,
                    boolean filtros, boolean ruedas, boolean discosfreno,
                    boolean anticongelante, boolean limpiaparabrisas) {
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

    public long getRevision_id() {
        return revision_id;
    }

    public void setRevision_id(long revision_id) {
        this.revision_id = revision_id;
    }

    public long getRevision_registerId() {
        return revision_registerId;
    }

    public void setRevision_registerId(long revision_registerId) {
        this.revision_registerId = revision_registerId;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
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
