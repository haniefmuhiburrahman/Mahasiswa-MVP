package com.hanief.mahasiswaappmvp.ui.model.getData

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseGetData(

	@field:SerializedName("data")
	val data: List<DataItem>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
) : Parcelable

@Parcelize
data class DataItem(

	@field:SerializedName("mahasiswa_alamat")
	val mahasiswaAlamat: String? = null,

	@field:SerializedName("mahasiswa_nohp")
	val mahasiswaNohp: String? = null,

	@field:SerializedName("id_mahasiswa")
	val idMahasiswa: String? = null,

	@field:SerializedName("mahasiswa_nama")
	val mahasiswaNama: String? = null
) : Parcelable
