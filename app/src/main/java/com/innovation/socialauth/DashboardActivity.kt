package com.innovation.socialauth

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.innovation.socialauth.databinding.ActivityDashboardBinding
import com.innovation.socialauth.util.FacebookAuth
import com.innovation.socialauth.util.GoogleAuth
import com.innovation.socialauth.util.SocialAuth

class DashboardActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityDashboardBinding.inflate(layoutInflater)
    }
    private var socialAuth: SocialAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initializeSetup()
    }

    private fun initializeSetup() {
        UserDataSingleton.userData.observe(this@DashboardActivity) {
            it?.let {
                socialAuth = it.socialAuth
                when (it.socialAuth) {
                    SocialAuth.FACEBOOK -> {
                        binding.tvUserName.text = it.name
                        binding.tvEmailAddress.text = it.emailAddress
                        Glide.with(this@DashboardActivity)
                            .load(it.profile)
                            .centerCrop()
                            .into(binding.ivUserImage)
                    }

                    SocialAuth.GOOGLE -> {
                        binding.tvUserName.text = it.name
                        binding.tvEmailAddress.text = it.emailAddress
                        Glide.with(this@DashboardActivity)
                            .load(it.profile)
                            .centerCrop()
                            .into(binding.ivUserImage)
                    }
                }
            }
        }

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setPositiveButton("Yes") { dialog, which ->
                    socialAuth?.let {
                        when (it) {
                            SocialAuth.GOOGLE -> {
                                GoogleAuth.Builder(this@DashboardActivity).signOutGoogle()
                            }

                            SocialAuth.FACEBOOK -> {
                                FacebookAuth.Builder(this@DashboardActivity).signOutFacebook()
                            }
                        }
                    }

                }
                .setNegativeButton("No", null)
                .setMessage("Are you sure want to logout from google?")
                .show()
        }
    }
}