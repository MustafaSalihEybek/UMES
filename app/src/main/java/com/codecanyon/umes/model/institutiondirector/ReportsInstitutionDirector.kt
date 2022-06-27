package com.codecanyon.umes.model.institutiondirector

data class ReportsInstitutionDirector (
    val studentId: String = "",
    val studentName: String = "",
    val studentSurname: String = "",
    val studentEmail: String = "",
    val studentPhone: String = "",
    val studentCoordinatorId : Int = 1,
    val studentCoordinatorName: String = ""
)