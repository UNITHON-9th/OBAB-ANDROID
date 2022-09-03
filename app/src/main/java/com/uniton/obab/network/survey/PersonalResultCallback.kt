package com.uniton.obab.network.survey

import com.uniton.obab.model.PersonalResultRepository

interface PersonalResultCallback {
    fun onSuccess(result: PersonalResultRepository)
    fun onFailure(result: String)
}