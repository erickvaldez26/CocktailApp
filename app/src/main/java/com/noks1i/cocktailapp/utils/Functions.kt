package com.noks1i.cocktailapp.utils

import java.util.*

object Functions {
    fun randomInt(range: IntRange): Int {
        var r = Random()
        var randomInt = r.nextInt(range.last - range.first) + range.first
        return randomInt
    }
}