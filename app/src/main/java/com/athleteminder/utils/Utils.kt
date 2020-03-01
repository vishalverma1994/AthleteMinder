package com.athleteminder.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    const val MOTION_X_ARG = "MOTION_X_ARG"
    const val MOTION_Y_ARG = "MOTION_Y_ARG"

    fun simplifiedDate(date: Calendar): String {
        val simpleDateFormat =
            SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
        return simpleDateFormat.format(date.time)
    }

    fun tadaAnimation(view: View) {
        val animatorSet = AnimatorSet()
        animatorSet.duration = 500
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(
                view,
                "scaleX",
                1f,
                0.9f,
                0.9f,
                1.1f,
                1.1f,
                1.1f,
                1.1f,
                1.1f,
                1.1f,
                1f
            ),
            ObjectAnimator.ofFloat(
                view,
                "scaleY",
                1f,
                0.9f,
                0.9f,
                1.1f,
                1.1f,
                1.1f,
                1.1f,
                1.1f,
                1.1f,
                1f
            ),
            ObjectAnimator.ofFloat(view, "rotation", 0f, -3f, -3f, 3f, -3f, 3f, -3f, 3f, -3f, 0f)
        )
        animatorSet.start()
    }

    fun fadeInAnimator(view: View) {
        val animatorSet = AnimatorSet()
        animatorSet.duration = 1000
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        )
        animatorSet.start()
    }

    fun zoomAnimator(view: View) {
        val animatorSet = AnimatorSet()
        animatorSet.duration = 2000
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.5f),
            ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.5f),
            ObjectAnimator.ofFloat(view, "alpha", 1f, 0f),
            ObjectAnimator.ofFloat(view, "scaleX", 1.5f, 1f),
            ObjectAnimator.ofFloat(view, "scaleY", 1.5f, 1f),
            ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        )
        animatorSet.start()
    }
}