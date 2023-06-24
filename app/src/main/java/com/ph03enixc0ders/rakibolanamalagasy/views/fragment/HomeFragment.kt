package com.ph03enixc0ders.rakibolanamalagasy.views.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ph03enixc0ders.rakibolanamalagasy.R
import com.ph03enixc0ders.rakibolanamalagasy.adapter.searchAdapter
import com.ph03enixc0ders.rakibolanamalagasy.databinding.FragmentHomeBinding
import com.ph03enixc0ders.rakibolanamalagasy.entity.teny
import com.ph03enixc0ders.rakibolanamalagasy.utils.utilities
import com.ph03enixc0ders.rakibolanamalagasy.viewmodels.tenyVM

class HomeFragment(var tenyId:Int):Fragment() {

    private var _binding:FragmentHomeBinding?=null
    private  val binding get()=_binding!!
    private  var isWordAddedToHistory = false
    private  var isObserving = false

    lateinit var _viewModel: tenyVM
    var _randomNumber:Int=0
    var countData:Int=0
    lateinit var _randomTeny:teny
    lateinit var data:teny;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentHomeBinding.inflate(inflater,container,false)
        return  binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initialize view
        initView()
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onStart() {
        super.onStart()
        this.binding.searchValidate.setOnClickListener {
            hideKeyboard()
            val value = this.binding.search.text.toString().trim()
                _viewModel.getListFilterByWord(value).observe(viewLifecycleOwner, Observer { result ->
                    if (result == null) {
                        Toast.makeText(context, "Miala tsiny ,tsy mbola voatahiry io teny io.", Toast.LENGTH_SHORT).show()
                    } else {
                         data = result

                        // TODO Check if the word has already been added to the history
                        if (!isWordAddedToHistory) {
                            val listId: MutableList<Int> = mutableListOf(data.id)
                            try {
                                Thread {
                                    _viewModel.addWordToHistory(listId)
                                }.start()
                                println("INSERTION OK")
                                isWordAddedToHistory = true // Set the flag to true to indicate that the word has been added
                            } catch (e: Exception) {
                                println("INSERTION KO ==> $e")
                            }
                        }
                        this.binding.randomWord.text = data.word
                        this.binding.definition.text = data.definition
                    }
                })
            }


        //
        this.onClick()


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    fun initView(){
        //HIDE KEYBOARD

        //initialize viewModel
        this._viewModel= ViewModelProvider(requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))[tenyVM::class.java]

        // Retrieve word by ID from the constructor
        // If the `tenyId` is not equal to 0, load the word that corresponds to this ID
        if(this.tenyId!=0){
                displayWordUI(this.tenyId)
        }
        else{
            // Generate a random number based on the countData
            this._viewModel.getAllList().observe(viewLifecycleOwner, Observer {
                    listTeny->

                countData=listTeny.size


                this.binding.wordNumber.text=getString(R.string.word_number) + " "+utilities.addSpace(countData.toString())

                // Generate a random number based on the countData
                this._randomNumber= utilities.getRandomNumber(countData)


                displayWordUI(this._randomNumber)

                //TODO AUTO COMPLETION

            })
        }




    }

    @SuppressLint("ServiceCast")
    private fun hideKeyboard() {
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.search.windowToken, 0)

    }


    /**
    Displays a word in the UI based on the given ID.
    Retrieves a word from the table using the provided ID and updates the UI with the word's information.
    The function ensures that the UI update is performed only once.
    @param ID The ID of the word to display.
     */
    private fun displayWordUI(ID:Int){
        // Get a word from the table based on the randomNumber
        this._viewModel.getWordById(ID).observe(viewLifecycleOwner, Observer { teny ->
            if(!isObserving){
                isObserving=true
                data=teny
                this._randomTeny = teny
                this.binding.randomWord.text=this._randomTeny.word
                this.binding.definition.text=this._randomTeny.definition
            }

        })
    }


    //Event

    fun onClick(){
            this.binding.mark.setOnClickListener{
                // Change the status of the work as marked
                var listWord:MutableList<Int> = mutableListOf()
                //Add the Id of the word to the list
                listWord+=data.id
                try{
                    Thread {
                        this._viewModel.markWordAsImportant(listWord)
                        Log.e("Update status","OK")
                    }.start()

                }
                catch (e:Exception){
                    Log.e("Update status","KO ${e.message}")
                }
            }
    }
}