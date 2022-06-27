package com.codecanyon.umes.adapter.departmentdirector

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.DepartmentDirectorInstitutionDirectorsItemBinding
import com.codecanyon.umes.model.deparmentdirector.InstitutionDirectorDepartmentDirector

class DepartmentDirectorInstitutionDirectorsAdapter(var institutionDirectorList: List<InstitutionDirectorDepartmentDirector>, val mContext: Context) : RecyclerView.Adapter<DepartmentDirectorInstitutionDirectorsAdapter.DepartmentDirectorInstitutionDirectorsHolder>() {
    private lateinit var v: DepartmentDirectorInstitutionDirectorsItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DepartmentDirectorInstitutionDirectorsHolder {
        v = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.department_director_institution_directors_item, parent, false)
        return DepartmentDirectorInstitutionDirectorsHolder(v)
    }

    override fun onBindViewHolder(
        holder: DepartmentDirectorInstitutionDirectorsHolder,
        position: Int
    ) {
        holder.iD.institutiondirector = institutionDirectorList.get(position)
    }

    override fun getItemCount() = institutionDirectorList.size

    inner class DepartmentDirectorInstitutionDirectorsHolder(var iD: DepartmentDirectorInstitutionDirectorsItemBinding) : RecyclerView.ViewHolder(iD.root){
        val btnDetails: ImageView = iD.root.findViewById(R.id.department_director_institution_directors_item_btnDetails)
    }

    fun loadData(institutionDirectors: List<InstitutionDirectorDepartmentDirector>){
        institutionDirectorList = institutionDirectors
        notifyDataSetChanged()
    }
}