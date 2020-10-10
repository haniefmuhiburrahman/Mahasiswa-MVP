package com.hanief.mahasiswaappmvp.ui.presenter.getdata

import com.hanief.mahasiswaappmvp.network.ConfigNetwork
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class GetDataPresenter (val getDataView: GetDataView) {

    fun showData() {
        ConfigNetwork.getRetrofit().getData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response ->
                if (response.isSuccess == true) {
                    getDataView.successGetData(response.message ?: "Data kosong", response.data)
                }
            }, {
                getDataView.getError(it.localizedMessage)
            })
    }

    fun hapusData(id: String?) {

        ConfigNetwork.getRetrofit().deleteData(id ?: "").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                getDataView.successDelete("Data berhasil dihapus")
                showData()
            }, {
                getDataView.getError(it.localizedMessage)
            })
    }
}