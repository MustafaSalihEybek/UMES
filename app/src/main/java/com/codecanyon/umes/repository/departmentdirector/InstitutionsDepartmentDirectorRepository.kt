package com.codecanyon.umes.repository.departmentdirector

import com.codecanyon.umes.model.deparmentdirector.InstitutionDepartmentDirector
import com.codecanyon.umes.util.AppUtils
import io.reactivex.Single
import okhttp3.RequestBody

class InstitutionsDepartmentDirectorRepository {
    fun getInstitutionsForDepartmentDirector(requestBody: RequestBody) : Single<List<InstitutionDepartmentDirector>> {
        return AppUtils.getUmesAPI().getInstitutionsForDepartmentDirector(requestBody)
    }
}