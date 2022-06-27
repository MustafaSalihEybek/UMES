package com.codecanyon.umes.adapter.institutiondirector

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.InstitutionDirectorReportsItemBinding
import com.codecanyon.umes.model.institutiondirector.ReportsInstitutionDirector

class InstitutionDirectorReportsAdapter(var reportList: List<ReportsInstitutionDirector>) : RecyclerView.Adapter<InstitutionDirectorReportsAdapter.InstitutionDirectorReportsHolder>() {
    private lateinit var v: InstitutionDirectorReportsItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InstitutionDirectorReportsHolder {
        v = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.institution_director_reports_item, parent, false)
        return InstitutionDirectorReportsHolder(v)
    }

    override fun onBindViewHolder(holder: InstitutionDirectorReportsHolder, position: Int) {
        holder.rV.report = reportList.get(position)
    }

    override fun getItemCount() = reportList.size

    inner class InstitutionDirectorReportsHolder(var rV: InstitutionDirectorReportsItemBinding) : RecyclerView.ViewHolder(rV.root)

    fun loadData(reports: List<ReportsInstitutionDirector>){
        reportList = reports
        notifyDataSetChanged()
    }
}