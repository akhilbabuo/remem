package com.blackbird.diary.ui.main

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.blackbird.diary.R
import com.blackbird.diary.common.CustomSharedPref
import com.blackbird.diary.databinding.FragmentMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private var binding:FragmentMainBinding? = null
    private lateinit var viewModel: MainViewModel
    private var isPinSet = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        findNavController().navigate(R.id.action_mainFragment_to_diaryFragment)
        initResources()
    }

    private fun initResources(){
        binding?.apply {
            isPinSet = CustomSharedPref.isPinSet()
            tvTxtMsg.text = if (isPinSet){
                context?.getString(R.string.enterPin) ?: ""
            }else{
                context?.getString(R.string.createPin) ?: ""
            }

            etPin.addTextChangedListener {
                it?.let { editable ->
                    if (editable.length >= 4){
                        validatePin(editable.toString())
                    }
                }
            }
        }
    }

    private fun validatePin(pin:String){
        if(isPinSet){
            if(CustomSharedPref.getPin()?.equals(pin) == true)
                openDiary()
            else{
                binding?.tvError?.text = context?.getString(R.string.errorInvalidPin)
                binding?.tvError?.isVisible = true
                binding?.etPin?.text?.clear()
            }
        }else if (!isPinSet){
            CustomSharedPref.savePin(pin)
            CustomSharedPref.setIsPinSet(true)
            openDiary()
        }

    }

    private fun openDiary(){
        findNavController().navigate(R.id.action_mainFragment_to_diaryFragment)
    }

}