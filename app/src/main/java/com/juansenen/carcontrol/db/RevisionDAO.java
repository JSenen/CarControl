package com.juansenen.carcontrol.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.juansenen.carcontrol.domain.Revision;

import java.util.List;


@Dao
public interface RevisionDAO {

    @Query("SELECT * FROM Revision")
    List<Revision> getAll();

    @Query("SELECT * FROM revision WHERE revCarId = :revCarId")
    List<Revision> getByRevCarId(String revCarId);

    @Insert
    void insert(Revision revision);

    @Delete
    void delete(Revision revision);

    @Update
    void update(Revision revision);


}
