package com.ph03enixc0ders.rakibolanamalagasy.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ph03enixc0ders.rakibolanamalagasy.R
import com.ph03enixc0ders.rakibolanamalagasy.adapter.listAdapter
import com.ph03enixc0ders.rakibolanamalagasy.databinding.ActivityHistoricBinding
import com.ph03enixc0ders.rakibolanamalagasy.entity.teny
import com.ph03enixc0ders.rakibolanamalagasy.event.OnClickItemInterface
import com.ph03enixc0ders.rakibolanamalagasy.viewmodels.tenyVM

class Historic : AppCompatActivity(),OnClickItemInterface {
    lateinit var _binding: ActivityHistoricBinding
    lateinit var adapter: listAdapter
    lateinit var viewModel:tenyVM

    var listOfWordSelected= mutableSetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=ActivityHistoricBinding.inflate(layoutInflater)
        val view=_binding.root
        setContentView(view)

        initView()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_historic,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete->{
                if(this.listOfWordSelected.size>0)
                    this.viewModel.removeWordFromHistory(this.listOfWordSelected.toList())


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

        this.adapter= listAdapter(this, emptyList())
        this._binding.listView.adapter = this.adapter
        this.adapter.setOnItemCheckedChangeListener(this)
        this.adapter.setOnItemClickedListener(this)

        //INITIALIZE VIEW MODEL
        this.viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[tenyVM::class.java]

        this.viewModel.getRecentlyOpenedWords().observe(this, Observer {
                listTeny->
            if(listTeny.size!=0){
                //INITIALIZE LISTVIEW
                this.adapter.updateData(listTeny)
                this._binding.noResult.visibility= GONE
                this._binding.listView.adapter=this.adapter
                this._binding.listView.visibility= VISIBLE

            }
            else{
                this._binding.listView.visibility= GONE
                this._binding.noResult.visibility= VISIBLE
            }

        })


    }

    override fun onBackPressed() {
       // super.onBackPressed()
        val intent= Intent(this,com.ph03enixc0ders.rakibolanamalagasy.views.Menu::class.java)
        startActivity(intent)
        finish()
    }

    override fun onItemCheckedChanged(item: teny, isCheckBox: Boolean) {
        if(isCheckBox){
            this.listOfWordSelected.add(item.id)
        }
        else{
            this.listOfWordSelected.remove(item.id)
        }
    }

    override fun onItemClicked(item: teny) {
        val intent= Intent(this,com.ph03enixc0ders.rakibolanamalagasy.views.Menu::class.java)
        intent.putExtra("FROM","HISTORIC")
        intent.putExtra("TENY_ID",item.id)
        startActivity(intent)
        finish()
    }

}