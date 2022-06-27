package com.codecanyon.umes.repository.departmentdirector

import com.codecanyon.umes.model.deparmentdirector.InstitutionDirectorDepartmentDirector
import com.codecanyon.umes.util.AppUtils
import io.reactivex.Single
import okhttp3.RequestBody

class InstitutionDirectorsDepartmentDirectorRepository {
    fun getInstitutionDirectorsForDepartmentDirector(requestBody: RequestBody) : Single<List<InstitutionDirectorDepartmentDirector>> {
        return AppUtils.getUmesAPI().getInstitutionDirectorsForDepartmentDirector(requestBody)
    }
}