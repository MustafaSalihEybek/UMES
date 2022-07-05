package com.codecanyon.umes.repository.facultydirector

import com.codecanyon.umes.model.facultydirector.StudentsFacultyDirector
import com.codecanyon.umes.util.AppUtils
import io.reactivex.Single
import okhttp3.RequestBody

class StudentsFacultyDirectorRepository {
    fun getStudentsForFacultyDirector(bodyData: RequestBody) : Single<List<StudentsFacultyDirector>> {
        return AppUtils.getUmesAPI().getStudentsForFacultyDirector(bodyData)
    }
}