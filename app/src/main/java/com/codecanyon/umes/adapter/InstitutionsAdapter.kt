package com.codecanyon.umes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.InstitutionsBinding
import com.codecanyon.umes.model.Institution

class InstitutionsAdapter(var institutionList: ArrayList<Institution>) : RecyclerView.Adapter<InstitutionsAdapter.InstitutionsHolder>() {
    private lateinit var v: InstitutionsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstitutionsHolder {
        v = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.institutions, parent, false)
        return InstitutionsHolder(v)
    }

    override fun onBindViewHolder(holder: InstitutionsHolder, position: Int) {
        holder.insV.institutions = institutionList.get(position)
    }

    override fun getItemCount(): Int {
        return institutionList.size
    }

    fun loadData(institutions: ArrayList<Institution>){
        institutionList = ArrayList()
        institutionList.addAll(institutions)
        notifyDataSetChanged()
    }

    inner class InstitutionsHolder(var insV: InstitutionsBinding) : RecyclerView.ViewHolder(insV.root)
}