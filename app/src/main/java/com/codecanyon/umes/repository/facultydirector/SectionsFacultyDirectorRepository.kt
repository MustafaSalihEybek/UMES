package com.codecanyon.umes.repository.facultydirector

import com.codecanyon.umes.model.facultydirector.SectionFacultyDirector
import com.codecanyon.umes.util.AppUtils
import io.reactivex.Single
import okhttp3.RequestBody

class SectionsFacultyDirectorRepository {
    fun getSectionsForFacultyDirector(bodyData: RequestBody) : Single<List<SectionFacultyDirector>> {
        return AppUtils.getUmesAPI().getSectionsForFacultyDirector(bodyData)
    }
}