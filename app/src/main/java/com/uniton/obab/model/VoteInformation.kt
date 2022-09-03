package com.uniton.obab.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VoteInformation(
    val isReader: Boolean = false,
    val roomNo: String = "",
    val countryId: Int = 0,
    val typeId: Int = 0,
    val isSpicy: Boolean = false,
    val isSoup: Boolean = false,
    val isHot: Boolean = false,
) : Parcelable
