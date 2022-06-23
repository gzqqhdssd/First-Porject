package me.practice.minutes_calculator

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var txtSelectedDate: TextView? = null
    private var tvAgeDays: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)

        txtSelectedDate = findViewById(R.id.txtSelectedDate)

        tvAgeDays = findViewById(R.id.tvAgeDays)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }


    }

    fun clickDatePicker() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            { view, year, month, dayOfMonth ->
                Toast.makeText(
                    this,
                    "The month was ${month + 1}, the year was ${year}, the day was $dayOfMonth",
                    Toast.LENGTH_LONG
                ).show()

                val selectedDateString = "${month + 1}/${dayOfMonth}/${year}"

                txtSelectedDate?.setText(selectedDateString)

                val sdfFormatter = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)

                val theDate = sdfFormatter.parse(selectedDateString)

                theDate?.let {

                    val selectedDate = theDate.time / 60000

                    val currentDate = sdfFormatter.parse(sdfFormatter.format(System.currentTimeMillis()))

                    currentDate?.let {

                        val theCurrentDate = currentDate.time / 60000

                        val daysDifference = theCurrentDate - selectedDate

                        tvAgeDays?.setText(daysDifference.toString())

                    }
                }

                val selectedDate = theDate.time / 60000
                val currentDate = sdfFormatter.parse(sdfFormatter.format(System.currentTimeMillis()))

                val theCurrentDate = currentDate.time / 60000

                val daysDifference = (theCurrentDate - selectedDate) / 60

                tvAgeDays?.setText(daysDifference.toString())

            }, year, month, day)

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()










    }


}


