package com.bawp.jettip_test.util

fun calculateTotalPerPerson(
    enterBill: Double,
    beverageCount: Int,
    personCount: Int,
) = if(enterBill > 0)(enterBill + (beverageCount * 3000)) / personCount else 0.0