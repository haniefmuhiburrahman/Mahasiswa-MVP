package com.hanief.mahasiswaappmvp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hanief.mahasiswaappmvp.R
import com.hanief.mahasiswaappmvp.ui.model.action.ResponseAction
import com.hanief.mahasiswaappmvp.ui.model.getData.DataItem
import com.hanief.mahasiswaappmvp.ui.presenter.action.ActionPresenter
import com.hanief.mahasiswaappmvp.ui.presenter.action.ActionView
import kotlinx.android.synthetic.main.activity_input.*

class InputActivity : AppCompatActivity(), ActionView {

    private var actionPresenter: ActionPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        actionPresenter = ActionPresenter(this)

        val getData = intent.getParcelableExtra<DataItem>("data")

        if (getData != null) {
            et_nama.setText(getData.mahasiswaNama)
            et_nohp.setText(getData.mahasiswaNohp)
            et_alamat.setText(getData.mahasiswaAlamat)

            bt_simpan.text = "Update"
            tv_title.text = "Update Data"
        }

        when(bt_simpan.text) {
            "Update" -> {
                bt_simpan.setOnClickListener {
                    actionPresenter?.updateData(getData?.idMahasiswa, et_nama.text.toString(), et_nohp.text.toString(), et_alamat.text.toString())
                }
            } else -> {
                tv_title.text = "Insert Data"
                bt_simpan.setOnClickListener {
                    actionPresenter?.inputData(et_nama.text.toString(), et_nohp.text.toString(), et_alamat.text.toString())
                }
            }
        }

        bt_batal.setOnClickListener {
            finish()
        }
    }

    override fun successInput(response: ResponseAction) {
        Toast.makeText(this, "Data berhasil diinput", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun successUpdate(response: ResponseAction) {
        Toast.makeText(this, "Data berhasil diupdate", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun errorAction(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun empty() {
        Toast.makeText(this, "Tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show()
    }
}