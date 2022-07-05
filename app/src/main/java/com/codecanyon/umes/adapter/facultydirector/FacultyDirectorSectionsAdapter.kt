package com.codecanyon.umes.adapter.facultydirector

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.FacultyDirectorSectionsItemBinding
import com.codecanyon.umes.model.facultydirector.SectionFacultyDirector

class FacultyDirectorSectionsAdapter(var sectionList: List<SectionFacultyDirector>) : RecyclerView.Adapter<FacultyDirectorSectionsAdapter.FacultyDirectorSectionsHolder>() {
    private lateinit var v: FacultyDirectorSectionsItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FacultyDirectorSectionsHolder {
        v = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.faculty_director_sections_item, parent, false)
        return FacultyDirectorSectionsHolder(v)
    }

    override fun onBindViewHolder(holder: FacultyDirectorSectionsHolder, position: Int) {
        holder.sV.section = sectionList.get(position)
    }

    override fun getItemCount() = sectionList.size

    inner class FacultyDirectorSectionsHolder(var sV: FacultyDirectorSectionsItemBinding) : RecyclerView.ViewHolder(sV.root)

    fun loadData(sections: List<SectionFacultyDirector>){
        sectionList = sections
        notifyDataSetChanged()
    }
}