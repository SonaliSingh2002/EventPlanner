package com.eventplanner.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eventplanner.EventAdapter
import com.eventplanner.EventViewModel
import com.eventplanner.R
import com.eventplanner.base.BaseActivity
import com.eventplanner.databinding.ActivityMainBinding
import com.eventplanner.room_db.Event
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var eventViewModel: EventViewModel
    private lateinit var adapter: EventAdapter
    private var selectedDate: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupInsets()

       // adapter = EventAdapter()
        adapter = EventAdapter(
            onItemClick = { event -> showEditEventDialog(event) },
            onItemLongClick = { event -> showDeleteConfirmation(event) }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        eventViewModel = ViewModelProvider(this)[EventViewModel::class.java]


        // Set today's date
        selectedDate = getCurrentDate()
        binding.dateEditText.setText(selectedDate)
        observeEvents(selectedDate)

        // On date field click, show date picker
        binding.dateEditText.setOnClickListener {
            showMaterialDatePickerForEditTextViewDOB(binding.dateEditText)
        }
       /* binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
            eventViewModel.getEventsByDate(selectedDate).observe(this) { events ->
                adapter.submitList(events)
            }
        }
*/
        binding.fab.setOnClickListener {
            startActivity(Intent(this, AddEditEventActivity::class.java))
        }
    }

    private fun observeEvents(date: String) {
        eventViewModel.getEventsByDate(date).observe(this) { events ->
            adapter.submitList(events)
            binding.tvNoEvents.visibility = if (events.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    private fun showMaterialDatePickerForEditTextViewDOB(dateView: AppCompatEditText) {
      /*  val constraintsBuilder = CalendarConstraints.Builder()
            .setValidator(DateValidatorPointBackward.now())*/

        val picker = MaterialDatePicker.Builder.datePicker()
            .setTheme(R.style.ThemeOverlay_App_MaterialCalendar)
            .setTitleText("Select date")
           // .setCalendarConstraints(constraintsBuilder.build())
            .build()

        picker.addOnPositiveButtonClickListener { selection ->
            val date = Date(selection)
            val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formattedDate = format.format(date)
            dateView.setText(formattedDate)
            selectedDate = formattedDate
            observeEvents(formattedDate)
        }

        picker.show(supportFragmentManager, "MATERIAL_DATE_PICKER")
    }

    private fun showEditEventDialog(event: Event) {
        val intent = Intent(this, AddEditEventActivity::class.java)
        intent.putExtra("event_id", event.id)
        intent.putExtra("title", event.title)
        intent.putExtra("description", event.description)
        intent.putExtra("date", event.date)
        intent.putExtra("time", event.time)
        startActivity(intent)
    }

    private fun showDeleteConfirmation(event: Event) {
        AlertDialog.Builder(this)
            .setTitle("Delete Event")
            .setMessage("Are you sure you want to delete this event?")
            .setPositiveButton("Yes") { _, _ ->
                eventViewModel.delete(event)
                Toast.makeText(this, "Event deleted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No", null)
            .show()
    }

}