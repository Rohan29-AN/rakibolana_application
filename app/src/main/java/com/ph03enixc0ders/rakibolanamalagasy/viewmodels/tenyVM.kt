package com.ph03enixc0ders.rakibolanamalagasy.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ph03enixc0ders.rakibolanamalagasy.entity.teny
import com.ph03enixc0ders.rakibolanamalagasy.repository.tenyRepository
import java.util.concurrent.Future

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

    /**
     * Adds the specified word to the history by updating its status as recently opened.
     * @param wordIds List of word IDs to be added to the history.
     */
    fun addWordToHistory(wordIds: List<Int>) {
        _tenyRepo.updateWordRecentlyOpenedStatus(1, wordIds)
    }

    /**
     * Removes the specified word from the history by updating its status as not recently opened.
     * @param wordIds List of word IDs to be removed from the history.
     */
    fun removeWordFromHistory(wordIds: List<Int>) {
        _tenyRepo.updateWordRecentlyOpenedStatus(0, wordIds)
    }

    /**
     * Marks the specified word as important by updating its status as marked.
     * @param wordIds List of word IDs to be marked as important.
     */
    fun markWordAsImportant(wordIds: List<Int>) {
        _tenyRepo.updateWordMarkedStatus(1, wordIds)
    }

    /**
     * Removes the mark from the specified word by updating its status as not marked.
     * @param wordIds List of word IDs to remove the mark from.
     */
    fun removeMarkFromWord(wordIds: List<Int>) {
        _tenyRepo.updateWordMarkedStatus(0, wordIds)
    }




}