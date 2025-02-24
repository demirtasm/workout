package com.example.a7minuteworkout.database

import com.example.a7minuteworkout.model.HistoryEntity
import kotlinx.coroutines.flow.Flow

class HistoryRepository(private val historyDao: HistoryDao) {

    val getAllHistory: Flow<List<HistoryEntity>> = historyDao.fetchAllDates()

    suspend fun insertHistory(historyEntity: HistoryEntity) = historyDao.insert(historyEntity)
}