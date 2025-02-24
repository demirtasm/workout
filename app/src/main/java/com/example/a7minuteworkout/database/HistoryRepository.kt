package com.example.a7minuteworkout.database

import com.example.a7minuteworkout.model.HistoryEntity

class HistoryRepository(private val historyDao: HistoryDao) {

    suspend fun insertHistory(historyEntity: HistoryEntity) = historyDao.insert(historyEntity)
}