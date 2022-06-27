package com.codecanyon.umes.viewmodel.departmentdirector

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.codecanyon.umes.model.deparmentdirector.MemberDepartmentDirector
import com.codecanyon.umes.repository.departmentdirector.MembersDepartmentDirectorRepository
import com.codecanyon.umes.util.AppUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class DepartmentDirectorMembersViewModel(application: Application) : AndroidViewModel(application) {
    val errorMessage = MutableLiveData<String>()
    val memberList = MutableLiveData<List<MemberDepartmentDirector>>()

    fun getMembers(teacherDepartmentId: Int){
        AppUtils.membersDepartmentDirectorRepository = MembersDepartmentDirectorRepository()
        AppUtils.disposable = CompositeDisposable()

        AppUtils.jsonObject = JSONObject()
        AppUtils.jsonObject.put("teacherDepartmentId", teacherDepartmentId)

        AppUtils.jsonObjectString = AppUtils.jsonObject.toString()
        AppUtils.requestBody = AppUtils.jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        AppUtils.disposable.add(
            AppUtils.membersDepartmentDirectorRepository.getMembersForDepartmentDirector(AppUtils.requestBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<MemberDepartmentDirector>>(){
                    override fun onSuccess(t: List<MemberDepartmentDirector>) {
                        memberList.value = t
                    }

                    override fun onError(e: Throwable) {
                        errorMessage.value = e.localizedMessage
                    }
                })
        )
    }
}