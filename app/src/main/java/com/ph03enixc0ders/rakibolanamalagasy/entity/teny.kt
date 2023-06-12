package com.ph03enixc0ders.rakibolanamalagasy.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rakibolana")
data class teny(
    @ColumnInfo(name="id")
    @PrimaryKey(autoGenerate = true)
    val id:Int,


    @ColumnInfo(name="word")
    val word:String,

    @ColumnInfo(name = "definition")
    val definition:String,

    @ColumnInfo(name="isRecentlyOpen")
    val isRecentlyOpen:Int,

    @ColumnInfo(name="isMarked")
    val isMarked:Int
)