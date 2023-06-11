package com.ph03enixc0ders.rakibolanamalagasy.utils

import kotlin.random.Random

class utilities {

    companion object{

        fun getRandomNumber(max:Int):Int{
            return Random.nextInt(0,max)
        }

        fun addSpace(value:String):String{
           var valueReversed=value.reversed()
            var result=""
           var index=0
           while (index<valueReversed.length){
               result+=valueReversed[index]
               if(index!=valueReversed.lastIndex && (index+1)%3==0){
                       result+=" "
               }
               index++
           }
            return result.reversed()
        }
    }
}