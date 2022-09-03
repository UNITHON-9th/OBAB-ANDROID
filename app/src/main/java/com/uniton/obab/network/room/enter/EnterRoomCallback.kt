package com.uniton.obab.network.room.enter

import com.uniton.obab.model.EnterRoomRepository

interface EnterRoomCallback {
    fun onSuccess(result: EnterRoomRepository)
    fun onFailure(result: EnterRoomRepository)
}