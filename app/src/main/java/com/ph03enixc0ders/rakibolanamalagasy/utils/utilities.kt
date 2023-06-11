package com.ph03enixc0ders.rakibolanamalagasy.utils

import kotlin.random.Random

class utilities {

    companion object{

        fun getRandomNumber(max:Int):Int{
            return Random.nextInt(0,max)
        }
    }
}