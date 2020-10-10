package com.hanief.mahasiswaappmvp.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.hanief.mahasiswaappmvp.R
import com.hanief.mahasiswaappmvp.ui.adapter.DataAdapter
import com.hanief.mahasiswaappmvp.ui.model.getData.DataItem
import com.hanief.mahasiswaappmvp.ui.presenter.getdata.GetDataPresenter
import com.hanief.mahasiswaappmvp.ui.presenter.getdata.GetDataView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GetDataView {

    private var getDataPresenter: GetDataPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        faButton.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
        }

        getDataPresenter = GetDataPresenter(this)
        getDataPresenter?.showData()
    }

    override fun successGetData(msg: String, list: List<DataItem>?) {

        val adapter = DataAdapter(list, object : DataAdapter.onClickListener {
            override fun detail(item: DataItem?) {
                val intent = Intent(applicationContext, InputActivity::class.java)
                intent.putExtra("data", item)
                startActivity(intent)
            }

            override fun delete(item: DataItem?) {

                AlertDialog.Builder(this@MainActivity).apply {
                    setTitle("Hapus Data")
                    setMessage("Yakin mau hapus data?")
                    setPositiveButton("Hapus") { dialog, which ->
                        getDataPresenter?.hapusData(item?.idMahasiswa)
                        dialog.dismiss()
                    }
                    setNegativeButton("Batal") {dialogInterface, i ->
                        dialogInterface.dismiss()
                    }
                }.show()
            }
        })
        rv_list.adapter = adapter
    }

    override fun successDelete(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun getError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        getDataPresenter?.showData()
    }
}