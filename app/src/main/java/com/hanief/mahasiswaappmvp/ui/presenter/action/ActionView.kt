package com.hanief.mahasiswaappmvp.ui.presenter.action

import com.hanief.mahasiswaappmvp.ui.model.action.ResponseAction

interface ActionView {

    fun successInput(response: ResponseAction)
    fun successUpdate(response: ResponseAction)
    fun errorAction(msg: String)
    fun empty()
}