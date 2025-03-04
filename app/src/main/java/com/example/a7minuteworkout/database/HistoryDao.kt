package com.example.a7minuteworkout.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.a7minuteworkout.model.HistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(historyEntity: HistoryEntity)

    @Query("SELECT * FROM `history-table`")
     fun fetchAllDates(): Flow<List<HistoryEntity>>

}