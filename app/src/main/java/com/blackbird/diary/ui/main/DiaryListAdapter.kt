package com.blackbird.diary.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blackbird.diary.data.DiaryEntry
import com.blackbird.diary.databinding.DiaryListItemBinding


class DiaryListAdapter() : RecyclerView.Adapter<ViewHolder>() {

    private var diaryList:MutableList<DiaryEntry> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DiaryListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            tvHeading.text = diaryList[position].heading
        }
    }

    override fun getItemCount() = diaryList.size

    fun setData(list: MutableList<DiaryEntry>){
        diaryList.clear()
        diaryList.addAll(list)
        notifyDataSetChanged()
    }
}

class ViewHolder(val binding: DiaryListItemBinding) : RecyclerView.ViewHolder(binding.root)
