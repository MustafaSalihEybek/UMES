package com.codecanyon.umes.adapter.coordinator

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.CoordinatorRatingItemBinding
import com.codecanyon.umes.model.coordinator.RatingCoordinator

class CoordinatorRatingsAdapter(var ratingList: List<RatingCoordinator>, val mContext: Context) : RecyclerView.Adapter<CoordinatorRatingsAdapter.CoordinatorRatingsHolder>() {
    private lateinit var v: CoordinatorRatingItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoordinatorRatingsHolder {
        v = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.coordinator_rating_item, parent, false)
        return CoordinatorRatingsHolder(v)
    }

    override fun onBindViewHolder(holder: CoordinatorRatingsHolder, position: Int) {
        holder.cV.rating = ratingList.get(position)
    }

    override fun getItemCount() = ratingList.size

    inner class CoordinatorRatingsHolder(var cV: CoordinatorRatingItemBinding) : RecyclerView.ViewHolder(cV.root){
        val btnDetails: ImageView = cV.root.findViewById(R.id.coordinator_rating_item_btnDetails)
    }

    fun loadData(ratings: List<RatingCoordinator>){
        ratingList = ratings
        notifyDataSetChanged()
    }
}