package com.fernando.mydogs.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Dog(
    @DrawableRes val dogImage: Int,
    @StringRes val dogName: Int,
    val age: Int,
    @StringRes val hobby: Int
)
