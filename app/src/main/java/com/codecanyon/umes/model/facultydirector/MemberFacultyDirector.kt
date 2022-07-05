package com.codecanyon.umes.model.facultydirector

data class MemberFacultyDirector(
    val teacherId: Int = 0,
    val teacherName: String = "",
    val teacherSurname: String = "",
    val teacherEmail: String = "",
    val teacherIsActive: Int = 0,
    val teacherPhone: String = "",
    val teacherDepartmentName: String = ""
)
