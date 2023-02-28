package com.blackbird.diary.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.blackbird.diary.R
import com.blackbird.diary.data.DiaryEntry
import com.blackbird.diary.databinding.FragmentDiaryEntryBinding

class DiaryEntryFragment : Fragment() {

    var binding : FragmentDiaryEntryBinding? = null

    val viewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiaryEntryBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            btSave.setOnClickListener {
                val content = etContent.text
                if (content.isNotEmpty()) {
                    val heading = content.substring(
                        0, if (content.length < 10) {
                            content.length
                        } else {
                            10
                        }
                    )
                    val entry = DiaryEntry(heading = heading, content = content.toString())
                    viewModel.saveEntry(entry = entry)
                    findNavController().popBackStack()
                }
            }
        }
    }


}