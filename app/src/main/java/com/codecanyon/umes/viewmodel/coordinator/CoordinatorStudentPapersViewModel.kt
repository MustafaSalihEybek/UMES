package com.codecanyon.umes.viewmodel.coordinator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.codecanyon.umes.model.coordinator.StudentPaperCoordinator
import com.codecanyon.umes.repository.coordinator.StudentPapersCoordinatorRepository
import com.codecanyon.umes.util.AppUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class CoordinatorStudentPapersViewModel(application: Application) : AndroidViewModel(application) {
    val errorMessage = MutableLiveData<String>()
    val studentPaperList = MutableLiveData<List<StudentPaperCoordinator>>()

    fun getStudentPapers(teacherId: Int, teacherDepartmentId: Int){
        AppUtils.studentPapersCoordinatorRepository = StudentPapersCoordinatorRepository()
        AppUtils.disposable = CompositeDisposable()

        AppUtils.jsonObject = JSONObject()
        AppUtils.jsonObject.put("teacherId", teacherId)
        AppUtils.jsonObject.put("teacherDepartmentId", teacherDepartmentId)

        AppUtils.jsonObjectString = AppUtils.jsonObject.toString()
        AppUtils.requestBody = AppUtils.jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        AppUtils.disposable.add(
            AppUtils.studentPapersCoordinatorRepository.getStudentPapersForCoordinator(AppUtils.requestBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<StudentPaperCoordinator>>(){
                    override fun onSuccess(t: List<StudentPaperCoordinator>) {
                        studentPaperList.value = t
                    }

                    override fun onError(e: Throwable) {
                        errorMessage.value = e.localizedMessage
                    }
                })
        )
    }
}