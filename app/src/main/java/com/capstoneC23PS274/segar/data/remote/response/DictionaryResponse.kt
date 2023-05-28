package com.capstoneC23PS274.segar.data.remote.response

data class DictionaryResponse (
    val success: Boolean
)

data class DictionaryItemData (
    val id: String,
    val name: String,
    val nameLatin: String,
    val ImageUrl: String,
    val famili: String,
    val konsumsi: String,
    val otherInformation: String
)