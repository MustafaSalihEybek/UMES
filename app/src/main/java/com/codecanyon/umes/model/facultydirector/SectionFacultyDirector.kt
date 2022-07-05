package com.codecanyon.umes.model.facultydirector

data class SectionFacultyDirector(
    val departmentId: Int = 0,
    val departmentName: String = "",
    val departmentDirectorId: Int = 0,
    val departmentDirectorName: String = "",
    val departmentDirectorEmail: String = "",
    val departmentIsActive: Int = 0
)
