package com.codecanyon.umes.adapter.departmentdirector

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.DepartmentDirectorStudentsItemBinding
import com.codecanyon.umes.model.deparmentdirector.StudentDepartmentDirector

class DepartmentDirectorStudentsAdapter(var studentList: List<StudentDepartmentDirector>, val mContext: Context) : RecyclerView.Adapter<DepartmentDirectorStudentsAdapter.DepartmentDirectorStudentsHolder>() {
    private lateinit var v: DepartmentDirectorStudentsItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DepartmentDirectorStudentsHolder {
        v = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.department_director_students_item, parent, false)
        return DepartmentDirectorStudentsHolder(v)
    }

    override fun onBindViewHolder(holder: DepartmentDirectorStudentsHolder, position: Int) {
        holder.dS.student = studentList.get(position)
    }

    override fun getItemCount() = studentList.size

    inner class DepartmentDirectorStudentsHolder(var dS: DepartmentDirectorStudentsItemBinding) : RecyclerView.ViewHolder(dS.root){
        val btnDetails: ImageView = dS.root.findViewById(R.id.department_director_students_item_btnDetails)
    }

    fun loadData(students: List<StudentDepartmentDirector>){
        studentList = students
        notifyDataSetChanged()
    }
}