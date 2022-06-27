package com.codecanyon.umes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codecanyon.umes.R
import com.codecanyon.umes.util.show

class NavListAdapter(val titleList: ArrayList<String>) : RecyclerView.Adapter<NavListAdapter.NavListHolder>() {
    private lateinit var v: View
    private var aPos: Int = 0
    private lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavListHolder {
        v = LayoutInflater.from(parent.context).inflate(R.layout.nav_list_item, parent, false)
        return NavListHolder(v)
    }

    override fun onBindViewHolder(holder: NavListHolder, position: Int) {
        holder.txtItemName.text = "-  ${titleList.get(position)}"

        holder.itemView.setOnClickListener {
            aPos = holder.adapterPosition

            if (aPos != RecyclerView.NO_POSITION)
                listener.onClick(titleList.get(position))
        }
    }

    override fun getItemCount(): Int {
        return titleList.size
    }

    inner class NavListHolder(view: View) : RecyclerView.ViewHolder(view){
        var txtItemName: TextView = view.findViewById(R.id.nav_list_item_txtItemName)
    }

    interface OnItemClickListener{
        fun onClick(title: String)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }
}