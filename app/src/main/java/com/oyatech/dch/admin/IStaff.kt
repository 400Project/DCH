package com.oyatech.dch.admin

import androidx.lifecycle.LiveData
import com.oyatech.dch.model.Staff

interface IStaff {

    fun addStaff(staff: Staff)
    fun fetchStaff(): LiveData<MutableList<Staff>>
}