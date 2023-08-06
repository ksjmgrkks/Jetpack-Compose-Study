package com.kks.readerapp.model


data class Offer(
    val finskyOfferType: Int,
    val giftable: Boolean,
    val listPrice: ListPriceX,
    val retailPrice: RetailPrice
)