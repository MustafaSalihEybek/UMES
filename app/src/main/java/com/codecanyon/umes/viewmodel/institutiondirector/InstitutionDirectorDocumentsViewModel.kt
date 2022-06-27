package com.codecanyon.umes.viewmodel.institutiondirector

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.codecanyon.umes.model.institutiondirector.ReportsInstitutionDirector
import com.codecanyon.umes.repository.institutiondirector.ReportsInstitutionDirectorRepository
import com.codecanyon.umes.util.AppUtils
import com.codecanyon.umes.viewmodel.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class InstitutionDirectorDocumentsViewModel(application: Application) : BaseViewModel(application) {
    val reportList = MutableLiveData<List<ReportsInstitutionDirector>>()
    val errorMessage = MutableLiveData<String>()

    fun getReports(institutionDirectorId: Int){
        AppUtils.reportsInstitutionDirectorRepository = ReportsInstitutionDirectorRepository()
        AppUtils.disposable = CompositeDisposable()

        AppUtils.jsonObject = JSONObject()
        AppUtils.jsonObject.put("institutionDirectorId", institutionDirectorId)

        AppUtils.jsonObjectString = AppUtils.jsonObject.toString()
        AppUtils.requestBody = AppUtils.jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        AppUtils.disposable.add(
            AppUtils.reportsInstitutionDirectorRepository.getReportsForInstitutionDirector(AppUtils.requestBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<ReportsInstitutionDirector>>(){
                    override fun onSuccess(t: List<ReportsInstitutionDirector>) {
                        reportList.value = t
                    }

                    override fun onError(e: Throwable) {
                        errorMessage.value = e.localizedMessage
                    }
                })
        )
    }
}