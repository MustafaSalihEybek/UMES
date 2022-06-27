package com.codecanyon.umes.repository.departmentdirector

import com.codecanyon.umes.model.deparmentdirector.StudentPaperDepartmentDirector
import com.codecanyon.umes.util.AppUtils
import io.reactivex.Single
import okhttp3.RequestBody

class StudentPaperDepartmentDirectorRepository {
    fun getStudentPaperForDepartmentDirector(requestBody: RequestBody) : Single<List<StudentPaperDepartmentDirector>> {
        return AppUtils.getUmesAPI().getStudentPaperForDepartmentDirector(requestBody)
    }
}