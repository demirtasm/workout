package com.example.a7minuteworkout.view

import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.a7minuteworkout.app.WorkoutApp
import com.example.a7minuteworkout.databinding.ActivityFinishBinding
import com.example.a7minuteworkout.model.HistoryDao
import com.example.a7minuteworkout.model.HistoryEntity
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class FinishActivity : AppCompatActivity() {

    private var binding:ActivityFinishBinding?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

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
        val dao = (application as WorkoutApp).db.historyDao()
        addDateToDatabase(dao)
    }

    private fun addDateToDatabase(historyDao: HistoryDao){

        val c = Calendar.getInstance()
        val dateTime = c.time
        val sdf = SimpleDateFormat("dd MM yyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)
        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(date))
        }
    }
}