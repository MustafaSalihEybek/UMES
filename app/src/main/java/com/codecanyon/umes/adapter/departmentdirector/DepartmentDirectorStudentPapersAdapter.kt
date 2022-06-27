package com.codecanyon.umes.adapter.departmentdirector

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.DepartmentDirectorStudentPapersItemBinding
import com.codecanyon.umes.model.deparmentdirector.StudentPaperDepartmentDirector

class DepartmentDirectorStudentPapersAdapter(var studentPaperList: List<StudentPaperDepartmentDirector>, val mContext: Context) : RecyclerView.Adapter<DepartmentDirectorStudentPapersAdapter.DepartmentDirectorStudentPapersHolder>() {
    private lateinit var v: DepartmentDirectorStudentPapersItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DepartmentDirectorStudentPapersHolder {
        v = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.department_director_student_papers_item, parent, false)
        return DepartmentDirectorStudentPapersHolder(v)
    }

    override fun onBindViewHolder(holder: DepartmentDirectorStudentPapersHolder, position: Int) {
        holder.sP.studentpaper = studentPaperList.get(position)
    }

    override fun getItemCount() = studentPaperList.size

    inner class DepartmentDirectorStudentPapersHolder(var sP: DepartmentDirectorStudentPapersItemBinding) : RecyclerView.ViewHolder(sP.root){
        val btnDetails: ImageView = sP.root.findViewById(R.id.department_director_student_papers_item_btnDetails)
    }

    fun loadData(studentPapers: List<StudentPaperDepartmentDirector>){
        studentPaperList = studentPapers
        notifyDataSetChanged()
    }
}