package com.codecanyon.umes.adapter.coordinator

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.CoordinatorStudentPapersItemBinding
import com.codecanyon.umes.model.coordinator.StudentPaperCoordinator

class CoordinatorStudentPapersAdapter(var studentPaperList: List<StudentPaperCoordinator>, val mContext: Context) : RecyclerView.Adapter<CoordinatorStudentPapersAdapter.CoordinatorStudentPapersHolder>() {
    private lateinit var v: CoordinatorStudentPapersItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoordinatorStudentPapersHolder {
        v = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.coordinator_student_papers_item, parent, false)
        return CoordinatorStudentPapersHolder(v)
    }

    override fun onBindViewHolder(holder: CoordinatorStudentPapersHolder, position: Int) {
        holder.sV.studentpaper = studentPaperList.get(position)
    }

    override fun getItemCount() = studentPaperList.size

    inner class CoordinatorStudentPapersHolder(var sV: CoordinatorStudentPapersItemBinding) : RecyclerView.ViewHolder(sV.root){
        val btnDetails: ImageView = sV.root.findViewById(R.id.coordinator_student_papers_item_btnDetails)
    }

    fun loadData(studentPapers: List<StudentPaperCoordinator>){
        studentPaperList = studentPapers
        notifyDataSetChanged()
    }
}