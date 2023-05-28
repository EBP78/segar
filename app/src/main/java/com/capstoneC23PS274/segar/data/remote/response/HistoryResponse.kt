package com.capstoneC23PS274.segar.data.remote.response

data class HistoryResponse (
    val success: Boolean
)

data class HistoryResponseItem (
    val id: String,
    val name: String,
    val ImageUrl: String,
    val result: String,
    val date: String
)