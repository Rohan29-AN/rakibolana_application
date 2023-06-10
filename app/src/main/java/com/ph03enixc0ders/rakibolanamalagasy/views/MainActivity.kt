package com.ph03enixc0ders.rakibolanamalagasy.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ph03enixc0ders.rakibolanamalagasy.R
import com.ph03enixc0ders.rakibolanamalagasy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var _binding:ActivityMainBinding

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

        //
    }
}