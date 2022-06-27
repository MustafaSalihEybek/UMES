package com.codecanyon.umes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.StudentsBinding
import com.codecanyon.umes.model.Student

class StudentsAdapter(var studentList: ArrayList<Student>) : RecyclerView.Adapter<StudentsAdapter.StudentsHolder>() {
    private lateinit var v: StudentsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsHolder {
        v = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.students, parent, false)
        return StudentsHolder(v)
    }

    override fun onBindViewHolder(holder: StudentsHolder, position: Int) {
        holder.stuV.student = studentList.get(position)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun loadData(students: ArrayList<Student>){
        studentList = ArrayList()
        studentList.addAll(students)
        notifyDataSetChanged()
    }

    inner class StudentsHolder(var stuV: StudentsBinding) : RecyclerView.ViewHolder(stuV.root)
}