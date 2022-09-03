package com.androstays.a7minuteworkout

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface HistoryDao {

    @Insert
    suspend fun insert(historyEntity: HistoryEntity)

    @Query("select * from history_table")
    fun fetchAllDates(): Flow<List<HistoryEntity>>

}