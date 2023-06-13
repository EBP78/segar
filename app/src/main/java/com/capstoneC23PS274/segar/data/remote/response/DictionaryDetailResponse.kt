package com.capstoneC23PS274.segar.data.remote.response

import com.google.gson.annotations.SerializedName

data class DictionaryDetailResponse(

	@field:SerializedName("data")
	val data: DictDetailItem? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DictDetailItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("origin")
	val origin: String? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("brief_desc")
	val briefDesc: String? = null,

	@field:SerializedName("scientific_name")
	val scientificName: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("consumable_part")
	val consumablePart: String? = null,

	@field:SerializedName("famili")
	val famili: String? = null
)
