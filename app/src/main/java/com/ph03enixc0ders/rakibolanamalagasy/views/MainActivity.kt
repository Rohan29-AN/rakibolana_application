package com.ph03enixc0ders.rakibolanamalagasy.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ph03enixc0ders.rakibolanamalagasy.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    override fun onStart() {
        super.onStart()


    }

    fun initView(){
        this.supportActionBar?.hide();
    }
}