package com.ph03enixc0ders.rakibolanamalagasy.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ph03enixc0ders.rakibolanamalagasy.R
import com.ph03enixc0ders.rakibolanamalagasy.databinding.ActivityMenuBinding
import com.ph03enixc0ders.rakibolanamalagasy.entity.teny
import com.ph03enixc0ders.rakibolanamalagasy.utils.utilities
import com.ph03enixc0ders.rakibolanamalagasy.viewmodels.tenyVM
import com.ph03enixc0ders.rakibolanamalagasy.views.fragment.BookmarkFragment
import com.ph03enixc0ders.rakibolanamalagasy.views.fragment.HistoryFragment
import com.ph03enixc0ders.rakibolanamalagasy.views.fragment.HomeFragment
import com.ph03enixc0ders.rakibolanamalagasy.views.fragment.SettingFragment

class Menu : AppCompatActivity() {

    lateinit var _binding:ActivityMenuBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this._binding=ActivityMenuBinding.inflate(layoutInflater)
        val view=this._binding.root
        setContentView(view)


        //INITIALIZE VIEW
        initView()
    }


    fun initView(){

        //hide action bar
        this.supportActionBar?.hide()

        //initialize fragment
        //Display the homefragment by default
        loadFragment(HomeFragment(0))

        //this function is used to set up the navigation listener
        onMenuClicked()
    }

    override fun onStart() {
        super.onStart()
    }

    fun loadFragment(fragment: Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container,fragment)
        fragmentTransaction.commit()
    }

    fun onMenuClicked(){
        this._binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                    R.id.navigation_home->loadFragment(HomeFragment(0))
                    R.id.navigation_history->{
                       val intent=Intent(this,Historic::class.java)
                        startActivity(intent)
                        finish()
                    }
                    R.id.navigation_bookmark->loadFragment(BookmarkFragment())
                    R.id.navigation_settings->loadFragment(SettingFragment())
            }
            true
        }
    }


    override fun onResume() {
        super.onResume()
        val intent = getIntent()
        val extraValue = intent.getStringExtra("FROM")

        var tenyId:Int=0

        // Check if the extraValue is not null
        if (extraValue != null) {
            when(extraValue){
                "HISTORIC"->{
                    // If the extraValue is "HISTORIC", retrieve the teny_id from the intent
                    val tenyId = intent.getIntExtra("TENY_ID", 0)

                }
            }
        }
        // Load the HomeFragment with the provided tenyId
        loadFragment(HomeFragment(tenyId))
    }






}