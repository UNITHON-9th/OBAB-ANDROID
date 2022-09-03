package com.uniton.obab.network.room.create

import com.uniton.obab.model.CreateRoomRepository

interface CreateRoomCallback {
    fun onSuccess(result: CreateRoomRepository)
    fun onFailure(result: CreateRoomRepository)
}