package com.ph03enixc0ders.rakibolanamalagasy.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ph03enixc0ders.rakibolanamalagasy.R
import com.ph03enixc0ders.rakibolanamalagasy.databinding.ActivityMainBinding
import com.ph03enixc0ders.rakibolanamalagasy.viewmodels.tenyVM

class MainActivity : AppCompatActivity() {

    lateinit var _binding:ActivityMainBinding
    lateinit var _viewModel:tenyVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=ActivityMainBinding.inflate(layoutInflater)
        val view=_binding.root
        setContentView(view)

        //INITVIEW
        initView()
    }

    override fun onStart() {
        super.onStart()


    }

    fun initView(){
        //HIDE ACTION BAR
        this.supportActionBar?.hide();

        //INITIALIZE VIEWMODEL
        this._viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[tenyVM::class.java]


        this._viewModel.getAllList().observe(this, Observer {
            listTeny->
                val _wordLength="Word length"+listTeny.size
                _binding.wordNumber.setText(_wordLength)
        })
    }
}