package com.codecanyon.umes.repository.facultydirector

import com.codecanyon.umes.model.facultydirector.MemberFacultyDirector
import com.codecanyon.umes.util.AppUtils
import io.reactivex.Single
import okhttp3.RequestBody

class MembersFacultyDirectorRepository {
    fun getMembersForFacultyDirector(bodyData: RequestBody) : Single<List<MemberFacultyDirector>> {
        return AppUtils.getUmesAPI().getMembersForFacultyDirector(bodyData)
    }
}