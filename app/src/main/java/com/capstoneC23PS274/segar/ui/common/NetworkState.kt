package com.capstoneC23PS274.segar.ui.common

sealed class NetworkState<out T: Any?> {
    object NetworkAvailable : NetworkState<Nothing>()
    object NetworkUnavailable : NetworkState<Nothing>()
    object NetworkLost : NetworkState<Nothing>()
    object NetworkLosing : NetworkState<Nothing>()
}