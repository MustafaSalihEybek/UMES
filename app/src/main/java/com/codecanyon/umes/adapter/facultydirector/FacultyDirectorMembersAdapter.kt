package com.codecanyon.umes.adapter.facultydirector

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.FacultyDirectorMembersItemBinding
import com.codecanyon.umes.model.facultydirector.MemberFacultyDirector

class FacultyDirectorMembersAdapter(var memberList: List<MemberFacultyDirector>) : RecyclerView.Adapter<FacultyDirectorMembersAdapter.FacultyDirectorMembersHolder>() {
    private lateinit var v: FacultyDirectorMembersItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FacultyDirectorMembersHolder {
        v = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.faculty_director_members_item, parent, false)
        return FacultyDirectorMembersHolder(v)
    }

    override fun onBindViewHolder(holder: FacultyDirectorMembersHolder, position: Int) {
        holder.mV.member = memberList.get(position)
    }

    override fun getItemCount() = memberList.size

    inner class FacultyDirectorMembersHolder(var mV: FacultyDirectorMembersItemBinding) : RecyclerView.ViewHolder(mV.root)

    fun loadData(members: List<MemberFacultyDirector>){
        memberList = members
        notifyDataSetChanged()
    }
}