package com.uniton.obab.network.sendChoice

interface SendUserChoiceCallback {
    fun onSuccess()
    fun onFail(errMsg: String?)
}
