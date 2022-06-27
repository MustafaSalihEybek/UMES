package com.codecanyon.umes.model.deparmentdirector

data class MemberActivationDepartmentDirector(
    val teacherId: Int = 1,
    val teacherName: String = "",
    val teacherSurname: String = "",
    val teacherEmail: String = "",
    val teacherIsActive: Int = 1,
    val teacherPhone: String = ""
)
