package com.codecanyon.umes.viewmodel.facultydirector

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.codecanyon.umes.model.facultydirector.SectionFacultyDirector
import com.codecanyon.umes.repository.facultydirector.SectionsFacultyDirectorRepository
import com.codecanyon.umes.util.AppUtils
import com.codecanyon.umes.viewmodel.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class FacultyDirectorDepartmentsViewModel(application: Application) : BaseViewModel(application) {
    val sectionList = MutableLiveData<List<SectionFacultyDirector>>()
    val errorMessage = MutableLiveData<String>()

    fun getSections(facultyDirectorId: Int){
        AppUtils.sectionsFacultyDirectorRepository = SectionsFacultyDirectorRepository()
        AppUtils.disposable = CompositeDisposable()

        AppUtils.jsonObject = JSONObject()
        AppUtils.jsonObject.put("facultyDirectorId", facultyDirectorId)

        AppUtils.jsonObjectString = AppUtils.jsonObject.toString()
        AppUtils.requestBody = AppUtils.jsonObjectString.toRequestBody("Application/json".toMediaTypeOrNull())

        AppUtils.disposable.add(
            AppUtils.sectionsFacultyDirectorRepository.getSectionsForFacultyDirector(AppUtils.requestBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<SectionFacultyDirector>>(){
                    override fun onSuccess(t: List<SectionFacultyDirector>) {
                        sectionList.value = t
                    }

                    override fun onError(e: Throwable) {
                        errorMessage.value = e.localizedMessage
                    }
                })
        )
    }
}