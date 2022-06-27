package com.codecanyon.umes.repository.coordinator

import com.codecanyon.umes.model.coordinator.InstitutionCoordinator
import com.codecanyon.umes.util.AppUtils
import io.reactivex.Single
import okhttp3.RequestBody

class InstitutionsCoordinatorRepository {
    fun getInstitutionsForCoordinator(requestBody: RequestBody) : Single<List<InstitutionCoordinator>> {
        return AppUtils.getUmesAPI().getInstitutionsForCoordinator(requestBody)
    }
}