package com.ph03enixc0ders.rakibolanamalagasy.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class teny(
    @PrimaryKey
    @ColumnInfo(name="word")
    val word:String?,

    @ColumnInfo(name = "definition")
    val definition:String?
)