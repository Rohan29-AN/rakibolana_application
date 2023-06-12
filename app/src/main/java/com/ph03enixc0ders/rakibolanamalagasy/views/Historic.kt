package com.ph03enixc0ders.rakibolanamalagasy.views

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import com.ph03enixc0ders.rakibolanamalagasy.R

class Historic : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historic)
        initView()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_historic,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete->{
                Toast.makeText(this,"Hello",Toast.LENGTH_LONG).show();
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }


    fun initView(){
        //CUSTOM ACTION BAR
        supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back)
            setDisplayShowTitleEnabled(true)
            setTitle(R.string.title_history)
        }
    }

    override fun onBackPressed() {
       // super.onBackPressed()
        val intent= Intent(this,com.ph03enixc0ders.rakibolanamalagasy.views.Menu::class.java)
        startActivity(intent)
        finish()
    }
}