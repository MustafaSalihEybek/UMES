package com.codecanyon.umes.repository.institutiondirector

import com.codecanyon.umes.model.institutiondirector.ReportsInstitutionDirector
import com.codecanyon.umes.util.AppUtils
import io.reactivex.Single
import okhttp3.RequestBody

class ReportsInstitutionDirectorRepository {
    fun getReportsForInstitutionDirector(bodyData: RequestBody) : Single<List<ReportsInstitutionDirector>> {
        return AppUtils.getUmesAPI().getReportsForInstitutionDirector(bodyData)
    }
}