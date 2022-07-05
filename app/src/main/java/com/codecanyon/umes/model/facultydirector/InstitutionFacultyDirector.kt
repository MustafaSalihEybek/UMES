package com.codecanyon.umes.model.facultydirector

data class InstitutionFacultyDirector(
    val institutionsId: Int = 0,
    val institutionsName: String = "",
    val institutionsAddress: String = "",
    val institutionsPhone: String = "",
    val institutionsDepartmentId: Int = 0,
    val institutionsDepartmentName: String = "",
    val institutionsDirectorName: String,
    val institutionsStudents: List<InstitutionStudentFacultyDirector>
)
