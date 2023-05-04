package com.innovation.socialauth

import androidx.lifecycle.MutableLiveData
import com.innovation.socialauth.datamodel.UserData

object UserDataSingleton {
    val userData : MutableLiveData<UserData?> = MutableLiveData()
}