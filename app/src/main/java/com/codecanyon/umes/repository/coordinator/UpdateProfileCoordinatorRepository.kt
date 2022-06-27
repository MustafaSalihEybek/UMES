package com.codecanyon.umes.repository.coordinator

import com.codecanyon.umes.model.Result
import com.codecanyon.umes.util.AppUtils
import io.reactivex.Single
import okhttp3.RequestBody

class UpdateProfileCoordinatorRepository {
    fun updateCoordinatorProfile(body: RequestBody) : Single<Result> {
        return AppUtils.getUmesAPI().updateCoordinatorProfile(body)
    }
}