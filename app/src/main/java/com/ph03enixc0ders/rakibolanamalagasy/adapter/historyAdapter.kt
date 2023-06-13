package com.ph03enixc0ders.rakibolanamalagasy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ph03enixc0ders.rakibolanamalagasy.R
import com.ph03enixc0ders.rakibolanamalagasy.entity.teny

class historyAdapter(val context: Context, private val tenyList:List<teny> ):BaseAdapter() {

    override fun getCount(): Int {
        return this.tenyList.size
    }

    override fun getItem(p0: Int): teny {
        return this.tenyList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view:View
        val viewHolder:ViewHolder

        if(p1===null){
            view=LayoutInflater.from(context).inflate(R.layout.item_history,p2,false)
            viewHolder=ViewHolder(view)
        }
        else{
            view=p1
            viewHolder=view.tag as ViewHolder
        }

        val teny=getItem(p0)
        viewHolder.word.text=teny.word
//        viewHolder.checkBox.setOnCheckedChangeListener{
//            _,isChecked->
//            if(isChecked){
//                Toast.makeText(context,"Checked: ${teny.word}",Toast.LENGTH_SHORT)
//            }
//            else{
//                Toast.makeText(context,"Unchecked: ${teny.word}",Toast.LENGTH_SHORT)
//            }
//        }
        return view!!
    }


    private class ViewHolder(view:View){
        val word:TextView =view.findViewById(R.id.word)
        val checkBox:CheckBox=view.findViewById(R.id.isChecked)
    }
}