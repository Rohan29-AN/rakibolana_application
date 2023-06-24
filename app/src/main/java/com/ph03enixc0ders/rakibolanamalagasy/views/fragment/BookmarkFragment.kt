package com.ph03enixc0ders.rakibolanamalagasy.views.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ph03enixc0ders.rakibolanamalagasy.R
import com.ph03enixc0ders.rakibolanamalagasy.adapter.listAdapter
import com.ph03enixc0ders.rakibolanamalagasy.databinding.FragmentBookmarkBinding
import com.ph03enixc0ders.rakibolanamalagasy.databinding.FragmentHomeBinding
import com.ph03enixc0ders.rakibolanamalagasy.entity.teny
import com.ph03enixc0ders.rakibolanamalagasy.event.OnClickItemInterface
import com.ph03enixc0ders.rakibolanamalagasy.viewmodels.tenyVM
import com.ph03enixc0ders.rakibolanamalagasy.views.Menu

class BookmarkFragment:Fragment() ,OnClickItemInterface{
    private var _binding: FragmentBookmarkBinding?=null
    private  val binding get()=_binding!!

    lateinit var adapter:listAdapter

    var listOfWordSelected= mutableListOf<Int>()

    lateinit var viewModel:tenyVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentBookmarkBinding.inflate(inflater,container,false)


        val actionBar=(requireActivity() as AppCompatActivity).supportActionBar

        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back)
            setDisplayShowTitleEnabled(true)
            setTitle(R.string.title_bookmark)
        }

        return  binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onStart() {
        super.onStart()

        onClick()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
    
    
    
    fun initView(){
        this.adapter= listAdapter(requireContext(), emptyList())
        this.binding.listView.adapter = this.adapter
        this.adapter.setOnItemCheckedChangeListener(this)
        this.adapter.setOnItemClickedListener(this)

        //INITIALIZE VIEW MODEL
        this.viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))[tenyVM::class.java]

        //Get list of word marked
        this.viewModel.getMarkedWords().observe(viewLifecycleOwner, Observer{
            markedWords->
            if(!markedWords.isEmpty()){
               this.adapter.updateData(markedWords)
                this.binding.noResult.visibility= View.GONE
                this.binding.listView.adapter=this.adapter
                this.binding.listView.visibility= View.VISIBLE
                this.binding.unmark.visibility=View.VISIBLE
            }
            else{
                this.binding.listView.visibility= View.GONE
                this.binding.noResult.visibility= View.VISIBLE
                this.binding.unmark.visibility=View.GONE
            }

        })

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
        val intent= Intent(requireContext(),com.ph03enixc0ders.rakibolanamalagasy.views.Menu::class.java)
        intent.putExtra("FROM","BOOKMARK")
        intent.putExtra("TENY_ID",item.id)
        startActivity(intent)
    }

    fun onClick(){
        this.binding.unmark.setOnClickListener{
            if(this.listOfWordSelected.size>0){
                this.viewModel.removeMarkFromWord(this.listOfWordSelected.toList())
            }
            else{
                Toast.makeText(requireContext(),R.string.listEmpty,Toast.LENGTH_SHORT).show()
            }


        }
    }

}