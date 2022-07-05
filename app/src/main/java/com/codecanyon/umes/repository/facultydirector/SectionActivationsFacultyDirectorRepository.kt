package com.codecanyon.umes.repository.facultydirector

import com.codecanyon.umes.model.facultydirector.SectionActivationFacultyDirector
import com.codecanyon.umes.util.AppUtils
import io.reactivex.Single
import okhttp3.RequestBody

class SectionActivationsFacultyDirectorRepository {
    fun getSectionsActivationForFacultyDirector(bodyData: RequestBody) : Single<List<SectionActivationFacultyDirector>> {
        return AppUtils.getUmesAPI().getSectionActivationsForFacultyDirector(bodyData)
    }
}