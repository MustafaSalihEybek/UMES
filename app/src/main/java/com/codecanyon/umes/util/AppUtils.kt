package com.codecanyon.umes.util

import com.codecanyon.umes.api.UmesAPI
import com.codecanyon.umes.model.Coordinator
import com.codecanyon.umes.model.FacultyDirector
import com.codecanyon.umes.model.InsDirector
import com.codecanyon.umes.model.Student
import com.codecanyon.umes.repository.LoginRepository
import com.codecanyon.umes.repository.coordinator.*
import com.codecanyon.umes.repository.departmentdirector.*
import com.codecanyon.umes.repository.institutiondirector.ReportsInstitutionDirectorRepository
import com.codecanyon.umes.repository.institutiondirector.StudentsInstitutionDirectorRepository
import io.reactivex.disposables.CompositeDisposable
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object AppUtils {
    lateinit var mCoordinator: Coordinator
    lateinit var mStudent: Student
    lateinit var mInsDirector: InsDirector
    lateinit var mFacultyDirector: FacultyDirector

    lateinit var loginRepository: LoginRepository

    lateinit var institutionsCoordinatorRepository: InstitutionsCoordinatorRepository
    lateinit var ratingsCoordinatorRepository: RatingsCoordinatorRepository
    lateinit var studentPapersCoordinatorRepository: StudentPapersCoordinatorRepository
    lateinit var studentsCoordinatorRepository: StudentsCoordinatorRepository
    lateinit var updateProfileCoordinatorRepository: UpdateProfileCoordinatorRepository

    lateinit var institutionDirectorsDepartmentDirectorRepository: InstitutionDirectorsDepartmentDirectorRepository
    lateinit var institutionsDepartmentDirectorRepository: InstitutionsDepartmentDirectorRepository
    lateinit var membersActivationDepartmentDirectorRepository: MembersActivationDepartmentDirectorRepository
    lateinit var membersDepartmentDirectorRepository: MembersDepartmentDirectorRepository
    lateinit var studentPaperDepartmentDirectorRepository: StudentPaperDepartmentDirectorRepository
    lateinit var studentsDepartmentDirectorRepository: StudentsDepartmentDirectorRepository

    lateinit var studentsInstitutionDirectorRepository: StudentsInstitutionDirectorRepository
    lateinit var reportsInstitutionDirectorRepository: ReportsInstitutionDirectorRepository

    lateinit var disposable: CompositeDisposable
    lateinit var jsonObject: JSONObject
    lateinit var jsonObjectString: String
    lateinit var requestBody: RequestBody

    fun getUmesAPI() : UmesAPI {
        return Retrofit.Builder()
            .baseUrl(Singleton.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(UmesAPI::class.java)
    }
}