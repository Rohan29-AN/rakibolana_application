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

    // Marks a specific word as "marked" by setting the isMarked field to 1.
    @Query("UPDATE rakibolana SET isMarked = 1 WHERE id = :wordId")
    fun markWordAsMarked(wordId: Int)

    // Updates the status of a specific word as "recently opened" by setting the isRecentlyOpen field to 1.
    @Query("UPDATE rakibolana SET isRecentlyOpen = 1 WHERE id = :wordId")
    fun updateWordAsRecentlyOpened(wordId: Int)




}