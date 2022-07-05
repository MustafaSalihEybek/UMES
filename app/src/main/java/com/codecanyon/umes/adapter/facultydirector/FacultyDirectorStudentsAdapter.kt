package com.codecanyon.umes.adapter.facultydirector

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.FacultyDirectorStudentsItemBinding
import com.codecanyon.umes.model.facultydirector.StudentsFacultyDirector

class FacultyDirectorStudentsAdapter(var studentList: List<StudentsFacultyDirector>) : RecyclerView.Adapter<FacultyDirectorStudentsAdapter.FacultyDirectorStudentsHolder>() {
    private lateinit var v: FacultyDirectorStudentsItemBinding
    private lateinit var listener: StudentDetailsOnItemClickListener
    private var aPos: Int = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FacultyDirectorStudentsHolder {
        v = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.faculty_director_students_item, parent, false)
        return FacultyDirectorStudentsHolder(v)
    }

    override fun onBindViewHolder(holder: FacultyDirectorStudentsHolder, position: Int) {
        holder.sV.student = studentList.get(position)

        holder.btnDetails.setOnClickListener {
            aPos = holder.adapterPosition

            if (aPos != RecyclerView.NO_POSITION)
                listener.onItemClick(studentList.get(aPos))
        }
    }

    override fun getItemCount() = studentList.size

    inner class FacultyDirectorStudentsHolder(var sV: FacultyDirectorStudentsItemBinding) : RecyclerView.ViewHolder(sV.root){
        val btnDetails: ImageView = sV.root.findViewById(R.id.faculty_director_students_item_btnDetails)
    }

    fun loadData(students: List<StudentsFacultyDirector>){
        studentList = students
        notifyDataSetChanged()
    }

    interface StudentDetailsOnItemClickListener{
        fun onItemClick(studentData: StudentsFacultyDirector)
    }

    fun setStudentDetailsOnItemClickListener(listener: StudentDetailsOnItemClickListener){
        this.listener = listener
    }
}