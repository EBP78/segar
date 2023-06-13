package com.capstoneC23PS274.segar.data.remote.response

import com.google.gson.annotations.SerializedName

data class CommonResponse(

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
