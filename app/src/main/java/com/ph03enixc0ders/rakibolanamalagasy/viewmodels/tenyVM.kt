package com.ph03enixc0ders.rakibolanamalagasy.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ph03enixc0ders.rakibolanamalagasy.entity.teny
import com.ph03enixc0ders.rakibolanamalagasy.repository.tenyRepository

class tenyVM(application: Application):AndroidViewModel(application) {

    var _tenyRepo:tenyRepository

   init {
       this._tenyRepo= tenyRepository(application)
   }


    fun getAllList():LiveData<List<teny>>{
        return this._tenyRepo.getAllList()
    }

    fun getListFilterByWord(word:String):LiveData<List<teny>>{
        return this._tenyRepo.getListFilterByWord(word)
    }

    fun getWordById(id:Int):LiveData<teny>{
        return this._tenyRepo.getListById(id)
    }

    // Returns LiveData representing the list of recently opened words
    fun getRecentlyOpenedWords(): LiveData<List<teny>> {
        return this._tenyRepo.getRecentlyOpenedWords()
    }

    // Returns LiveData representing the list of marked words
    fun getMarkedWords(): LiveData<List<teny>> {
        return this._tenyRepo.getMarkedWords()
    }



}