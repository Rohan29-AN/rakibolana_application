package com.ph03enixc0ders.rakibolanamalagasy.views

import android.animation.LayoutTransition
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.ViewTreeObserver
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ph03enixc0ders.rakibolanamalagasy.R
import com.ph03enixc0ders.rakibolanamalagasy.databinding.ActivityMainBinding
import com.ph03enixc0ders.rakibolanamalagasy.viewmodels.tenyVM

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

        //Animate
        val imageLogo = findViewById<LinearLayout>(R.id.image_logo)
        imageLogo.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

        val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                // Remove the listener to avoid multiple calls
                imageLogo.viewTreeObserver.removeOnGlobalLayoutListener(this)

                // Start the animation
                val animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.fade_in)
                imageLogo.startAnimation(animation)

                // Delay and redirect to another activity
                val delayMillis = 3000L // Delay in milliseconds
                Handler().postDelayed({
                    val intent = Intent(this@MainActivity, Menu::class.java)
                    startActivity(intent)
                    finish()
                }, delayMillis)
            }
        }
        imageLogo.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)

        val animation2=AnimationUtils.loadAnimation(this,R.anim.slide_in_from_bottom)
        this._binding.proverbe.startAnimation(animation2)

    }
}