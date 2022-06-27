package com.codecanyon.umes.viewmodel.coordinator

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.codecanyon.umes.model.coordinator.StudentCoordinator
import com.codecanyon.umes.repository.coordinator.StudentsCoordinatorRepository
import com.codecanyon.umes.util.AppUtils
import com.codecanyon.umes.viewmodel.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class CoordinatorStudentsViewModel(application: Application) : BaseViewModel(application) {
    val errorMessage = MutableLiveData<String>()
    val studentList = MutableLiveData<List<StudentCoordinator>>()

    fun getStudents(coordinatorId: Int, departmentId: Int){
        AppUtils.studentsCoordinatorRepository = StudentsCoordinatorRepository()
        AppUtils.disposable = CompositeDisposable()

        AppUtils.jsonObject = JSONObject()
        AppUtils.jsonObject.put("studentCoordinatorId", coordinatorId)
        AppUtils.jsonObject.put("studentDepartmenId", departmentId)

        AppUtils.jsonObjectString = AppUtils.jsonObject.toString()
        AppUtils.requestBody = AppUtils.jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        AppUtils.disposable.add(
            AppUtils.studentsCoordinatorRepository.getStudentsForCoordinator(AppUtils.requestBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<StudentCoordinator>>(){
                    override fun onSuccess(t: List<StudentCoordinator>) {
                        studentList.value = t
                    }

                    override fun onError(e: Throwable) {
                        errorMessage.value = e.localizedMessage
                    }
                })
        )
    }
}