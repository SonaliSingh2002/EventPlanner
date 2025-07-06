package com.eventplanner.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.eventplanner.R
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

open class BaseActivity : AppCompatActivity() {

    fun setupInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun getCurrentDate(): String {
        val format = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        return sdf.format(Date())
    }

    /*fun showMaterialDatePickerForEditTextViewDOB(dateView: AppCompatEditText) {
        // Create calendar constraints to restrict selection to past dates only
        val constraintsBuilder = CalendarConstraints.Builder()
            .setValidator(DateValidatorPointBackward.now())

        // Build the date picker with the constraints
        val builder = MaterialDatePicker.Builder.datePicker()
            .setTheme(R.style.ThemeOverlay_App_MaterialCalendar)
            .setTitleText("Select date")
            .setCalendarConstraints(constraintsBuilder.build())

        val picker = builder.build()

        // Add a listener to update the dateView when a date is selected.
        picker.addOnPositiveButtonClickListener { selection ->
            // selection is a Long representing the time in milliseconds
            val date = Date(selection)
            val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formattedDate = format.format(date)
            dateView.setText(formattedDate)
        }

        // Show the date picker using the supportFragmentManager
        picker.show(supportFragmentManager, "MATERIAL_DATE_PICKER")
    }*/
}