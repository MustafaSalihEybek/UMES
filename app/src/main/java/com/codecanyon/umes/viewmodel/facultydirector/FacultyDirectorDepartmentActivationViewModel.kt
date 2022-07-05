package com.codecanyon.umes.viewmodel.facultydirector

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.codecanyon.umes.model.facultydirector.SectionActivationFacultyDirector
import com.codecanyon.umes.repository.facultydirector.SectionActivationsFacultyDirectorRepository
import com.codecanyon.umes.util.AppUtils
import com.codecanyon.umes.viewmodel.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class FacultyDirectorDepartmentActivationViewModel(application: Application) : BaseViewModel(application) {
    val sectionActivationList = MutableLiveData<List<SectionActivationFacultyDirector>>()
    val errorMessage = MutableLiveData<String>()

    fun getSectionActivationList(facultyDirectorId: Int){
        AppUtils.sectionActivationsFacultyDirectorRepository = SectionActivationsFacultyDirectorRepository()
        AppUtils.disposable = CompositeDisposable()

        AppUtils.jsonObject = JSONObject()
        AppUtils.jsonObject.put("facultyDirectorId", facultyDirectorId)

        AppUtils.jsonObjectString = AppUtils.jsonObject.toString()
        AppUtils.requestBody = AppUtils.jsonObjectString.toRequestBody("Application/json".toMediaTypeOrNull())

        AppUtils.disposable.add(
            AppUtils.sectionActivationsFacultyDirectorRepository.getSectionsActivationForFacultyDirector(AppUtils.requestBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<SectionActivationFacultyDirector>>(){
                    override fun onSuccess(t: List<SectionActivationFacultyDirector>) {
                        sectionActivationList.value = t
                    }

                    override fun onError(e: Throwable) {
                        errorMessage.value = e.localizedMessage
                    }
                })
        )
    }
}