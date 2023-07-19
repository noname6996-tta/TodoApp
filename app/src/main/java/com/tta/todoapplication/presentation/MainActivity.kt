package com.tta.todoapplication.presentation

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationBarView
import com.tta.todoapplication.R
import com.tta.todoapplication.data.model.ToDoData
import com.tta.todoapplication.data.viewmodel.ToDoViewModel
import com.tta.todoapplication.databinding.ActivityMainBinding
import com.tta.todoapplication.presentation.fragment.SharedViewModel
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mToDoViewModel: ToDoViewModel by viewModels()
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navHostFragment: NavHostFragment
    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    var myHour: Int = 0
    var myMinute: Int = 0
    var time: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
        addEvent()
    }

    private fun addEvent() {
        binding.fab.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.dialog_add_task, null)
            dialog.setContentView(view)
            val imgSetTime = view.findViewById<ImageView>(R.id.imgSetTime)
            val imgSetTag = view.findViewById<ImageView>(R.id.imgSetTag)
            val imgSetFlag = view.findViewById<ImageView>(R.id.imgSetFlag)
            val imgAddTask = view.findViewById<ImageView>(R.id.imgAddTask)
            //

            dialog.show()

            imgSetTime.setOnClickListener {
                val calendar: Calendar = Calendar.getInstance()
                day = calendar.get(Calendar.DAY_OF_MONTH)
                month = calendar.get(Calendar.MONTH)
                year = calendar.get(Calendar.YEAR)
                val datePickerDialog =
                    DatePickerDialog(this@MainActivity, this@MainActivity, year, month, day)
                datePickerDialog.show()
            }

            imgSetFlag.setOnClickListener { }
            imgSetTag.setOnClickListener { }
            imgAddTask.setOnClickListener {
                val edtAddTitle = view.findViewById<EditText>(R.id.edtAddTitle)
                val edtAddDesc = view.findViewById<EditText>(R.id.edtAddDesc)
                val title = edtAddTitle.text.toString().trim()
                val desc = edtAddDesc.text.toString().trim()
                val validation = mSharedViewModel.verifyDataFromUser(
                    title,
                    desc
                )
                if (validation) {
                    val todoData = ToDoData(0,title,desc, 0,time, 0,0,"School")
                    mToDoViewModel.insertData(todoData)
                    dialog.dismiss()
                } else {
                    Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun initUi() {
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.frameMain) as NavHostFragment
        navController = navHostFragment.findNavController()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment, R.id.calenderFragment, R.id.focusFragment, R.id.profileFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                else -> {
                    binding.bottomNavigationView.visibility = View.GONE
                }
            }
        }

        val mNavigationItemSelected = object : NavigationBarView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.homeFragment -> {
                        NavigationUI.onNavDestinationSelected(
                            item,
                            navController
                        )
                        return true
                    }
                    R.id.calenderFragment -> {
                        NavigationUI.onNavDestinationSelected(
                            item,
                            navController
                        )
                        return true
                    }
                    R.id.focusFragment -> {
                        NavigationUI.onNavDestinationSelected(
                            item,
                            navController
                        )
                        return true
                    }
                    R.id.profileFragment -> {
                        NavigationUI.onNavDestinationSelected(
                            item,
                            navController
                        )
                        return true
                    }
                    else -> {
                        return false
                    }
                }
            }
        }

        binding.bottomNavigationView.setupWithNavController(navController)
        binding.bottomNavigationView.apply {
            setOnItemSelectedListener(mNavigationItemSelected)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return (NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp())
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myDay = day
        myYear = year
        myMonth = month
        val calendar: Calendar = Calendar.getInstance()
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(
            this@MainActivity, this@MainActivity, hour, minute,
            DateFormat.is24HourFormat(this)
        )
        timePickerDialog.show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        myHour = hourOfDay
        myMinute = minute
        Log.e(
            "tta",
            "Year: $myYear" + "Month: " + myMonth + "Day: " + myDay + "Hour: " + myHour + "Minute: " + myMinute
        )
//        2016-01-24T16:00:00.000Z
        time = "$myYear-$myMonth-$myDay"+"T"+"$myHour:$minute:00.000Z"
    }
}