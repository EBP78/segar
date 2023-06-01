package com.capstoneC23PS274.segar.data.remote.response

import com.google.gson.annotations.SerializedName

data class DictionaryResponse(

	@field:SerializedName("data")
	val data: List<DictionaryItem>,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DictionaryItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("scientific_name")
	val scientificName: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("consumable_part")
	val consumablePart: String? = null,

	@field:SerializedName("famili")
	val famili: String? = null
)
