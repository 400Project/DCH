package com.oyatech.dch.alerts

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.oyatech.dch.R

//context extension method that produces snackbar for a feedback
public fun Context.snackForError(view:View, errorMessage:String){
    Snackbar.make(view,errorMessage, Snackbar.LENGTH_LONG *3)
        .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
        .setBackgroundTint(resources.getColor(R.color.purple_500))
        .show()
}


public fun isEmptyView(view:TextInputEditText):Boolean{
    if (view.text!!.isEmpty()){
        view.error = "Please Enter data"
        return true
    }else
      return  false
}


