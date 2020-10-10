package com.hanief.mahasiswaappmvp.ui.presenter.getdata

import com.hanief.mahasiswaappmvp.ui.model.getData.DataItem

interface GetDataView {

    fun successGetData(msg: String, data: List<DataItem>?)
    fun successDelete(msg: String)
    fun getError(msg: String)
}