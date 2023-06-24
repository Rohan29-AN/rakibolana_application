package com.ph03enixc0ders.rakibolanamalagasy.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ph03enixc0ders.rakibolanamalagasy.R
import com.ph03enixc0ders.rakibolanamalagasy.databinding.FragmentHomeBinding
import com.ph03enixc0ders.rakibolanamalagasy.databinding.FragmentSettingBinding

class SettingFragment:Fragment() {

    private var _binding: FragmentSettingBinding?=null
    private  val binding get()=_binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentSettingBinding.inflate(inflater,container,false)

        val actionBar=(requireActivity() as AppCompatActivity).supportActionBar

        actionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setHomeAsUpIndicator(R.drawable.ic_back)
            setDisplayShowTitleEnabled(true)
            setTitle(R.string.title_settings)
        }


        return  binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}