package com.athleteminder.ui.activity

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.athleteminder.R
import com.athleteminder.ui.fragments.ChooseDayFragment
import com.athleteminder.ui.fragments.TrainingFragment
import com.athleteminder.utils.Utils
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener
import kotlinx.android.synthetic.main.activity_dashboard.*
import java.util.*


class DashboardActivity : AppCompatActivity(R.layout.activity_dashboard) {

    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this@DashboardActivity
        setToolbar()
        Handler().postDelayed({
            setCalendar()
        }, 1000)

        setListeners()
        Handler().postDelayed({
            setFragment()
        }, 2000)
    }

    private fun setFragment() {
        val chooseDayFragment = ChooseDayFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, chooseDayFragment, "ChooseDayFragment").commit()
    }

    private fun setListeners() {
        toolbar.setNavigationOnClickListener {
            Toast.makeText(mContext, "Open Drawer", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.let { actionBar ->
            actionBar.setDisplayShowTitleEnabled(false)
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowHomeEnabled(false)
        }
        toolbar.setNavigationIcon(R.mipmap.ic_menu)
    }

    private fun setCalendar() {
        tvTitle.text = Utils.simplifiedDate(Calendar.getInstance())
        val startDate = Calendar.getInstance()
        startDate.add(Calendar.MONTH, -1)
        val endDate = Calendar.getInstance()
        endDate.add(Calendar.MONTH, 1)
        val horizontalCalendar =
            HorizontalCalendar.Builder(mContext as Activity, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(7)   // Number of Dates cells shown on screen (default to 5).
                .configure()    // starts configuration.
                .formatTopText("EEEEE")       // default to "MMM".
                .formatMiddleText("dd")    // default to "dd".
                .showTopText(true)              // show or hide TopText (default to true).
                .showBottomText(false)           // show or hide BottomText (default to true).
                .colorTextMiddle(Color.WHITE, Color.WHITE)
                .colorTextTop(Color.WHITE, Color.WHITE)
                /*.selectedDateBackground(
                    ContextCompat.getDrawable(
                        mContext,
                        R.drawable.circle_white_selector_background
                    )
                ) */     // set selected date cell background.
                .selectorColor(Color.TRANSPARENT)               // set selection indicator bar's color (default to colorAccent).
                .end()          // ends configuration.
                .defaultSelectedDate(Calendar.getInstance())    // Date to be selected at start (default to current day `Calendar.getInstance()`).
                .build()


        horizontalCalendar.calendarListener = object : HorizontalCalendarListener() {
            override fun onDateSelected(date: Calendar?, position: Int) {
                //do something
                date?.let {
                    tvTitle.text = Utils.simplifiedDate(it)
                }
            }
        }
    }

    fun showTrainingFragmentButton() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.flContainer, TrainingFragment.newInstance(sourceView = tvNoData))
            .commit()
    }
}
