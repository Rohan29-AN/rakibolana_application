package com.ph03enixc0ders.rakibolanamalagasy.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ph03enixc0ders.rakibolanamalagasy.entity.teny

@Dao
interface tenyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeny(teny:teny);

    @Query("Select * from Rakibolana")
    fun getListTeny():LiveData<List<teny>>

    @Query("SELECT * from Rakibolana where word like %:keyword%")
    fun getListByWord(words:String):LiveData<List<teny>>;
}