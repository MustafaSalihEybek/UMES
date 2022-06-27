package com.codecanyon.umes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InsDirector(
    val insDirectorId: Int = 1,
    val insDirectorFullName: String = "",
    val insDirectorDepartmenId: Int = 1,
    val insDirectorDepartmenName: String = "",
    val insDirectorPassword: String = "",
    val insDirectorPhone: String = "",
    val insDirectorEmail: String = ""
) : Parcelable
