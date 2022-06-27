package com.codecanyon.umes.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.codecanyon.umes.util.AppUtils
import com.codecanyon.umes.model.Coordinator
import com.codecanyon.umes.model.FacultyDirector
import com.codecanyon.umes.model.InsDirector
import com.codecanyon.umes.model.Student
import com.codecanyon.umes.repository.LoginRepository
import com.codecanyon.umes.viewmodel.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class SignInViewModel(application: Application) : BaseViewModel(application) {
    val successMessage = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()
    val studentData = MutableLiveData<Student>()
    val coordinatorData = MutableLiveData<Coordinator>()
    val insDirectorData = MutableLiveData<InsDirector>()
    val facultyDirectorData = MutableLiveData<FacultyDirector>()

    fun loginToUser(userName: String, userPassword: String, loginType: String){
        AppUtils.loginRepository = LoginRepository()
        AppUtils.disposable = CompositeDisposable()

        AppUtils.jsonObject = JSONObject()
        AppUtils.jsonObject.put("userName", userName)
        AppUtils.jsonObject.put("userPassword", userPassword)
        AppUtils.jsonObject.put("loginType", loginType)

        AppUtils.jsonObjectString = AppUtils.jsonObject.toString()
        AppUtils.requestBody = AppUtils.jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        AppUtils.disposable.add(
            AppUtils.loginRepository.loginToUser(AppUtils.requestBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Map<String, Any>>(){
                    override fun onSuccess(t: Map<String, Any>) {
                        successMessage.value = (t.get("message") as String)

                        if (loginType == "1"){
                            AppUtils.mStudent = Student(
                                t.get("studentId").toString(),
                                t.get("studentFullName").toString(),
                                t.get("studentDepartmenId").toString().toInt(),
                                t.get("studentCoordinator").toString().toInt(),
                                t.get("studentDepartmenName").toString()
                            )

                            studentData.value = AppUtils.mStudent
                        } else if (loginType == "2" || loginType == "4"){
                            AppUtils.mCoordinator = Coordinator(
                                t.get("teacherId").toString().toInt(),
                                t.get("teacherFullName").toString(),
                                t.get("teacherDepartmenId").toString().toInt(),
                                t.get("teacherDepartmenName").toString(),
                                t.get("teacherPassword").toString(),
                                t.get("teacherPhone").toString(),
                                t.get("teacherEmail").toString()
                            )

                            coordinatorData.value = AppUtils.mCoordinator
                        } else if (loginType == "3"){
                            AppUtils.mInsDirector = InsDirector(
                                t.get("insDirectorId").toString().toInt(),
                                t.get("insDirectorFullName").toString(),
                                t.get("insDirectorDepartmenId").toString().toInt(),
                                t.get("insDirectorDepartmenName").toString(),
                                t.get("insDirectorPassword").toString(),
                                t.get("insDirectorPhone").toString(),
                                t.get("insDirectorEmail").toString()
                            )

                            insDirectorData.value = AppUtils.mInsDirector
                        } else if (loginType == "5"){
                            AppUtils.mFacultyDirector = FacultyDirector(
                                t.get("facultyDirectorId").toString().toInt(),
                                t.get("facultyDirectorFullName").toString(),
                                t.get("facultyId").toString().toInt(),
                                t.get("facultyDepartmenName").toString()
                            )

                            facultyDirectorData.value = AppUtils.mFacultyDirector
                        }
                    }

                    override fun onError(e: Throwable) {
                        errorMessage.value = e.localizedMessage
                    }
                })
        )
    }
}