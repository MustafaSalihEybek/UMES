package com.codecanyon.umes.repository.departmentdirector

import com.codecanyon.umes.model.deparmentdirector.MemberActivationDepartmentDirector
import com.codecanyon.umes.util.AppUtils
import io.reactivex.Single
import okhttp3.RequestBody

class MembersActivationDepartmentDirectorRepository {
    fun getMembersActivationForDepartmentDirector(requestBody: RequestBody) : Single<List<MemberActivationDepartmentDirector>> {
        return AppUtils.getUmesAPI().getMembersActivationForDepartmentDirector(requestBody)
    }
}