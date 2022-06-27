package com.codecanyon.umes.repository.institutiondirector

import com.codecanyon.umes.model.institutiondirector.StudentsInstitutionDirector
import com.codecanyon.umes.util.AppUtils
import io.reactivex.Single
import okhttp3.RequestBody

class StudentsInstitutionDirectorRepository {
    fun getStudentsForInstitutionDirector(bodyData: RequestBody) : Single<List<StudentsInstitutionDirector>> {
        return AppUtils.getUmesAPI().getStudentsForInstitutionDirector(bodyData)
    }
}