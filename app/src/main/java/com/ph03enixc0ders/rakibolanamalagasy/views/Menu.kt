package com.ph03enixc0ders.rakibolanamalagasy.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ph03enixc0ders.rakibolanamalagasy.R
import com.ph03enixc0ders.rakibolanamalagasy.databinding.ActivityMenuBinding
import com.ph03enixc0ders.rakibolanamalagasy.views.fragment.BookmarkFragment
import com.ph03enixc0ders.rakibolanamalagasy.views.fragment.HomeFragment
import com.ph03enixc0ders.rakibolanamalagasy.views.fragment.SettingFragment

class Menu : AppCompatActivity() {

    lateinit var _binding:ActivityMenuBinding
    lateinit var _currentFragment:Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this._binding=ActivityMenuBinding.inflate(layoutInflater)
        val view=this._binding.root
        setContentView(view)


        //INITIALIZE VIEW
        initView()
    }


    fun initView(){


        //initialize fragment
        //Display the homefragment by default
        this._currentFragment=HomeFragment(0)
        loadFragment(this._currentFragment)
        supportActionBar?.hide()
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
                    R.id.navigation_home->{
                        this._currentFragment=HomeFragment(0)
                        loadFragment(this._currentFragment)
                        supportActionBar?.hide()
                    }
                    R.id.navigation_history->{
                       val intent=Intent(this,Historic::class.java)
                        startActivity(intent)
                        finish()
                    }
                    R.id.navigation_bookmark->{
                        this._currentFragment=BookmarkFragment()
                        loadFragment(this._currentFragment)
                        supportActionBar?.show()
                    }
                    R.id.navigation_settings->{
                        this._currentFragment=SettingFragment()
                        loadFragment(this._currentFragment)
                        supportActionBar?.show()
                    }
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
            if (extraValue == "HISTORIC" || extraValue == "BOOKMARK") {
                // If the extraValue is "HISTORIC" or "BOOKMARK", retrieve the teny_id from the intent
                tenyId = intent.getIntExtra("TENY_ID", 0)
            }
        }
        // Load the HomeFragment with the provided tenyId
        loadFragment(HomeFragment(tenyId))
    }





}