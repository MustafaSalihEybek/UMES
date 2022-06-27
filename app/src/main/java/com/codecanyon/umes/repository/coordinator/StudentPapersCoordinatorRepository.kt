package com.codecanyon.umes.repository.coordinator

import com.codecanyon.umes.model.coordinator.StudentPaperCoordinator
import com.codecanyon.umes.util.AppUtils
import io.reactivex.Single
import okhttp3.RequestBody

class StudentPapersCoordinatorRepository {
    fun getStudentPapersForCoordinator(requestBody: RequestBody) : Single<List<StudentPaperCoordinator>> {
        return AppUtils.getUmesAPI().getStudentPapersForCoordinator(requestBody)
    }
}