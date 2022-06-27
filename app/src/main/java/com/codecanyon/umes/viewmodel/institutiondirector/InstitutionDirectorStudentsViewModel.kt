package com.codecanyon.umes.viewmodel.institutiondirector

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.codecanyon.umes.model.institutiondirector.StudentsInstitutionDirector
import com.codecanyon.umes.repository.institutiondirector.StudentsInstitutionDirectorRepository
import com.codecanyon.umes.util.AppUtils
import com.codecanyon.umes.viewmodel.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class InstitutionDirectorStudentsViewModel(application: Application) : BaseViewModel(application) {
    val studentList = MutableLiveData<List<StudentsInstitutionDirector>>()
    val errorMessage = MutableLiveData<String>()

    fun getStudents(institutionDirectorId: Int){
        AppUtils.studentsInstitutionDirectorRepository = StudentsInstitutionDirectorRepository()
        AppUtils.disposable = CompositeDisposable()

        AppUtils.jsonObject = JSONObject()
        AppUtils.jsonObject.put("institutionDirectorId", institutionDirectorId)

        AppUtils.jsonObjectString = AppUtils.jsonObject.toString()
        AppUtils.requestBody = AppUtils.jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        AppUtils.disposable.add(
            AppUtils.studentsInstitutionDirectorRepository.getStudentsForInstitutionDirector(AppUtils.requestBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<StudentsInstitutionDirector>>(){
                    override fun onSuccess(t: List<StudentsInstitutionDirector>) {
                        studentList.value = t
                    }

                    override fun onError(e: Throwable) {
                        errorMessage.value = e.localizedMessage
                    }
                })
        )
    }
}