package com.ph03enixc0ders.rakibolanamalagasy.views.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
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

class HomeFragment():Fragment() {

    private var _binding:FragmentHomeBinding?=null
    private  val binding get()=_binding!!


    lateinit var _viewModel: tenyVM
    var _randomNumber:Int=0
    var countData:Int=0
    lateinit var _randomTeny:teny



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

    override fun onStart() {
        super.onStart()
        this.binding.searchValidate.setOnClickListener{
            hideKeyboard()

            var value=this.binding.search.text.toString().trim();
            _viewModel.getListFilterByWord(value).observe(viewLifecycleOwner, Observer {
                    result->

                if(result.isEmpty()){
                    Toast.makeText(context, "Miala tsiny ,tsy mbola voatahiry io teny io.", Toast.LENGTH_SHORT).show()
                }
                else{
                    var data=result.get(0)
                    this.binding.randomWord.text=data.word
                    this.binding.definition.text=data.definition

                    // Add the current word to the history
                    var listId: MutableList<Int> = mutableListOf(data.id)
                    this._viewModel.addWordToHistory(listId)
                }

            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    fun initView(){
        //initialize viewModel
        this._viewModel= ViewModelProvider(requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))[tenyVM::class.java]

        // Generate a random number based on the countData
        this._viewModel.getAllList().observe(viewLifecycleOwner, Observer {
                listTeny->



            countData=listTeny.size


            this.binding.wordNumber.text=getString(R.string.word_number) + " "+utilities.addSpace(countData.toString())

            // Generate a random number based on the countData
            this._randomNumber= utilities.getRandomNumber(countData)

            // Get a word from the table based on the randomNumber
            this._viewModel.getWordById(this._randomNumber).observe(viewLifecycleOwner, Observer { teny ->
                this._randomTeny = teny
                this.binding.randomWord.text=this._randomTeny.word
                this.binding.definition.text=this._randomTeny.definition
            })


            //TODO AUTO COMPLETION

        })


    }

    @SuppressLint("ServiceCast")
    private fun hideKeyboard() {
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.search.windowToken, 0)

    }



}