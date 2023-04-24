package com.tta.todoapplication.data.models

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
class Priority (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var numberPriorty : Int
): Parcelable