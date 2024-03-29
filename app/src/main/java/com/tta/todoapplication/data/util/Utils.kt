package com.tta.todoapplication.data.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.tta.todoapplication.R

fun hideKeyboard(activity: Activity) {
    val inputMethodManager =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    val currentFocusedView = activity.currentFocus
    currentFocusedView?.let {
        inputMethodManager.hideSoftInputFromWindow(
            currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>){
    observe(lifecycleOwner, object : Observer<T>{
        override fun onChanged(t: T) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}

fun convertDataColorCategory(numberColor : Int): Int? {
    var color : Int? = null
    when(numberColor){
        0 -> color = R.color.colorPrimary
    }
    return color
}

fun convertDataIconCategory(numberIcon : Int): Int? {
    var color : Int? = null
    when(numberIcon){
        0 -> color = R.drawable.flag
    }
    return color
}