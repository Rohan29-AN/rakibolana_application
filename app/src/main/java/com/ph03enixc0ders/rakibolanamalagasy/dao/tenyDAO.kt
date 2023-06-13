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

   /* @Query("SELECT * from rakibolana where word like '%' || :keyword || '%'")
    fun getListByWord(keyword: String): LiveData<List<teny>>*/


    @Query("SELECT * from rakibolana where word COLLATE NOCASE LIKE :keyword ")
    fun getListByWord(keyword: String): LiveData<List<teny>>

    @Query("SELECT * from rakibolana where id= :id")
    fun getListById(id: Int): LiveData<teny>


    // Retrieves all words that are recently opened.
    // If the word is recently opened, the status is set to 1.
    @Query("SELECT * FROM rakibolana WHERE isRecentlyOpen = 1")
    fun getRecentlyOpenedWords(): LiveData<List<teny>>


    // Retrieves all words that are marked.
    // If the word is marked, the status is set to 1.
    @Query("SELECT * FROM rakibolana WHERE isMarked = 1")
    fun getMarkedWords(): LiveData<List<teny>>

    @Query("UPDATE rakibolana SET isMarked = :status WHERE id IN (:wordIds)")
    fun updateWordMarkedStatus(status: Int, wordIds: List<Int>)

    @Query("UPDATE rakibolana SET isRecentlyOpen = :status WHERE id IN (:wordIds)")
    fun updateWordRecentlyOpenedStatus(status: Int, wordIds: List<Int>)





}