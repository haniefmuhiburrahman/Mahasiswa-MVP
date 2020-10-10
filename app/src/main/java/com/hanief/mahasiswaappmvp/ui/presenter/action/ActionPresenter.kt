package com.hanief.mahasiswaappmvp.ui.presenter.action

import com.hanief.mahasiswaappmvp.network.ConfigNetwork
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ActionPresenter(val actionView: ActionView) {

    fun inputData(nama: String, nohp: String, alamat: String) {

        if (nama.isNotEmpty() && nohp.isNotEmpty() && alamat.isNotEmpty()) {

            val input = ConfigNetwork.getRetrofit().insertData(nama, nohp, alamat).subscribeOn(Schedulers.io())

            input.observeOn(AndroidSchedulers.mainThread())
                .subscribe( { response->
                    if (response.isSuccess == true) {
                        actionView.successInput(response)
                    } else {
                        actionView.errorAction(response.message ?: "")
                    }
                }, {
                    actionView.errorAction(it.localizedMessage)
                })
        } else {
            actionView.empty()
        }
    }

    fun updateData(id: String?, nama: String,nohp: String, alamat: String) {

        if (nama.isNotEmpty() && nohp.isNotEmpty() && alamat.isNotEmpty()) {

            val update = ConfigNetwork.getRetrofit().updateData(id ?: "", nama ?: "", nohp ?: "", alamat ?: "").subscribeOn(Schedulers.io())

                update.observeOn(AndroidSchedulers.mainThread())
                    .subscribe( { response ->
                        if (response.isSuccess == true) {
                            actionView.successUpdate(response)
                        } else {
                            actionView.errorAction(response.message ?: "")
                        }
                    }, {
                        actionView.errorAction(it.localizedMessage)
                    })
        } else {
            actionView.empty()
        }
    }
}