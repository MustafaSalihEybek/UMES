package com.codecanyon.umes.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.codecanyon.umes.model.Coordinator
import com.codecanyon.umes.model.Result
import com.codecanyon.umes.repository.coordinator.UpdateProfileCoordinatorRepository
import com.codecanyon.umes.util.AppUtils
import com.codecanyon.umes.viewmodel.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class MainViewModel(application: Application) : BaseViewModel(application) {
    val successMessage = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()
    val newTeacherData = MutableLiveData<Coordinator>()

    fun updateTeacherData(userId: Int, userPhone: String, userEmail: String, userPassword: String){
        AppUtils.updateProfileCoordinatorRepository = UpdateProfileCoordinatorRepository()
        AppUtils.disposable = CompositeDisposable()

        AppUtils.jsonObject = JSONObject()
        AppUtils.jsonObject.put("coordinatorId", userId)
        AppUtils.jsonObject.put("teacherPhone", userPhone)
        AppUtils.jsonObject.put("teacherEmail", userEmail)
        AppUtils.jsonObject.put("teacherPassword", userPassword)

        AppUtils.jsonObjectString = AppUtils.jsonObject.toString()
        AppUtils.requestBody = AppUtils.jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        AppUtils.disposable.add(
            AppUtils.updateProfileCoordinatorRepository.updateCoordinatorProfile(AppUtils.requestBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Result>(){
                    override fun onSuccess(t: Result) {
                        successMessage.value = t.message

                        AppUtils.mCoordinator = Coordinator(
                            userId,
                            "",
                            1,
                            "",
                            userPassword,
                            userPhone,
                            userEmail
                        )
                        newTeacherData.value = AppUtils.mCoordinator
                    }

                    override fun onError(e: Throwable) {
                        errorMessage.value = e.localizedMessage
                    }
                })
        )
    }
}