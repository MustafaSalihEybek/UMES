package com.codecanyon.umes.adapter.facultydirector

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.FacultyDirectorSectionActivationsItemBinding
import com.codecanyon.umes.model.facultydirector.SectionActivationFacultyDirector

class FacultyDirectorSectionActivationsAdapter(var sectionActivationList: List<SectionActivationFacultyDirector>) : RecyclerView.Adapter<FacultyDirectorSectionActivationsAdapter.FacultyDirectorSectionActivationsHolder>() {
    private lateinit var v: FacultyDirectorSectionActivationsItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FacultyDirectorSectionActivationsHolder {
        v = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.faculty_director_section_activations_item, parent, false)
        return FacultyDirectorSectionActivationsHolder(v)
    }

    override fun onBindViewHolder(holder: FacultyDirectorSectionActivationsHolder, position: Int) {
        holder.sAV.sectionactivations = sectionActivationList.get(position)
    }

    override fun getItemCount() = sectionActivationList.size

    inner class FacultyDirectorSectionActivationsHolder(var sAV: FacultyDirectorSectionActivationsItemBinding) : RecyclerView.ViewHolder(sAV.root)

    fun loadData(sectionActivations: List<SectionActivationFacultyDirector>){
        sectionActivationList = sectionActivations
        notifyDataSetChanged()
    }
}