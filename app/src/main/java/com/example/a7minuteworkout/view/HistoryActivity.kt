package com.example.a7minuteworkout.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minuteworkout.app.WorkoutApp
import com.example.a7minuteworkout.databinding.ActivityHistoryBinding
import com.example.a7minuteworkout.model.HistoryAdapter
import com.example.a7minuteworkout.database.HistoryDao
import com.example.a7minuteworkout.viewmodel.HistoryViewModel
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {

    private var binding: ActivityHistoryBinding? = null
    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        historyViewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)

        setSupportActionBar(binding?.toolbarHistoryActivity)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "HISTORY"
        }
        binding?.toolbarHistoryActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
        getAllCompletedDate()
    }

    private fun getAllCompletedDate() {
        lifecycleScope.launch {
        historyViewModel.getAllHistory.collect { allCompletedDates ->
                if(allCompletedDates.isNotEmpty()){
                    binding?.tvHistory?.visibility = View.VISIBLE
                    binding?.rvHistory?.visibility = View.VISIBLE
                    binding?.tvNoDataAvailable?.visibility = View.GONE
                    binding?.rvHistory?.layoutManager = LinearLayoutManager(applicationContext)
                    val dates = ArrayList<String>()
                    for(date in allCompletedDates){
                        dates.add(date.date)
                    }
                    val historyAdapter =HistoryAdapter(dates)
                    binding?.rvHistory?.adapter = historyAdapter

                }else{
                    binding?.tvHistory?.visibility = View.GONE
                    binding?.rvHistory?.visibility = View.GONE
                    binding?.tvNoDataAvailable?.visibility = View.VISIBLE
                }
            }
        }
    }
}