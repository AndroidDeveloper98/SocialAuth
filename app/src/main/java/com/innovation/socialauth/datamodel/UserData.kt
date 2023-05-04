package com.innovation.socialauth.datamodel

import com.innovation.socialauth.util.SocialAuth

data class UserData(val emailAddress : String , val profile : String , val name : String,val socialAuth : SocialAuth)
