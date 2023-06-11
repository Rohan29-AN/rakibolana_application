package com.ph03enixc0ders.rakibolanamalagasy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.ph03enixc0ders.rakibolanamalagasy.R
import com.ph03enixc0ders.rakibolanamalagasy.entity.teny
import java.util.*
import kotlin.collections.ArrayList

class searchAdapter(val context: Context, private val tenyList: List<teny>) : BaseAdapter(),
    Filterable {

    private val filteredList = ArrayList<teny>()

    override fun getCount(): Int {
        return filteredList.size
    }

    override fun getItem(position: Int): teny {
        return filteredList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_suggestion, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val tenyObject = getItem(position)
        viewHolder.wordTextView.text = tenyObject.word

        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                filteredList.clear()

                if (!constraint.isNullOrEmpty()) {
                    val filterPattern = constraint.toString().toLowerCase(Locale.getDefault())
                    for (tenyObject in tenyList) {
                        if (tenyObject.word.toLowerCase(Locale.getDefault()).startsWith(filterPattern)) {
                            filteredList.add(tenyObject)
                        }
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredList
                filterResults.count = filteredList.size
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results != null && results.count > 0) {
                    filteredList.clear()
                    filteredList.addAll(results.values as ArrayList<teny>)
                    notifyDataSetChanged()
                } else {
                    notifyDataSetInvalidated()
                }
            }
        }
    }

    private class ViewHolder(view: View) {
        val wordTextView: TextView = view.findViewById(R.id.word)
    }
}