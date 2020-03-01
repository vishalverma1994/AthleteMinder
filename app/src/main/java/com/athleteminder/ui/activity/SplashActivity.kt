package com.athleteminder.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.transition.Fade
import android.view.View
import android.view.View.VISIBLE
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.athleteminder.R
import com.athleteminder.utils.Utils
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {

    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this@SplashActivity
        setDisable()
        setListeners()
        setSpan()
        setAppLogoAnim()
        setSplashTextAnimation()
        setButtonAnimation()
        setTermsServiceVisibility()
        setIndicatorVisibility()
    }

    private fun setListeners() {
        btnCreateMyProfile.setOnClickListener(this::onClick)
        btnSkip.setOnClickListener(this::onClick)
    }

    private fun setDisable() {
        btnCreateMyProfile.isEnabled = false
        btnCreateMyProfile.isEnabled = false
    }

    private fun setSpan() {
        val spannable = SpannableString(getString(R.string.terms_of_service))
        spannable.setSpan(StyleSpan(Typeface.BOLD), 49, 65, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannable.setSpan(UnderlineSpan(), 49, 65, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(
                    mContext,
                    "Athlete Minder's Terms and Service accepted.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        spannable.setSpan(clickableSpan, 49, 65, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        tvTermsService.movementMethod = LinkMovementMethod.getInstance()
        tvTermsService.text = spannable
    }

    private fun setAppLogoAnim() {
        val animZoomOut =
            AnimationUtils.loadAnimation(
                mContext,
                R.anim.splash_zoom_out
            )
        val animZoomIn =
            AnimationUtils.loadAnimation(
                mContext,
                R.anim.splash_zoom_in
            )
        ivAppLogo.visibility = VISIBLE
        ivAppLogo.startAnimation(animZoomOut)
        Handler().postDelayed({
            ivAppLogo.startAnimation(animZoomIn)
        }, 1600)
    }

    private fun setSplashTextAnimation() {
        Handler().postDelayed({
            val transition = Fade()
            transition.duration = 500
            transition.addTarget(ivSplashText)
            ivSplashText.visibility = VISIBLE
        }, 3100)
    }

    private fun setButtonAnimation() {
        Handler().postDelayed({
            btnCreateMyProfile.visibility = VISIBLE
            Utils.tadaAnimation(btnCreateMyProfile)
        }, 3700)

        Handler().postDelayed({
            btnSkip.visibility = VISIBLE
            Utils.tadaAnimation(btnSkip)
        }, 4300)
    }

    private fun setTermsServiceVisibility() {
        Handler().postDelayed({
            tvTermsService.visibility = VISIBLE
        }, 5000)
    }

    private fun setIndicatorVisibility() {
        Handler().postDelayed({
            ivIndicator.visibility = VISIBLE
            setEnable()
        }, 5200)
    }

    private fun setEnable() {
        btnCreateMyProfile.isEnabled = true
        btnCreateMyProfile.isEnabled = true
    }

    private fun onClick(v: View) {
        when (v) {
            btnCreateMyProfile -> startActivity(Intent(mContext, DashboardActivity::class.java))
            btnSkip -> startActivity(Intent(mContext, DashboardActivity::class.java))
        }
    }
}
