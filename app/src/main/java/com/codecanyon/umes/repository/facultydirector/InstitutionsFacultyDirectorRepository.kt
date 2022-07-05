package com.codecanyon.umes.repository.facultydirector

import com.codecanyon.umes.model.facultydirector.InstitutionFacultyDirector
import com.codecanyon.umes.util.AppUtils
import io.reactivex.Single
import okhttp3.RequestBody

class InstitutionsFacultyDirectorRepository {
    fun getInstitutionsForFacultyDirector(bodyData: RequestBody) : Single<List<InstitutionFacultyDirector>> {
        return AppUtils.getUmesAPI().getInstitutionsForFacultyDirector(bodyData)
    }
}