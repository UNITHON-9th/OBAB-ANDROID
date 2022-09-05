package com.uniton.obab.network.result

import com.uniton.obab.model.CreateRoomRepository
import com.uniton.obab.model.response.ResultResponse

interface ResultCallback {
    fun onSuccess(result: ResultResponse)
    fun onFailure(error: String)
}
