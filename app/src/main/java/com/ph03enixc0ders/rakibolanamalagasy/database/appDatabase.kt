package com.ph03enixc0ders.rakibolanamalagasy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ph03enixc0ders.rakibolanamalagasy.dao.tenyDAO
import com.ph03enixc0ders.rakibolanamalagasy.entity.teny

@Database(entities = [teny::class], exportSchema = false, version = 1)
abstract class appDatabase:RoomDatabase() {

    companion object config{
        val DATABASE_NAME="rakibolana_data.db"
        var instance:appDatabase?=null
        var LOCK= Object()

        fun getInstance(context:Context):appDatabase{
            if(instance==null){
                synchronized(LOCK){
                    if(instance==null){
                        instance=Room.databaseBuilder(context.applicationContext,appDatabase::class.java,
                        DATABASE_NAME).createFromAsset("rakibolana.db").build();

                    }
                }
            }
            return instance!!
        }
    }

    abstract fun tenyDAO():tenyDAO;


}