package com.torchtech.dateandtimepicker

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var display:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        display =findViewById(R.id.display)
        val dateButton =findViewById<Button>(R.id.showDate)
        val  timeButton =findViewById<Button>(R.id.showTime)

        dateButton.setOnClickListener {

            forDate()
        }

        timeButton.setOnClickListener {
            forTime()
        }

    }

    private fun forDate(){
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(this,{view :DatePicker ,selectedYear:Int,selectedMonth:Int,selectedDay:Int ->
            val selectedDate = "$selectedDay / ${selectedMonth+1} / $selectedYear\n"
            display.text=selectedDate
        },year,month,day)
        datePickerDialog.show()
    }

    private fun forTime(){
        val calendar =Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute =calendar.get(Calendar.MINUTE)

        val timePickerDialog =TimePickerDialog(this,{view,selectedHour,selectedMinute ->

            calendar.set(Calendar.HOUR_OF_DAY,selectedHour)
            calendar.set(Calendar.MINUTE,selectedMinute)
            val sdf=SimpleDateFormat("hh:mm a", Locale.getDefault())
            val selectedTime = sdf.format(calendar.time)
            display.text=selectedTime
        },hour,minute,false)
        timePickerDialog.show()
    }

}