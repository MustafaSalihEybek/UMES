package com.codecanyon.umes.adapter.coordinator

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.CoordinatorInstitutionsItemBinding
import com.codecanyon.umes.model.coordinator.InstitutionCoordinator

class CoordinatorInstitutionsAdapter(var institutionList: List<InstitutionCoordinator>, val mContext: Context) : RecyclerView.Adapter<CoordinatorInstitutionsAdapter.CoordinatorInstitutionsHolder>() {
    private lateinit var v: CoordinatorInstitutionsItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoordinatorInstitutionsHolder {
        v = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.coordinator_institutions_item, parent, false)
        return CoordinatorInstitutionsHolder(v)
    }

    override fun onBindViewHolder(holder: CoordinatorInstitutionsHolder, position: Int) {
        holder.cI.institution = institutionList.get(position)
    }

    override fun getItemCount() = institutionList.size

    inner class CoordinatorInstitutionsHolder(var cI: CoordinatorInstitutionsItemBinding) : RecyclerView.ViewHolder(cI.root){
        val btnDetails: ImageView = cI.root.findViewById(R.id.coordinator_institutions_item_btnDetails)
    }

    fun loadData(institutions: List<InstitutionCoordinator>){
        institutionList = institutions
        notifyDataSetChanged()
    }
}