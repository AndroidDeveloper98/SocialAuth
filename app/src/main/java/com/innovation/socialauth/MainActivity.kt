package com.innovation.socialauth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.facebook.appevents.codeless.internal.ViewHierarchy.setOnClickListener
import com.innovation.socialauth.databinding.ActivityMainBinding
import com.innovation.socialauth.util.FacebookAuth
import com.innovation.socialauth.util.GoogleAuth

class MainActivity : AppCompatActivity() {

    private val binding by lazy (LazyThreadSafetyMode.NONE){
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var mContext: Context
    private lateinit var googleAuth: GoogleAuth.Builder
    private lateinit var facebookAuth: FacebookAuth.Builder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mContext = this
        initializeSetup()
    }

    private fun initializeSetup() {
        googleAuth = GoogleAuth.Builder(this@MainActivity).isUserLogin()
        facebookAuth = FacebookAuth.Builder(this@MainActivity).isUserLogin()
        binding.ivGoogle.setOnClickListener {
            googleAuth.signIn(googleAuthActivityResultLauncher)
        }
        binding.ivFacebook.setOnClickListener {
            facebookAuth.signIn()
        }
    }

    private val googleAuthActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            googleAuth.handleSignInResult(result)
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        facebookAuth.getCallbackManager().onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

}