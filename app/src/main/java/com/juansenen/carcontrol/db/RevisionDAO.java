package com.juansenen.carcontrol.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;


import com.juansenen.carcontrol.domain.Cars;
import com.juansenen.carcontrol.domain.Revision;

import java.util.List;


@Dao
public interface RevisionDAO {

    @Query("SELECT * FROM revision")
    List<Revision> getAll();

    @Insert
    void insert(Revision revision);

    @Delete
    void delete(Revision revision);

    @Update
    void update(Revision revision);


}
