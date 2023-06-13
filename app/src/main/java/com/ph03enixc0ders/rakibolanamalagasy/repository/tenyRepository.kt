package com.ph03enixc0ders.rakibolanamalagasy.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.ph03enixc0ders.rakibolanamalagasy.database.appDatabase
import com.ph03enixc0ders.rakibolanamalagasy.entity.teny

class tenyRepository(context:Context) {

    var appDatabaseInstance:appDatabase

    //constructor

    init {
        this.appDatabaseInstance=appDatabase.getInstance(context)
    }


    //get all list

    fun getAllList():LiveData<List<teny>>{
        return this.appDatabaseInstance.tenyDAO().getListTeny()
    }

    //get list with filter
    fun getListFilterByWord(word:String):LiveData<teny>{
        return this.appDatabaseInstance.tenyDAO().getListByWord(word)
    }

    //get list by id
    fun getListById(id:Int):LiveData<teny>{
        return this.appDatabaseInstance.tenyDAO().getListById(id)
    }


    // Retrieves the list of recently opened words.
    fun getRecentlyOpenedWords(): LiveData<List<teny>> {
        return this.appDatabaseInstance.tenyDAO().getRecentlyOpenedWords()
    }

    // Retrieves the list of marked words.
    fun getMarkedWords(): LiveData<List<teny>> {
        return this.appDatabaseInstance.tenyDAO().getMarkedWords()
    }


    // Update word status to indicate recently opened
    fun updateWordRecentlyOpenedStatus(status: Int, wordIds: List<Int>) {
        return this.appDatabaseInstance.tenyDAO().updateWordRecentlyOpenedStatus(status, wordIds)
    }

    // Update word status to indicate marked
    fun updateWordMarkedStatus(status: Int, wordIds: List<Int>) {
        return this.appDatabaseInstance.tenyDAO().updateWordMarkedStatus(status, wordIds)
    }






}