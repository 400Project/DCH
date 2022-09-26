package com.oyatech.dch.alerts

import android.content.Context
import android.view.View
import android.widget.EditText
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_FADE
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.oyatech.dch.R

//context extension method that produces snackbar for a feedback
public fun Context.snackForError(view:View, errorMessage:String){
    Snackbar.make(view,errorMessage, Snackbar.LENGTH_LONG)
        .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
        .setBackgroundTint(resources.getColor(R.color.purple_500))
        .show()
}

fun emptyView(view:TextInputEditText):Boolean{
    if (view.text!!.isEmpty()){
        view.error = "Please Enter data"
        return true
    }else
      return  false
}
