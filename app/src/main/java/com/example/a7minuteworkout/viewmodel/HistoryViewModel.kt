package com.example.a7minuteworkout.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.a7minuteworkout.database.HistoryDatabase
import com.example.a7minuteworkout.database.HistoryRepository
import com.example.a7minuteworkout.model.HistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: HistoryRepository
    val getAllHistory: Flow<List<HistoryEntity>>

    init {
        val historyDao = HistoryDatabase.getInstance(application).historyDao()
        repository = HistoryRepository(historyDao)
        getAllHistory = repository.getAllHistory
    }

    fun insertHistory(historyEntity: HistoryEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertHistory(historyEntity)
        }
    }
}