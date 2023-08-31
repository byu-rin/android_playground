package com.example.applemarket

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SaleItem (
    val Image: Int,
    val ItemTitle: String,
    val ItemDetail: String,
    val SellerName: String,
    val Prices: Int,
    val Address: String,
    val InterestCnt: Int,
    val ChatCnt: Int,
    var isLike: Boolean
) : Parcelable