package com.blackbird.diary.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blackbird.diary.data.DiaryEntry

class MainViewModel : ViewModel() {
    val diaryList = MutableLiveData<MutableList<DiaryEntry>>()


    fun saveEntry(entry: DiaryEntry){
        val list = diaryList.value ?: mutableListOf()
        list.add(entry)
        diaryList.postValue(list)
    }
}