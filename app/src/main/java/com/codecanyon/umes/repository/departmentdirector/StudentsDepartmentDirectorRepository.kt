package com.codecanyon.umes.repository.departmentdirector

import com.codecanyon.umes.model.deparmentdirector.StudentDepartmentDirector
import com.codecanyon.umes.util.AppUtils
import io.reactivex.Single
import okhttp3.RequestBody

class StudentsDepartmentDirectorRepository {
    fun getStudentsForDepartmentDirector(requestBody: RequestBody) : Single<List<StudentDepartmentDirector>> {
        return AppUtils.getUmesAPI().getStudentsForDepartmentDirector(requestBody)
    }
}