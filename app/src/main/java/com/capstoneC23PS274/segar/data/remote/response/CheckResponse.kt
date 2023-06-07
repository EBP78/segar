package com.capstoneC23PS274.segar.data.remote.response

import com.google.gson.annotations.SerializedName

data class CheckResponse(

	@field:SerializedName("data")
	val data: CheckResult? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class CheckResult(

	@field:SerializedName("score")
	val score: Double? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("creator")
	val creator: String? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null
)
