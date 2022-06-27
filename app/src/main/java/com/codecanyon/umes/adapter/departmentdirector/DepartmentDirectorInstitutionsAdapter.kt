package com.codecanyon.umes.adapter.departmentdirector

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.DepartmentDirectorInstitutionsItemBinding
import com.codecanyon.umes.model.deparmentdirector.InstitutionDepartmentDirector

class DepartmentDirectorInstitutionsAdapter(var institutionList: List<InstitutionDepartmentDirector>, val mContext: Context) : RecyclerView.Adapter<DepartmentDirectorInstitutionsAdapter.DepartmentDirectorInstitutionsHolder>() {
    private lateinit var v: DepartmentDirectorInstitutionsItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DepartmentDirectorInstitutionsHolder {
        v = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.department_director_institutions_item, parent, false)
        return DepartmentDirectorInstitutionsHolder(v)
    }

    override fun onBindViewHolder(holder: DepartmentDirectorInstitutionsHolder, position: Int) {
        holder.iD.institution = institutionList.get(position)
    }

    override fun getItemCount() = institutionList.size

    inner class DepartmentDirectorInstitutionsHolder(var iD: DepartmentDirectorInstitutionsItemBinding) : RecyclerView.ViewHolder(iD.root){
        val btnDetails: ImageView = iD.root.findViewById(R.id.department_director_institutions_item_btnDetails)
    }

    fun loadData(institutions: List<InstitutionDepartmentDirector>){
        institutionList = institutions
        notifyDataSetChanged()
    }
}