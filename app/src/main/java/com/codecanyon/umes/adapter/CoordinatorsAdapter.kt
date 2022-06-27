package com.codecanyon.umes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.CoordinatorsBinding
import com.codecanyon.umes.model.Coordinator

class CoordinatorsAdapter(var coordinatorList: ArrayList<Coordinator>) : RecyclerView.Adapter<CoordinatorsAdapter.CoordinatorsHolder>() {
    private lateinit var v: CoordinatorsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoordinatorsHolder {
        v = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.coordinators, parent, false)
        return CoordinatorsHolder(v)
    }

    override fun onBindViewHolder(holder: CoordinatorsHolder, position: Int) {
        holder.corV.coordinator = coordinatorList.get(position)
    }

    override fun getItemCount(): Int {
        return coordinatorList.size
    }

    fun loadData(coordinators: ArrayList<Coordinator>){
        coordinatorList = ArrayList()
        coordinatorList.addAll(coordinators)
        notifyDataSetChanged()
    }

    inner class CoordinatorsHolder(var corV: CoordinatorsBinding) : RecyclerView.ViewHolder(corV.root)
}