package com.codecanyon.umes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FacultyDirector(
    val facultyDirectorId: Int = 1,
    val facultyDirectorFullName: String = "",
    val facultyId: Int = 1,
    val facultyDepartmenName: String = ""
) : Parcelable
