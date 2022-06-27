package com.codecanyon.umes.viewmodel.coordinator

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.codecanyon.umes.model.coordinator.RatingCoordinator
import com.codecanyon.umes.repository.coordinator.RatingsCoordinatorRepository
import com.codecanyon.umes.util.AppUtils
import com.codecanyon.umes.viewmodel.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class CoordinatorRatingViewModel(application: Application) : BaseViewModel(application) {
    val errorMessage = MutableLiveData<String>()
    val ratingList = MutableLiveData<List<RatingCoordinator>>()

    fun getRatings(teacherId: Int, teacherDepartmentId: Int){
        AppUtils.ratingsCoordinatorRepository = RatingsCoordinatorRepository()
        AppUtils.disposable = CompositeDisposable()

        AppUtils.jsonObject = JSONObject()
        AppUtils.jsonObject.put("teacherId", teacherId)
        AppUtils.jsonObject.put("teacherDepartmentId", teacherDepartmentId)

        AppUtils.jsonObjectString = AppUtils.jsonObject.toString()
        AppUtils.requestBody = AppUtils.jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        AppUtils.disposable.add(
            AppUtils.ratingsCoordinatorRepository.getRatingsForCoordinator(AppUtils.requestBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<RatingCoordinator>>(){
                    override fun onSuccess(t: List<RatingCoordinator>) {
                        ratingList.value = t
                    }

                    override fun onError(e: Throwable) {
                        errorMessage.value = e.localizedMessage
                    }
                })
        )
    }
}