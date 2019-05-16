package com.sinigr.usersapp.network.network_manager

typealias OnSuccessWithoutData = () -> Unit
typealias OnSuccessWithData <T> = (T) -> Unit
typealias OnError = (Int, String) -> Unit