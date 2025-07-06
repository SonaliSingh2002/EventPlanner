package com.eventplanner.view

import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.eventplanner.EventViewModel
import com.eventplanner.R
import com.eventplanner.base.BaseActivity
import com.eventplanner.databinding.ActivityAddEditEventBinding
import com.eventplanner.databinding.ActivityMainBinding
import com.eventplanner.room_db.Event
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddEditEventActivity : BaseActivity() {
    private lateinit var binding: ActivityAddEditEventBinding
    private lateinit var viewModel: EventViewModel

    private var selectedDate: String = ""
    private var selectedTime: String = ""
    val isEditMode : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddEditEventBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupInsets()

        viewModel = ViewModelProvider(this)[EventViewModel::class.java]

        val eventId = intent.getIntExtra("event_id", -1)
        val isEditMode = eventId != -1

        if (isEditMode) {
            binding.etTitle.setText(intent.getStringExtra("title"))
            binding.etDescription.setText(intent.getStringExtra("description"))
            selectedDate = intent.getStringExtra("date") ?: getTodayDate()
            selectedTime = intent.getStringExtra("time") ?: ""
            binding.btnSelectTime.text = selectedTime
            binding.btnSelectTime.setText(selectedDate)
        }


        // Get the date passed from MainActivity
        selectedDate = intent.getStringExtra("date") ?: getTodayDate()

        binding.btnSelectTime.setOnClickListener {
            showTimePickerDialog()
        }

        binding.btnSave.setOnClickListener {
            saveEvent()
        }

    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
            binding.btnSelectTime.text = selectedTime
        }, hour, minute, true)

        timePickerDialog.show()
    }

    private fun saveEvent() {
        val title = binding.etTitle.text.toString().trim()
        val description = binding.etDescription.text.toString().trim()

        if (title.isEmpty() || description.isEmpty() || selectedTime.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }
        val eventId = intent.getIntExtra("event_id", -1)
        val isEditMode = eventId != -1

        val event = Event(
            id = if (isEditMode) eventId else 0,
            title = title,
            description = description,
            date = selectedDate,
            time = selectedTime
        )

        if (isEditMode) {
            viewModel.update(event)
            Toast.makeText(this, "Event updated", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.insert(event)
            Toast.makeText(this, "Event saved", Toast.LENGTH_SHORT).show()
        }

        finish()
    }

   /* private fun saveEvent() {
        val title = binding.etTitle.text.toString().trim()
        val description = binding.etDescription.text.toString().trim()

        if (title.isEmpty() || description.isEmpty() || selectedTime.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val event = Event(
            title = title,
            description = description,
            date = selectedDate,
            time = selectedTime
        )

        viewModel.insert(event)
        Toast.makeText(this, "Event saved", Toast.LENGTH_SHORT).show()
        finish()
    }*/

    private fun getTodayDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }
}