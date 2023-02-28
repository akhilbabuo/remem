package com.blackbird.diary.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.blackbird.diary.R
import com.blackbird.diary.data.DiaryEntry
import com.blackbird.diary.databinding.FragmentDiaryBinding


class DiaryFragment : Fragment() {
    var binding : FragmentDiaryBinding? = null
    var adapter : DiaryListAdapter? = null
    private val mainFragment:MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiaryBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = DiaryListAdapter()
        binding?.apply {
            rvDiaryList.adapter = adapter
            btFloating.setOnClickListener {
                findNavController().navigate(R.id.action_diaryFragment_to_diaryEntryFragment)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        handleObservers()
    }

    fun handleObservers(){
        mainFragment.diaryList.observe(viewLifecycleOwner){ diaryList ->
            if (diaryList.isEmpty()){
                binding?.hasData = false
            }else{
                adapter?.setData(diaryList)
                binding?.hasData = true
            }
        }
    }

}