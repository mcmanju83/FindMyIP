package com.manju.mylibrary.uistate

import com.manju.mylibrary.data.IpInfo

data class FindIPState(
    val ipInfoItems: IpInfo? = null,
    val isLoading: Boolean = false
)