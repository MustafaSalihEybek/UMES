package com.codecanyon.umes.viewmodel.departmentdirector

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.codecanyon.umes.model.deparmentdirector.StudentPaperDepartmentDirector
import com.codecanyon.umes.repository.departmentdirector.StudentPaperDepartmentDirectorRepository
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

class DepartmentDirectorStudentPaperViewModel(application: Application) : BaseViewModel(application) {
    val errorMessage = MutableLiveData<String>()
    val studentPaperList = MutableLiveData<List<StudentPaperDepartmentDirector>>()

    fun getStudentPapers(teacherDepartmentId: Int){
        AppUtils.studentPaperDepartmentDirectorRepository = StudentPaperDepartmentDirectorRepository()
        AppUtils.disposable = CompositeDisposable()

        AppUtils.jsonObject = JSONObject()
        AppUtils.jsonObject.put("teacherDepartmentId", teacherDepartmentId)

        AppUtils.jsonObjectString = AppUtils.jsonObject.toString()
        AppUtils.requestBody = AppUtils.jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        AppUtils.disposable.add(
            AppUtils.studentPaperDepartmentDirectorRepository.getStudentPaperForDepartmentDirector(AppUtils.requestBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<StudentPaperDepartmentDirector>>(){
                    override fun onSuccess(t: List<StudentPaperDepartmentDirector>) {
                        studentPaperList.value = t
                    }

                    override fun onError(e: Throwable) {
                        errorMessage.value = e.localizedMessage
                    }
                })
        )
    }
}