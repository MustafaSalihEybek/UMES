package com.codecanyon.umes.repository.departmentdirector

import com.codecanyon.umes.model.deparmentdirector.MemberDepartmentDirector
import com.codecanyon.umes.util.AppUtils
import io.reactivex.Single
import okhttp3.RequestBody

class MembersDepartmentDirectorRepository {
    fun getMembersForDepartmentDirector(requestBody: RequestBody) : Single<List<MemberDepartmentDirector>> {
        return AppUtils.getUmesAPI().getMembersForDepartmentDirector(requestBody)
    }
}