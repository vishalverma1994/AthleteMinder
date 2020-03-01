package com.athleteminder.ui.fragments

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.View.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.athleteminder.R
import com.athleteminder.ui.activity.DashboardActivity
import kotlinx.android.synthetic.main.fragment_choose_day.*


class ChooseDayFragment : Fragment(R.layout.fragment_choose_day), OnTouchListener {

    private lateinit var mContext: Context
    private var hit = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        ivTraining.setOnTouchListener(this)
        ivCirclePlus.setOnDragListener(
            TrashDragListener(
                R.mipmap.ic_plus_training,
                R.mipmap.ic_plus_white
            )
        )
    }

    override fun onResume() {
        super.onResume()
        rippleCircle.startRippleAnimation()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        Log.d("Debug", "onTouch: view->view$view\n MotionEvent$motionEvent")
        return if (motionEvent.action == MotionEvent.ACTION_DOWN) {
            val data = ClipData.newPlainText("", "")
            val shadowBuilder = DragShadowBuilder(view)
            view.startDrag(data, shadowBuilder, view, 0)
            view.visibility = INVISIBLE
            tvTraining.visibility = INVISIBLE
            true
        } else {
            false
        }
    }

    inner class TrashDragListener(private val enterShape: Int, private val normalShape: Int) :
        OnDragListener {
        private var hit = false

        override fun onDrag(v: View, event: DragEvent): Boolean {
            val containerView = v as ImageView
            val draggedView =
                event.localState as ImageView
            return when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    Log.d("Debug", "onDrag: ACTION_DRAG_STARTED")
                    hit = false
                    true
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    Log.d("Debug", "onDrag: ACTION_DRAG_ENTERED")
                    containerView.setImageResource(enterShape)
                    openTrainingFragment()
                    true
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    Log.d("Debug", "onDrag: ACTION_DRAG_EXITED")
                    containerView.setImageResource(normalShape)
                    true
                }
                DragEvent.ACTION_DROP -> {
                    Log.d("Debug", "onDrag: ACTION_DROP")
                    hit = true
                    draggedView.post { draggedView.visibility = GONE }
                    true
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    Log.d("Debug", "onDrag: ACTION_DRAG_ENDED")
                    containerView.setImageResource(normalShape)
                    v.setVisibility(VISIBLE)
                    tvTraining.visibility = VISIBLE
                    if (!hit) {
                        draggedView.post { draggedView.visibility = VISIBLE }
                    }
                    true
                }
                else -> true
            }
        }
    }

    private fun openTrainingFragment() {
        (mContext as DashboardActivity).showTrainingFragmentButton()
    }
}