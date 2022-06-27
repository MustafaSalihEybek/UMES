package com.codecanyon.umes.repository.coordinator

import com.codecanyon.umes.model.coordinator.StudentCoordinator
import com.codecanyon.umes.util.AppUtils
import io.reactivex.Single
import okhttp3.RequestBody

class StudentsCoordinatorRepository {
    fun getStudentsForCoordinator(requestBody: RequestBody) : Single<List<StudentCoordinator>> {
        return AppUtils.getUmesAPI().getStudentsForCoordinator(requestBody)
    }
}