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
    fun getListFilterByWord(word:String):LiveData<List<teny>>{
        return this.appDatabaseInstance.tenyDAO().getListByWord(word)
    }

    //get list by id
    fun getListById(id:Int):LiveData<teny>{
        return this.appDatabaseInstance.tenyDAO().getListById(id)
    }
}