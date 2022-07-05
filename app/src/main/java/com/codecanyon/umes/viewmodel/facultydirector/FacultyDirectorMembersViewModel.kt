package com.codecanyon.umes.viewmodel.facultydirector

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.codecanyon.umes.model.facultydirector.MemberFacultyDirector
import com.codecanyon.umes.repository.facultydirector.MembersFacultyDirectorRepository
import com.codecanyon.umes.util.AppUtils
import com.codecanyon.umes.viewmodel.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class FacultyDirectorMembersViewModel(application: Application) : BaseViewModel(application) {
    val memberList = MutableLiveData<List<MemberFacultyDirector>>()
    val errorMessage = MutableLiveData<String>()

    fun getMembers(facultyDirectorId: Int){
        AppUtils.membersFacultyDirectorRepository = MembersFacultyDirectorRepository()
        AppUtils.disposable = CompositeDisposable()

        AppUtils.jsonObject = JSONObject()
        AppUtils.jsonObject.put("facultyDirectorId", facultyDirectorId)

        AppUtils.jsonObjectString = AppUtils.jsonObject.toString()
        AppUtils.requestBody = AppUtils.jsonObjectString.toRequestBody("Application/json".toMediaTypeOrNull())

        AppUtils.disposable.add(
            AppUtils.membersFacultyDirectorRepository.getMembersForFacultyDirector(AppUtils.requestBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<MemberFacultyDirector>>(){
                    override fun onSuccess(t: List<MemberFacultyDirector>) {
                        memberList.value = t
                    }

                    override fun onError(e: Throwable) {
                        errorMessage.value = e.localizedMessage
                    }
                })
        )
    }
}