package com.codecanyon.umes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Student(
    val studentId: String = "",
    val studentFullName: String = "",
    val studentDepartmenId: Int = 1,
    val studentCoordinator: Int = 1,
    val studentDepartmenName: String = "",
    val studentPassword: String = ""
) : Parcelable
