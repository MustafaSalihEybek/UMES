package com.codecanyon.umes.adapter.departmentdirector

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.DepartmentDirectorMembersItemBinding
import com.codecanyon.umes.model.deparmentdirector.MemberDepartmentDirector

class DepartmentDirectorMembersAdapter(var memberList: List<MemberDepartmentDirector>, val mContext: Context) : RecyclerView.Adapter<DepartmentDirectorMembersAdapter.DepartmentDirectorMembersHolder>() {
    private lateinit var v: DepartmentDirectorMembersItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DepartmentDirectorMembersHolder {
        v = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.department_director_members_item, parent, false)
        return DepartmentDirectorMembersHolder(v)
    }

    override fun onBindViewHolder(holder: DepartmentDirectorMembersHolder, position: Int) {
        holder.dM.member = memberList.get(position)
    }

    override fun getItemCount() = memberList.size

    inner class DepartmentDirectorMembersHolder(var dM: DepartmentDirectorMembersItemBinding) : RecyclerView.ViewHolder(dM.root){
        val btnDetails: ImageView = dM.root.findViewById(R.id.department_director_members_item_btnDetails)
    }

    fun loadData(members: List<MemberDepartmentDirector>){
        memberList = members
        notifyDataSetChanged()
    }
}