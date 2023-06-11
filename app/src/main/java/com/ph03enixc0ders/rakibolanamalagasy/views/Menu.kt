package com.ph03enixc0ders.rakibolanamalagasy.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ph03enixc0ders.rakibolanamalagasy.R
import com.ph03enixc0ders.rakibolanamalagasy.databinding.ActivityMenuBinding
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
        loadFragment(HomeFragment())


        //this function is used to set up the navigation listener
        onMenuClicked()
    }

    override fun onStart() {
        super.onStart()
        //onMenuClicked()
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
                    R.id.navigation_home->loadFragment(HomeFragment())
                    R.id.navigation_history->loadFragment(HistoryFragment())
                    R.id.navigation_bookmark->loadFragment(BookmarkFragment())
                    R.id.navigation_settings->loadFragment(SettingFragment())
            }
            true
        }
    }






}