package com.oyatech.dch.alerts

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_DIAL
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher

import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.oyatech.dch.R

//context extension method that produces snackbar for a feedback
public fun Context.snackForError(view: View, feedback: String) {
    Snackbar.make(view, feedback, Snackbar.LENGTH_LONG * 4)
        .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
        .setTextColor(resources.getColor(R.color.white))
        .setBackgroundTint(resources.getColor(com.google.android.material.R.color.m3_ref_palette_black))
        .show()
}

//Empty EditText checker
public fun isEmptyView(view: TextInputEditText): Boolean {
    if (view.text!!.isEmpty()) {
        view.error = "Please Enter data"
        return true
    } else
        return false
}

//An intent to WhatsApp chat
public fun Context.whatsApp() {
    val url = buildString {
        append("https://api.whatsapp.com/send?phone=$+233242663981")
    }
    val i = Intent(ACTION_VIEW)
    i.data = Uri.parse(url)
    startActivity(i)

    toaster(getString(R.string.whatsapp_chat))

}

//a call intent from likely patient of the hospital
fun Context.call() {
    val callIntent = Intent(ACTION_DIAL).apply {
        data = Uri.parse(getString(R.string.call))
    }
    if (callIntent.resolveActivity(this.packageManager) != null) {
        this.startActivity(callIntent)
    }

    //making a toast feedback to the patient
    toaster(getString(R.string.calling))
}

//a feedback toast for most actions
fun Context.toaster(ms: String) {
    Toast.makeText(
        this,
        ms,
        Toast.LENGTH_LONG * 3
    ).show()
}
//intent to show dch location through google map
fun Context.location(){
    val callIntent = Intent(ACTION_VIEW).apply {
        data = Uri.parse(getString(R.string.goe_location))
    }
    if (callIntent.resolveActivity(this.packageManager) != null) {
        this.startActivity(callIntent)
    }
    toaster(getString(R.string.location))
}


fun Context.mobileLength(text:TextInputEditText){
    text.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            text.error = "Enter valid Mobile"
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (text.text?.length != 10){
                text.error = "Mobile No. Invalid"
            }else
                text.error = "Mobile No. valid"
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    })

}

 fun trimText(editText: TextInputEditText): String {
    return editText.text.toString().trim()
}
