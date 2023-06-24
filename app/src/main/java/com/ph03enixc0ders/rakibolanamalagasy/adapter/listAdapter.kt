package com.ph03enixc0ders.rakibolanamalagasy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.ph03enixc0ders.rakibolanamalagasy.R
import com.ph03enixc0ders.rakibolanamalagasy.entity.teny
import com.ph03enixc0ders.rakibolanamalagasy.event.OnClickItemInterface

class listAdapter(val context: Context, private var tenyList: List<teny>) : BaseAdapter() {

    private var onItemCheckedChangeListener: OnClickItemInterface? = null
    private var onItemClickedListener: OnClickItemInterface? = null

    override fun getCount(): Int {
        return this.tenyList.size
    }

    override fun getItem(position: Int): teny {
        return this.tenyList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val teny = getItem(position)
        viewHolder.word.text = teny.word

        viewHolder.checkBox.setOnCheckedChangeListener{ _,isChecked->
           onItemCheckedChangeListener?.onItemCheckedChanged(teny,isChecked)
        }

        view.setOnClickListener{
            onItemClickedListener?.onItemClicked(teny)
        }

        return view
    }

    private class ViewHolder(view: View) {
        val word: TextView = view.findViewById(R.id.word)
        val checkBox: CheckBox = view.findViewById(R.id.isChecked)
    }

    fun updateData(newData: List<teny>) {
        this.tenyList = newData
        notifyDataSetChanged()
    }



    fun setOnItemCheckedChangeListener(listener: OnClickItemInterface) {
        this.onItemCheckedChangeListener = listener
    }

    fun setOnItemClickedListener(listener: OnClickItemInterface){
        onItemClickedListener=listener
    }
}
