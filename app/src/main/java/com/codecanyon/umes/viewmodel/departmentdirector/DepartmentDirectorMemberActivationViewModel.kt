package com.codecanyon.umes.viewmodel.departmentdirector

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.codecanyon.umes.model.deparmentdirector.MemberActivationDepartmentDirector
import com.codecanyon.umes.repository.departmentdirector.MembersActivationDepartmentDirectorRepository
import com.codecanyon.umes.util.AppUtils
import com.codecanyon.umes.viewmodel.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class DepartmentDirectorMemberActivationViewModel(application: Application) : BaseViewModel(application) {
    val errorMessage = MutableLiveData<String>()
    val memberActivationList = MutableLiveData<List<MemberActivationDepartmentDirector>>()

    fun getMembersActivation(teacherDepartmentId: Int){
        AppUtils.membersActivationDepartmentDirectorRepository = MembersActivationDepartmentDirectorRepository()
        AppUtils.disposable = CompositeDisposable()

        AppUtils.jsonObject = JSONObject()
        AppUtils.jsonObject.put("teacherDepartmentId", teacherDepartmentId)

        AppUtils.jsonObjectString = AppUtils.jsonObject.toString()
        AppUtils.requestBody = AppUtils.jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        AppUtils.disposable.add(
            AppUtils.membersActivationDepartmentDirectorRepository.getMembersActivationForDepartmentDirector(AppUtils.requestBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<MemberActivationDepartmentDirector>>(){
                    override fun onSuccess(t: List<MemberActivationDepartmentDirector>) {
                        memberActivationList.value = t
                        println("Size = ${t.size}")
                    }

                    override fun onError(e: Throwable) {
                        errorMessage.value = e.localizedMessage
                    }
                })
        )
    }
}