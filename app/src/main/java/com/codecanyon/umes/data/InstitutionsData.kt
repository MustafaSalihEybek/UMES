package com.codecanyon.umes.data

import com.codecanyon.umes.model.Institution

object InstitutionsData {
    private var numbers = arrayOf("1", "2")
    private var names = arrayOf("Heraklet Yazılım", "T-Soft")
    private var address = arrayOf("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantiu", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod")
    private lateinit var institutionList: ArrayList<Institution>
    private lateinit var institution: Institution

    fun getData() : ArrayList<Institution>{
        institutionList = ArrayList()

        for (inst in 0 until numbers.size){
            institution = Institution(numbers.get(inst), names.get(inst), address.get(inst))
            institutionList.add(institution)
        }

        return institutionList
    }
}