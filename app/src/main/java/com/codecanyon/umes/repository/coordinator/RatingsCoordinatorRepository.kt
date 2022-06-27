package com.codecanyon.umes.repository.coordinator

import com.codecanyon.umes.model.coordinator.RatingCoordinator
import com.codecanyon.umes.util.AppUtils
import io.reactivex.Single
import okhttp3.RequestBody

class RatingsCoordinatorRepository {
    fun getRatingsForCoordinator(requestBody: RequestBody) : Single<List<RatingCoordinator>> {
        return AppUtils.getUmesAPI().getRatingsForCoordinator(requestBody)
    }
}