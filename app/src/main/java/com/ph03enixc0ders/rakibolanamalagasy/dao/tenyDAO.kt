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

    @Query("Select * from rakibolana")
    fun getListTeny():LiveData<List<teny>>

    @Query("SELECT * from rakibolana where word like '%' || :keyword || '%'")
    fun getListByWord(keyword: String): LiveData<List<teny>>


    @Query("SELECT * from rakibolana where id= :id")
    fun getListById(id: Int): LiveData<teny>

}