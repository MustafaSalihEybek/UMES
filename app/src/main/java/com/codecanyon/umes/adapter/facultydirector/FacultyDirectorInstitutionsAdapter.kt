package com.codecanyon.umes.adapter.facultydirector

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.FacultyDirectorInstitutionItemBinding
import com.codecanyon.umes.model.facultydirector.InstitutionFacultyDirector

class FacultyDirectorInstitutionsAdapter(var institutionList: List<InstitutionFacultyDirector>) : RecyclerView.Adapter<FacultyDirectorInstitutionsAdapter.FacultyDirectorInstitutionsHolder>() {
    private lateinit var v: FacultyDirectorInstitutionItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FacultyDirectorInstitutionsHolder {
        v = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.faculty_director_institution_item, parent, false)
        return FacultyDirectorInstitutionsHolder(v)
    }

    override fun onBindViewHolder(holder: FacultyDirectorInstitutionsHolder, position: Int) {
        holder.iV.institution = institutionList.get(position)
    }

    override fun getItemCount() = institutionList.size

    inner class FacultyDirectorInstitutionsHolder(var iV: FacultyDirectorInstitutionItemBinding) : RecyclerView.ViewHolder(iV.root)

    fun loadData(institutions: List<InstitutionFacultyDirector>){
        institutionList = institutions
        notifyDataSetChanged()
    }
}