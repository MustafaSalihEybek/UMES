package com.codecanyon.umes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coordinator (
    val teacherId: Int = 1,
    val teacherFullName: String = "",
    val teacherDepartmenId: Int = 1,
    val teacherDepartmenName: String = "",
    val teacherPassword: String = "",
    val teacherPhone: String = "",
    val teacherEmail: String = ""
) : Parcelable