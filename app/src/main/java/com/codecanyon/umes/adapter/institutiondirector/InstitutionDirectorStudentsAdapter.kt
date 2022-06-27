package com.codecanyon.umes.adapter.institutiondirector

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.InstitutionDirectorStudentsItemBinding
import com.codecanyon.umes.model.institutiondirector.StudentsInstitutionDirector

class InstitutionDirectorStudentsAdapter(var studentList: List<StudentsInstitutionDirector>) : RecyclerView.Adapter<InstitutionDirectorStudentsAdapter.InstitutionDirectorStudentsHolder>() {
    private lateinit var v: InstitutionDirectorStudentsItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InstitutionDirectorStudentsHolder {
        v = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.institution_director_students_item, parent, false)
        return InstitutionDirectorStudentsHolder(v)
    }

    override fun onBindViewHolder(holder: InstitutionDirectorStudentsHolder, position: Int) {
        holder.sV.student = studentList.get(position)
    }

    override fun getItemCount() = studentList.size

    inner class InstitutionDirectorStudentsHolder(var sV: InstitutionDirectorStudentsItemBinding) : RecyclerView.ViewHolder(sV.root)

    fun loadData(students: List<StudentsInstitutionDirector>){
        studentList = students
        notifyDataSetChanged()
    }
}