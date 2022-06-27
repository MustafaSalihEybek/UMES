package com.codecanyon.umes.model.deparmentdirector

data class StudentDepartmentDirector(
    val studentId: String = "",
    val studentName: String = "",
    val studentSurname: String = "",
    val studentCoordinatorId: Int = 1,
    val studentCoordinatorName: String = "",
    val studentInstitutionName: String = "",
    val studentInstitutionId: Int = 1,
    val studentInstitutionDirectorId: Int = 1,
    val studentPhone: String = "",
    val studentEmail: String = ""
)
