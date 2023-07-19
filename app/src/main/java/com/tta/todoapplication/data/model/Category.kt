package com.tta.todoapplication.data.model

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
