package com.codecanyon.umes.api

import com.codecanyon.umes.model.Result
import com.codecanyon.umes.model.coordinator.InstitutionCoordinator
import com.codecanyon.umes.model.coordinator.RatingCoordinator
import com.codecanyon.umes.model.coordinator.StudentCoordinator
import com.codecanyon.umes.model.coordinator.StudentPaperCoordinator
import com.codecanyon.umes.model.deparmentdirector.*
import com.codecanyon.umes.model.facultydirector.*
import com.codecanyon.umes.model.institutiondirector.ReportsInstitutionDirector
import com.codecanyon.umes.model.institutiondirector.StudentsInstitutionDirector
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface UmesAPI {
    @POST("login.php")
    fun loginUser(@Body loginBody: RequestBody) : Single<Map<String, Any>>

    @POST("coordinator/students.php")
    fun getStudentsForCoordinator(@Body bodyData: RequestBody) : Single<List<StudentCoordinator>>

    @POST("coordinator/institutions.php")
    fun getInstitutionsForCoordinator(@Body bodyData: RequestBody) : Single<List<InstitutionCoordinator>>

    @POST("coordinator/rating.php")
    fun getRatingsForCoordinator(@Body bodyData: RequestBody) : Single<List<RatingCoordinator>>

    @POST("coordinator/studentpapers.php")
    fun getStudentPapersForCoordinator(@Body bodyData: RequestBody) : Single<List<StudentPaperCoordinator>>

    @POST("coordinator/updateprofile.php")
    fun updateCoordinatorProfile(@Body bodyData: RequestBody) : Single<Result>

    @POST("departmentdirector/students.php")
    fun getStudentsForDepartmentDirector(@Body bodyData: RequestBody) : Single<List<StudentDepartmentDirector>>

    @POST("departmentdirector/members.php")
    fun getMembersForDepartmentDirector(@Body bodyData: RequestBody) : Single<List<MemberDepartmentDirector>>

    @POST("departmentdirector/membersactivation.php")
    fun getMembersActivationForDepartmentDirector(@Body bodyData: RequestBody) : Single<List<MemberActivationDepartmentDirector>>

    @POST("departmentdirector/studentpapers.php")
    fun getStudentPaperForDepartmentDirector(@Body bodyData: RequestBody) : Single<List<StudentPaperDepartmentDirector>>

    @POST("departmentdirector/institutions.php")
    fun getInstitutionsForDepartmentDirector(@Body bodyData: RequestBody) : Single<List<InstitutionDepartmentDirector>>

    @POST("departmentdirector/institutiondirectors.php")
    fun getInstitutionDirectorsForDepartmentDirector(@Body bodyData: RequestBody) : Single<List<InstitutionDirectorDepartmentDirector>>

    @POST("insdirector/students.php")
    fun getStudentsForInstitutionDirector(@Body bodyData: RequestBody) : Single<List<StudentsInstitutionDirector>>

    @POST("insdirector/reports.php")
    fun getReportsForInstitutionDirector(@Body bodyData: RequestBody) : Single<List<ReportsInstitutionDirector>>

    @POST("facultydirector/students.php")
    fun getStudentsForFacultyDirector(@Body bodyData: RequestBody) : Single<List<StudentsFacultyDirector>>

    @POST("facultydirector/members.php")
    fun getMembersForFacultyDirector(@Body bodyData: RequestBody) : Single<List<MemberFacultyDirector>>

    @POST("facultydirector/sections.php")
    fun getSectionsForFacultyDirector(@Body bodyData: RequestBody) : Single<List<SectionFacultyDirector>>

    @POST("facultydirector/sectionactivation.php")
    fun getSectionActivationsForFacultyDirector(@Body bodyData: RequestBody) : Single<List<SectionActivationFacultyDirector>>

    @POST("facultydirector/institutions.php")
    fun getInstitutionsForFacultyDirector(@Body bodyData: RequestBody) : Single<List<InstitutionFacultyDirector>>
}