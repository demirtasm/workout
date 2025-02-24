package com.example.a7minuteworkout.view

import android.icu.util.Calendar
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.a7minuteworkout.app.WorkoutApp
import com.example.a7minuteworkout.databinding.ActivityFinishBinding
import com.example.a7minuteworkout.database.HistoryDao
import com.example.a7minuteworkout.model.HistoryEntity
import com.example.a7minuteworkout.viewmodel.HistoryViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class FinishActivity : AppCompatActivity() {

    private var binding:ActivityFinishBinding?= null
    private lateinit var historyViewModel: HistoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        historyViewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        setSupportActionBar(binding?.toolbarFinishActivity)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarFinishActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        binding?.btnFinish?.setOnClickListener {
            finish()
        }
        addDateToDatabase()
    }

    private fun addDateToDatabase(){

        val c = Calendar.getInstance()
        val dateTime = c.time
        val sdf = SimpleDateFormat("dd MM yyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)

        historyViewModel.insertHistory(HistoryEntity(date))
    }
}