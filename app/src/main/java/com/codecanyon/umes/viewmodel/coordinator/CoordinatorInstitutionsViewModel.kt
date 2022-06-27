package com.codecanyon.umes.viewmodel.coordinator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.codecanyon.umes.model.coordinator.InstitutionCoordinator
import com.codecanyon.umes.repository.coordinator.InstitutionsCoordinatorRepository
import com.codecanyon.umes.util.AppUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class CoordinatorInstitutionsViewModel(application: Application) : AndroidViewModel(application) {
    val errorMessage = MutableLiveData<String>()
    val institutionsList = MutableLiveData<List<InstitutionCoordinator>>()

    fun getInstitutions(departmentId: Int){
        AppUtils.institutionsCoordinatorRepository = InstitutionsCoordinatorRepository()
        AppUtils.disposable = CompositeDisposable()

        AppUtils.jsonObject = JSONObject()
        AppUtils.jsonObject.put("teacherDepartmentId", departmentId)

        AppUtils.jsonObjectString = AppUtils.jsonObject.toString()
        AppUtils.requestBody = AppUtils.jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        AppUtils.disposable.add(
            AppUtils.institutionsCoordinatorRepository.getInstitutionsForCoordinator(AppUtils.requestBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<InstitutionCoordinator>>(){
                    override fun onSuccess(t: List<InstitutionCoordinator>) {
                        institutionsList.value = t
                    }

                    override fun onError(e: Throwable) {
                        errorMessage.value = e.localizedMessage
                    }
                })
        )
    }
}