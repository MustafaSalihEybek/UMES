package com.codecanyon.umes.adapter.departmentdirector

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.DepartmentDirectorMembersActivationItemBinding
import com.codecanyon.umes.model.deparmentdirector.MemberActivationDepartmentDirector

class DepartmentDirectorMembersActivationAdapter(var memberActivationList: List<MemberActivationDepartmentDirector>, val mContext: Context) : RecyclerView.Adapter<DepartmentDirectorMembersActivationAdapter.DepartmentDirectorMembersActivationHolder>() {
    private lateinit var v: DepartmentDirectorMembersActivationItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DepartmentDirectorMembersActivationHolder {
        v = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.department_director_members_activation_item, parent, false)
        return DepartmentDirectorMembersActivationHolder(v)
    }

    override fun onBindViewHolder(
        holder: DepartmentDirectorMembersActivationHolder,
        position: Int
    ) {
        holder.mA.memberactivation = memberActivationList.get(position)
    }

    override fun getItemCount() = memberActivationList.size

    inner class DepartmentDirectorMembersActivationHolder(var mA: DepartmentDirectorMembersActivationItemBinding) : RecyclerView.ViewHolder(mA.root){
        val btnDetails: ImageView = mA.root.findViewById(R.id.department_director_members_activation_item_btnDetails)
    }

    fun loadData(membersActivation: List<MemberActivationDepartmentDirector>){
        memberActivationList = membersActivation
    }
}