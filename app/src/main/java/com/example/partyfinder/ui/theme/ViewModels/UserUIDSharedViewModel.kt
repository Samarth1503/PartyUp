package com.example.partyfinder.ui.theme.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserUIDSharedViewModel: ViewModel() {
    var _currentUserUID = MutableLiveData<String>()
    val currentUserUID :LiveData<String> get() = _currentUserUID

    fun updateCurrentUserUID(newValue : String){
        _currentUserUID.value = newValue
    }

}