package com.codecanyon.umes.adapter.coordinator

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.CoordinatorStudentsItemBinding
import com.codecanyon.umes.model.coordinator.StudentCoordinator
import com.codecanyon.umes.util.Singleton

class CoordinatorStudentsAdapter(var studentList: List<StudentCoordinator>, val mContext: Context) : RecyclerView.Adapter<CoordinatorStudentsAdapter.CoordinatorStudentsHolder>() {
    private lateinit var v: CoordinatorStudentsItemBinding
    private var aPos: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoordinatorStudentsHolder {
        v = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.coordinator_students_item, parent, false)
        return CoordinatorStudentsHolder(v)
    }

    override fun onBindViewHolder(holder: CoordinatorStudentsHolder, position: Int) {
        holder.cS.student = studentList.get(position)

        holder.btnDetails.setOnClickListener {
            aPos = holder.adapterPosition

            if (aPos != RecyclerView.NO_POSITION)
                Singleton.showCoordinatorStudentDetailsDialog(mContext, studentList.get(aPos))
        }
    }

    override fun getItemCount() = studentList.size

    inner class CoordinatorStudentsHolder(var cS: CoordinatorStudentsItemBinding) : RecyclerView.ViewHolder(cS.root){
        val btnDetails: ImageView = cS.root.findViewById(R.id.coordinator_students_item_btnDetails)
    }

    fun loadData(students: List<StudentCoordinator>){
        studentList = students
        notifyDataSetChanged()
    }
}