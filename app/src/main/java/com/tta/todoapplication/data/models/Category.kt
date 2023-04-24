package com.tta.todoapplication.data.models

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
class Category(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var numberColor : Int,
    var numberIcon : Int
) : Parcelable
