package com.oyatech.dch.admin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oyatech.dch.database.Repository
import com.oyatech.dch.model.Staff
import kotlinx.coroutines.launch

class StaffViewModel(application:Application)
    : AndroidViewModel(application),IStaff {
private var _repository:Repository? = null
    private val repository get() = _repository!!
        init {
           _repository = Repository(application)
        }

    override fun addStaff(staff: Staff) {

        viewModelScope.launch {
            repository.addStaff(staff)
        }
    }

    override fun fetchStaff(): LiveData<MutableList<Staff>> {

   var staff: LiveData<MutableList<Staff>> = MutableLiveData()
        viewModelScope.launch {
       staff =     repository.fetchStaff()
        }
      return staff
    }


}