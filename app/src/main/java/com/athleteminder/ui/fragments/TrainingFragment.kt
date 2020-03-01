package com.athleteminder.ui.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.athleteminder.R
import com.athleteminder.utils.RevealCircleAnimatorHelper
import kotlinx.android.synthetic.main.fragment_training.*


class TrainingFragment : Fragment() {

    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    companion object {
        fun newInstance(sourceView: View? = null) = TrainingFragment().apply {
            arguments = Bundle()
            arguments?.let {
                RevealCircleAnimatorHelper.addBundleValues(it, sourceView)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_training, container, false)
        RevealCircleAnimatorHelper
            .create(this, container)
            .start(rootView)
        return rootView
    }

    override fun onResume() {
        super.onResume()
        setListeners()
        Handler().postDelayed({
            setTrainingAnimate()
        }, 2000)

        Handler().postDelayed({
            setTextTrainingVisibility()
        },3100)
    }

    private fun setListeners() {
        btnHowDidItGo.setOnClickListener(this::onClick)
        btnDeleteEvent.setOnClickListener(this::onClick)
    }

    private fun setTrainingAnimate() {
        ivTraining.visibility = VISIBLE
        val anim = AnimationUtils.loadAnimation(mContext, R.anim.slide_up)
        ivTraining.startAnimation(anim)
    }

    private fun setTextTrainingVisibility() {
        tvTraining.visibility = VISIBLE
        tvReflectTraining.visibility = VISIBLE
        btnHowDidItGo.visibility = VISIBLE
        btnDeleteEvent.visibility = VISIBLE
    }

    private fun onClick(view: View){
        when(view) {
            btnHowDidItGo -> Toast.makeText(mContext, "Working in progress", Toast.LENGTH_SHORT).show()
            btnDeleteEvent -> Toast.makeText(mContext, "Working in progress", Toast.LENGTH_SHORT).show()
        }
    }
}
