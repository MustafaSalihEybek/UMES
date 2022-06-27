package com.codecanyon.umes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Institution (
    val instNumber: String = "",
    val instName: String = "",
    val instAddress: String = ""
) : Parcelable