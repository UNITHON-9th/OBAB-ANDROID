package com.uniton.obab.network.room

import com.uniton.obab.model.Repository
import com.uniton.obab.model.RoomRepository

interface RoomsCallback {
    fun onSuccess(result: RoomRepository)
    fun onFailure(result: RoomRepository)
}